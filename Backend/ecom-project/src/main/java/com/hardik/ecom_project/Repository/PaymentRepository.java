package com.hardik.ecom_project.Repository;

import com.hardik.ecom_project.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
