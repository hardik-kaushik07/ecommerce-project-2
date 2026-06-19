package com.hardik.ecom_project.Controller;

import com.hardik.ecom_project.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> pay(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.processPayment(orderId));
    }
}

