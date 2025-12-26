package com.gymmanagement.controller;

import com.gymmanagement.model.Member;
import com.gymmanagement.model.Supplement;
import com.gymmanagement.model.SupplementOrder;
import com.gymmanagement.model.User;
import com.gymmanagement.service.MemberService;
import com.gymmanagement.service.SupplementOrderService;
import com.gymmanagement.service.SupplementService;
import com.gymmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class SupplementOrderController {

    private final SupplementOrderService orderService;
    private final MemberService memberService;
    private final SupplementService supplementService;
    private final UserService userService;

    public SupplementOrderController(
            SupplementOrderService orderService,
            MemberService memberService,
            SupplementService supplementService,
            UserService userService) {

        this.orderService = orderService;
        this.memberService = memberService;
        this.supplementService = supplementService;
        this.userService = userService;
    }

    // Get all orders
    @GetMapping
    public List<SupplementOrder> getAll() {
        return orderService.getAll();
    }

    // Get orders for particular member
    @GetMapping("/member/{memberId}")
    public List<SupplementOrder> getForMember(@PathVariable Long memberId) {
        return orderService.getForMember(memberId);
    }

    // Create new order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, String> request) {

        Long memberId = Long.parseLong(request.get("memberId"));
        Long supplementId = Long.parseLong(request.get("supplementId"));
        Long adminId = Long.parseLong(request.get("adminId"));
        int quantity = Integer.parseInt(request.get("quantity"));

        Optional<Member> member = memberService.getMember(memberId);
        Optional<Supplement> supplement = supplementService.get(supplementId);
        Optional<User> admin = userService.getUser(adminId);

        if (member.isEmpty()) return ResponseEntity.badRequest().body("Invalid member id");
        if (supplement.isEmpty()) return ResponseEntity.badRequest().body("Invalid supplement id");
        if (admin.isEmpty()) return ResponseEntity.badRequest().body("Invalid admin id");

        double total = supplement.get().getPrice() * quantity;

        SupplementOrder order = new SupplementOrder(
                member.get(),
                supplement.get(),
                admin.get(),
                quantity,
                total,
                null   // orderService will set date
        );

        return ResponseEntity.ok(orderService.save(order));
    }
}
