package com.Challenge.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento; // CAMBIADO A INTEGER

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        // Protección contra nulos al convertir
        this.fechaDeNacimiento = (datosAutor.fechaDeNacimiento() != null) ?
                Integer.valueOf(datosAutor.fechaDeNacimiento()) : 0;
        this.fechaDeFallecimiento = (datosAutor.fechaDeFallecimiento() != null) ?
                Integer.valueOf(datosAutor.fechaDeFallecimiento()) : 0;
    }

    // Getters y Setters corregidos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
    public Integer getFechaDeFallecimiento() { return fechaDeFallecimiento; }
    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) { this.fechaDeFallecimiento = fechaDeFallecimiento; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Autor: " + nombre + " (" + fechaDeNacimiento + " - " +
                (fechaDeFallecimiento != 0 ? fechaDeFallecimiento : "N/A") + ")";
    }
}