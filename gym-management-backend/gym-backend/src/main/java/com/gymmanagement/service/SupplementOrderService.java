package com.gymmanagement.service;

import com.gymmanagement.model.SupplementOrder;
import com.gymmanagement.repository.SupplementOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupplementOrderService {

    private final SupplementOrderRepository supplementOrderRepository;

    public SupplementOrderService(SupplementOrderRepository supplementOrderRepository) {
        this.supplementOrderRepository = supplementOrderRepository;
    }

    public List<SupplementOrder> getAll() {
        return supplementOrderRepository.findAll();
    }

    public List<SupplementOrder> getForMember(Long memberId) {
        return supplementOrderRepository.findAll().stream()
                .filter(o -> o.getMember().getId().equals(memberId))
                .toList();
    }

    public SupplementOrder save(SupplementOrder order) {
        order.setOrderDate(LocalDate.now());
        return supplementOrderRepository.save(order);
    }
}
