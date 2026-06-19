package com.hardik.ecom_project.Controller;

import com.hardik.ecom_project.Service.CartService;
import com.hardik.ecom_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
public class CartContoller {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addToCart(@RequestParam Long productId,
                                       @RequestParam int quantity,
                                       Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCart(Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateCart(@RequestParam Long productId,
                                        @RequestParam int quantity,
                                        Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        return ResponseEntity.ok(cartService.updateCart(userId, productId, quantity));
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> removeFromCart(@RequestParam Long productId,
                                            Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        cartService.removeFromCart(userId, productId);

        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> clearCart(Principal principal) {

        Long userId = userService
                .getUserByUsername(principal.getName())
                .getId();

        cartService.clearCart(userId);

        return ResponseEntity.ok("Cart cleared");
    }
}
