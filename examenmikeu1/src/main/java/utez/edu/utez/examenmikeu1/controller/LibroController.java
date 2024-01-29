package utez.edu.utez.examenmikeu1.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.utez.examenmikeu1.model.dto.LibroDto;
import utez.edu.utez.examenmikeu1.model.entity.LibroBean;
import utez.edu.utez.examenmikeu1.service.impl.ImplLibro;

import java.util.*;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/api")
public class LibroController {
    private final ImplLibro libroService;

    @PostMapping("/")
    public LibroDto create(@RequestBody LibroDto libroDto) {
        LibroBean libroCreate = libroService.save(libroDto);
        return libroDto.builder()
                .id_libro(libroCreate.getId_libro())
                .nombre(libroCreate.getNombre())
                .isbn(libroCreate.getIsbn())
                .autor(libroCreate.getAutor())
                .numpag(libroCreate.getNumpag())
                .categoria(libroCreate.getCategoria())
                .fecha_public(libroCreate.getFecha_public())
                .folio(libroCreate.getFolio())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody LibroDto libroDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            LibroBean libroUpdate = libroService.save(libroDto);
            return new ResponseEntity<>(libroUpdate, HttpStatus.OK);
        } catch (DataAccessException ex) {
            response.put("msj", ex.getMessage());
            response.put("libro", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try{
            LibroBean libro = libroService.findById(id);
            libroService.delete(libro);
            return new ResponseEntity<>(libro, HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex){
            response.put("msj", ex.getMessage());
            response.put("Libro no existe", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<LibroBean> getLibros() {
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    public LibroBean getLibro(@PathVariable Integer id) {
        return libroService.findById(id);
    }

}
