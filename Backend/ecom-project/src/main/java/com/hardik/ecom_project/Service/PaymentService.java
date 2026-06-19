package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.Orders;
import com.hardik.ecom_project.Model.Payment;
import com.hardik.ecom_project.Repository.OrderRepository;
import com.hardik.ecom_project.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Object processPayment(Long orderId) {

        Orders order = orderRepository.findById(orderId).orElseThrow();

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setStatus("SUCCESS");
        payment.setMethod("MOCK");

        order.setStatus("PAID");

        return paymentRepository.save(payment);
    }
}
