package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Model_Libro;
import com.example.demo.service.service_libro;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class controller_libro {
    
    @Autowired
    private service_libro libroService;

    // Obtener todos los libros
    @GetMapping
    public List<Model_Libro> getAllLibros() {
        return libroService.getAllLibros();
    }

    // mostrar un libro por id
    @GetMapping("/{id}")
    public ResponseEntity<Model_Libro> getLibroById(@PathVariable Long id) {
        Optional<Model_Libro> libro = libroService.getLibroById(id);
        return libro.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Guardar un nuevo libro
    @PostMapping
    public ResponseEntity<Model_Libro> createLibro(@RequestBody Model_Libro libro) {
        Model_Libro savedLibro = libroService.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibro);
    }

    // Actualizar un libro por su id
    @PutMapping("/{id}")
    public ResponseEntity<Model_Libro> updateLibro(@PathVariable Long id, @RequestBody Model_Libro libroDetails) {
        try {
            Model_Libro updatedLibro = libroService.update(id, libroDetails);
            return ResponseEntity.ok(updatedLibro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar un libro por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
