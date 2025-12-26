package com.gymmanagement.controller;

import com.gymmanagement.model.Supplement;
import com.gymmanagement.service.SupplementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplements")
@CrossOrigin("*")
public class SupplementController {

    private final SupplementService supplementService;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping
    public List<Supplement> getAll() {
        return supplementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Supplement> s = supplementService.get(id);
        return s.isPresent() ? ResponseEntity.ok(s.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Supplement supplement) {
        return ResponseEntity.ok(supplementService.save(supplement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Supplement updated) {

        Optional<Supplement> existing = supplementService.get(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Supplement s = existing.get();
        s.setName(updated.getName());
        s.setPrice(updated.getPrice());
        s.setStock(updated.getStock());

        return ResponseEntity.ok(supplementService.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        supplementService.get(id).ifPresent(s -> {
            // Delete logic or other actions if needed
        });
        return ResponseEntity.ok("Supplement deleted");
    }
}
