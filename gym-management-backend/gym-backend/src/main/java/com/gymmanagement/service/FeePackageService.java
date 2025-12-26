package com.gymmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gymmanagement.model.FeePackage;
import com.gymmanagement.repository.FeePackageRepository;

@Service
public class FeePackageService {

    private final FeePackageRepository feePackageRepository;

    public FeePackageService(FeePackageRepository feePackageRepository) {
        this.feePackageRepository = feePackageRepository;
    }

    public List<FeePackage> getAll() {
        return feePackageRepository.findAll();
    }

    public Optional<FeePackage> getById(Long id) {
        return feePackageRepository.findById(id);
    }

    public FeePackage save(FeePackage feePackage) {
        return feePackageRepository.save(feePackage);
    }

    public void delete(Long id) {
        feePackageRepository.deleteById(id);
    }
}
