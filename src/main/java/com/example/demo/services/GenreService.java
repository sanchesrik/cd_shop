package com.example.demo.services;

import com.example.demo.domain.Genre;
import com.example.demo.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }
    public List<Genre> getAllGenre(){
        return repository.findAll();
    }
}
