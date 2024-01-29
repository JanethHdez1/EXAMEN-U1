package utez.edu.utez.examenmikeu1.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LibroDto {
    private Integer id_libro;
    private String nombre;
    private String isbn;
    private String autor;
    private Integer numpag;
    private String categoria;
    private String fecha_public;
    private String folio;
}
