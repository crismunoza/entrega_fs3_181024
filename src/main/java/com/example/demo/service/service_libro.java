package com.example.demo.service;

import com.example.demo.model.Model_Libro;
import com.example.demo.repository.repository_libro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class service_libro {

    private final repository_libro libroRepository;

    public service_libro(repository_libro libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Método para traer todos los libros
    public List<Model_Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Método para traer un libro por ID
    public Optional<Model_Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    // Método para guardar un libro
    public Model_Libro save(Model_Libro libro) {
        return libroRepository.save(libro);
    }

    // Método para actualizar un libro
    public Model_Libro update(Long id, Model_Libro libroDetails) {
        Model_Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id " + id));

        libro.setTitulo(libroDetails.getTitulo());
        libro.setAutor(libroDetails.getAutor());
        libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
        libro.setEditorial(libroDetails.getEditorial());
        libro.setNumeroPaginas(libroDetails.getNumeroPaginas());

        return libroRepository.save(libro);
    }

    // Método para eliminar un libro
    public void delete(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id " + id);
        }
        libroRepository.deleteById(id);
    }
}
