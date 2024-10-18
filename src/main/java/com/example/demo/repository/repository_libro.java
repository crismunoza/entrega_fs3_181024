package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Model_Libro;

// Repositorio para la entidad Model_Libro
public interface repository_libro extends JpaRepository<Model_Libro, Long> {
    
}
