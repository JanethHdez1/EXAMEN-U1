package utez.edu.utez.examenmikeu1.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "libros")
public class LibroBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro", nullable = false)
    private Integer id_libro;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "numpag", nullable = false)
    private Integer numpag;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "fecha public", nullable = false)
    private String fecha_public;

    @Column(name = "folio")
    private String folio;

/*
    Nombre del Libro: El título del libro.
    ISBN: Número Internacional Normalizado del Libro.
            Autor: Nombre del autor del libro.
            Páginas: Cantidad de páginas que tiene el libro.
            Categoría: La categoría a la que pertenece el libro (p. ej., Ciencia Ficción, Historia, Novela, etc.).
    Fecha de publicación
    Folio (Se genera automáticamente)*/

}
