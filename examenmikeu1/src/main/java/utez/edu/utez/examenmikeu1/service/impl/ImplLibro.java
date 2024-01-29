package utez.edu.utez.examenmikeu1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.utez.examenmikeu1.model.dao.LibroDao;
import utez.edu.utez.examenmikeu1.model.dto.LibroDto;
import utez.edu.utez.examenmikeu1.model.entity.LibroBean;
import utez.edu.utez.examenmikeu1.service.ILibro;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class ImplLibro implements ILibro {
    @Autowired
    private LibroDao libroDao;

    @Override
    @Transactional
    public LibroBean save(LibroDto libroDto) {
        String folio = getFolio(libroDto);

        LibroBean libroCreate = LibroBean.builder()
                .id_libro(libroDto.getId_libro())
                .nombre(libroDto.getNombre())
                .isbn(libroDto.getIsbn())
                .autor(libroDto.getAutor())
                .numpag(libroDto.getNumpag())
                .categoria(libroDto.getCategoria())
                .fecha_public(libroDto.getFecha_public())
                .folio(folio)
                .build();

        return libroDao.save(libroCreate);
    }

    @Override
    @Transactional
    public LibroBean update(Integer id, LibroDto libroDto) {
        LibroBean existingLibro = libroDao.findById(id).orElse(null);

        if (existingLibro != null){
            existingLibro.setNombre(libroDto.getNombre());
            existingLibro.setIsbn(libroDto.getIsbn());
            existingLibro.setAutor(libroDto.getAutor());
            existingLibro.setNumpag(libroDto.getNumpag());
            existingLibro.setCategoria(libroDto.getCategoria());
            existingLibro.setFecha_public(libroDto.getFecha_public());

            String updatedFolio = getFolio(libroDto);
            existingLibro.setFolio(updatedFolio);

            return libroDao.save(existingLibro);
        }else{
            return null;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public LibroBean findById(Integer id) {
        return libroDao.findById(id).orElse(null);
    }

    @Override
    public void delete(LibroBean libro) {
        libroDao.delete(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroBean> findAll() {
        return (List<LibroBean>) libroDao.findAll();
    }

    /*
    El folio del libro se debe componer de la primera letra del título,
    la primera letra del nombre del autor, las primeras dos letras del
    apellido del autor, la fecha de publicación (yyyy-MM-dd),
    las primeras 4 letras del ISBN y 2 dígitos random (letras y números).

    EJ.
    Titulo: El hechizo
    ISBN: 23456789056
    Autor: Janeth Hernandez
    NumPag: 38
    Categoria: Ciencia Ficcion
    Fecha_public: 1990-04-02

    Folio: EJHE199004022345 A1


    */
    private String getFolio(LibroDto libroDto){
        String nombre = libroDto.getNombre().substring(0,1).toUpperCase();
        String autor = libroDto.getAutor().substring(0,1).toUpperCase();
        String apellido = getLetrasApellido(libroDto.getAutor().toUpperCase(Locale.ROOT));
        String anio = libroDto.getFecha_public().substring(0, 4);
        String mes = String.format("%02d", Integer.parseInt(libroDto.getFecha_public().substring(5, 7)));
        String dia= String.format("%02d", Integer.parseInt(libroDto.getFecha_public().substring(8, 10)));
        String isbn = libroDto.getIsbn().substring(0,4);
        String random = generateRandomString(2).toUpperCase();

        return nombre + autor + apellido + anio + mes + dia + isbn + random;
    }

    private String getLetrasApellido(String str) {
        String[] parts = str.split(" ");
        String part1 = parts[0]; // nombre
        String part2 = parts[1]; // apellido
        return part2.substring(0, 2);
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }

        return randomString.toString();
    }
}
