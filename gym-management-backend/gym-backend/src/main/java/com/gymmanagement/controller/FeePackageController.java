package com.gymmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.model.FeePackage;
import com.gymmanagement.service.FeePackageService;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin("*")
public class FeePackageController {

    private final FeePackageService packageService;

    public FeePackageController(FeePackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public List<FeePackage> getAll() {
        return packageService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<FeePackage> p = packageService.getById(id);
        return p.isPresent() ? ResponseEntity.ok(p.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FeePackage pkg) {
        return ResponseEntity.ok(packageService.save(pkg));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FeePackage updated) {

        Optional<FeePackage> existing = packageService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        FeePackage pkg = existing.get();
        pkg.setName(updated.getName());
        pkg.setPrice(updated.getPrice());
        pkg.setDurationMonths(updated.getDurationMonths());

        return ResponseEntity.ok(packageService.save(pkg));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        packageService.delete(id);
        return ResponseEntity.ok("Package deleted");
    }
}
