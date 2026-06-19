package com.hardik.ecom_project.Repository;

import com.hardik.ecom_project.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
    Cart findByUserIdAndProductId(Long userId, Long productId);
}
