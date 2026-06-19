package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.Cart;
import com.hardik.ecom_project.Model.OrderItem;
import com.hardik.ecom_project.Model.Orders;
import com.hardik.ecom_project.Model.Users;
import com.hardik.ecom_project.Repository.CartRepository;
import com.hardik.ecom_project.Repository.OrderItemRepository;
import com.hardik.ecom_project.Repository.OrderRepository;
import com.hardik.ecom_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Orders placeOrder(Long userId){
        List<Cart> cartItems = cartRepository.findByUserId(userId);


    Users user = userRepository.findById(userId).orElse(null);
    if(user==null){
        throw new UsernameNotFoundException("User Not Found");
    }
        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("CREATED");

        BigDecimal totalAmount = BigDecimal.ZERO;


        Orders savedOrder = orderRepository.save(order);

        for (Cart item : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());


            totalAmount = totalAmount.add(
                    item.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

            orderItemRepository.save(orderItem);
        }


        savedOrder.setTotalAmount(totalAmount);


        cartRepository.deleteAll(cartItems);


        return orderRepository.save(savedOrder);
    }

    public List<Orders>  getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
