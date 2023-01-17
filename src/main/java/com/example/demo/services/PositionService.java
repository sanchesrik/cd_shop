package com.example.demo.services;

import com.example.demo.domain.Position;
import com.example.demo.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final PositionRepository repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public List<Position> getAllPosition(){return repository.findAll();}
}
