package utez.edu.utez.examenmikeu1.service;

import org.springframework.transaction.annotation.Transactional;
import utez.edu.utez.examenmikeu1.model.dto.LibroDto;
import utez.edu.utez.examenmikeu1.model.entity.LibroBean;

import java.util.List;

public interface ILibro {
    LibroBean save(LibroDto libroDto);

    @Transactional
    LibroBean update(Integer id, LibroDto libroDto);


    LibroBean findById(Integer id);

    void delete(LibroBean libro);

    List<LibroBean> findAll();
}
