package com.hardik.ecom_project.Controller;

import com.hardik.ecom_project.Service.OrderService;
import com.hardik.ecom_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/place")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> placeOrder(Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        return ResponseEntity.ok(orderService.placeOrder(userId));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrders(Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}
