# 📚 Challenge LiterAlura - Catálogo de Libros

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-red)

## 📝 Descripción del Proyecto
Este proyecto es una aplicación de consola desarrollada como parte del **Challenge LiterAlura** (Alura Latam). Su objetivo principal es gestionar un catálogo de libros mediante el consumo de la API externa **Gutendex**.

La aplicación permite buscar obras literarias, procesar sus datos en formato JSON y almacenarlos en una base de datos relacional para realizar consultas personalizadas sobre autores y libros registrados.

## 🚀 Funcionalidades
* **Búsqueda de libros por título**: Consulta la API Gutendex y registra el primer resultado coincidente en la base de datos.
* **Listar libros registrados**: Recupera y muestra en consola todos los libros almacenados en PostgreSQL.
* **Listar autores registrados**: Muestra la lista completa de autores cuyos libros han sido guardados.
* **Listar autores vivos en un año determinado**: Filtra los autores registrados basándose en un año específico mediante consultas JPQL personalizadas.
* **Listar libros por idioma**: Permite filtrar las obras guardadas según su código de idioma (ej: `es` para español, `en` para inglés).
* **Gestión de errores y duplicados**: El sistema evita el registro repetido de libros y maneja excepciones de datos para garantizar la estabilidad de la aplicación.

## 🛠️ Tecnologías Utilizadas
* **Java 25**: Lenguaje de programación robusto para el backend.
* **Spring Boot 3.2.3**: Framework para simplificar la configuración y el desarrollo.
* **Spring Data JPA**: Para el manejo de la persistencia y mapeo objeto-relacional (ORM).
* **PostgreSQL**: Base de datos relacional para el almacenamiento de la biblioteca.
* **Jackson**: Librería para la serialización y deserialización de JSON.
* **Maven**: Herramienta de gestión de dependencias.

## ⚙️ Configuración e Instalación
1. Clona este repositorio:
   ```bash
   git clone [https://github.com/elianaproserpio/Challenge-LiterAlura.git](https://github.com/elianaproserpio/Challenge-LiterAlura.git)

2. Configura las credenciales de tu base de datos en src/main/resources/application.properties o mediante variables de entorno:

   DB_HOST: Host de tu base de datos PostgreSQL.

   DB_USER: Usuario de acceso.

   DB_PASSWORD: Contraseña de acceso.

3. Compila y ejecuta la aplicación utilizando Maven o directamente desde tu IDE (IntelliJ IDEA).

👤 Autora
Eliana Proserpio

🎓 Estudiante de Lic Sistemas en Sistemas de Informacion- Universidad Nacional de Santiago del Estero (FCEyT) 
