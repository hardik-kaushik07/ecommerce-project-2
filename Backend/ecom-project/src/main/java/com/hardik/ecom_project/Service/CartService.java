package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.Cart;
import com.hardik.ecom_project.Model.Product;
import com.hardik.ecom_project.Model.Users;
import com.hardik.ecom_project.Repository.CartRepository;
import com.hardik.ecom_project.Repository.ProductRepository;
import com.hardik.ecom_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart addToCart(Long userId, Long productId, int quantity) {

        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }


        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }


        Cart existing = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return cartRepository.save(existing);
        }


        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {

        return cartRepository.findByUserId(userId);
    }


    public Cart updateCart(Long userId, Long productId, int quantity) {

        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);

        if (cart == null) {
            throw new RuntimeException("Cart item not found");
        }

        Product product = cart.getProduct();

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public void removeFromCart(Long userId, Long productId) {

        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);

        if (cart == null) {
            throw new RuntimeException("Cart item not found");
        }

        cartRepository.delete(cart);
    }

    public void clearCart(Long userId) {

        List<Cart> cartItems = cartRepository.findByUserId(userId);

        cartRepository.deleteAll(cartItems);
    }
}
