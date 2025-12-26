package com.gymmanagement.service;

import com.gymmanagement.model.Supplement;
import com.gymmanagement.repository.SupplementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public List<Supplement> getAll() {
        return supplementRepository.findAll();
    }

    public Optional<Supplement> get(Long id) {
        return supplementRepository.findById(id);
    }

    public Supplement save(Supplement supplement) {
        return supplementRepository.save(supplement);
    }
}
