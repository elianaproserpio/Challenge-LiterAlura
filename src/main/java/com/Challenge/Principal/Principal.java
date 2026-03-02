package com.Challenge.Principal;

import com.Challenge.Model.Autor;
import com.Challenge.Model.Datos;
import com.Challenge.Model.DatosLibro;
import com.Challenge.Model.Libro;
import com.Challenge.Repository.AutorRepository;
import com.Challenge.Repository.LibroRepository;
import com.Challenge.Service.ConsumoAPI;
import com.Challenge.Service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio; // Nueva variable

    // Actualizamos el constructor para recibir ambos
    public Principal(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    -----------------------------------
                    Elija la opción a través de su número:
                    1- buscar libro por título
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    0- salir
                    -----------------------------------
                    """;
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroWeb();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = lectura.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void buscarLibroWeb() {
        try {
            Datos datosBusqueda = getDatosLibro();

            if (datosBusqueda != null && !datosBusqueda.resultados().isEmpty()) {
                DatosLibro primerLibro = datosBusqueda.resultados().get(0);

                Optional<Libro> libroExistente = libroRepositorio.findByTituloIgnoreCase(primerLibro.titulo());

                if (libroExistente.isPresent()) {
                    System.out.println("\n[!] El libro ya está registrado en la base de datos.");
                    System.out.println(libroExistente.get());
                } else {
                    Libro libroNuevo = new Libro(primerLibro);

                    if (!primerLibro.autor().isEmpty()) {
                        Autor autor = new Autor(primerLibro.autor().get(0));
                        libroNuevo.setAutor(autor);
                    }

                    // Si esto falla (título largo, error de red, etc.), irá al catch
                    libroRepositorio.save(libroNuevo);
                    System.out.println("\n[+] Libro registrado con éxito:");
                    System.out.println(libroNuevo);
                }
            } else {
                System.out.println("Libro no encontrado en la API.");
            }
        } catch (Exception e) {
            // Esto evita que el programa se cierre
            System.out.println("\n[X] Error: No se pudo registrar el libro. Intente con otro título.");
            System.out.println("Detalle: " + e.getMessage());
        }
    }


    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepositorio.findAll(); // Uso de variable en minúscula
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll(); // Uso de variable en minúscula
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingrese el año que desea consultar:");
        try {
            var anio = lectura.nextInt();
            lectura.nextLine(); // Limpiamos el buffer del scanner

            List<Autor> autoresVivos = autorRepositorio.autoresVivosEnDeterminadoAnio(anio);

            if (autoresVivos.isEmpty()) {
                // Caso donde no hay registros para ese año
                System.out.println("\n[!] No se encontraron autores vivos registrados en el año " + anio + ".");
            } else {
                // Caso donde sí hay datos registrados
                System.out.println("\n--- AUTORES VIVOS EN EL AÑO " + anio + " ---");
                autoresVivos.forEach(System.out::println);
                System.out.println("-------------------------------------------\n");
            }
        } catch (Exception e) {
            System.out.println("\n[X] Error: Debe ingresar un número válido para el año.");
            lectura.nextLine(); // Limpiamos el scanner en caso de error de entrada
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        var idioma = lectura.nextLine();
        List<Libro> librosPorIdioma = libroRepositorio.findByIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
    }
}