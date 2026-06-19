package com.hardik.ecom_project.Repository;

import com.hardik.ecom_project.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long userId);
}
