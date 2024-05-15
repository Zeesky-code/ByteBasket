package com.estu.ByteBasket.cart;

import com.estu.ByteBasket.user.User;
import com.estu.ByteBasket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @GetMapping
    public Cart getCart(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return cartService.getCartByUserId(user.getId());
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam("productId") String productId, Authentication authentication) {
        String userId = authentication.getName();
        cartService.addItemToCart(userId, productId);
        return "profile";
    }

    @PutMapping("/update/{productId}")
    public Cart updateCartItem(@PathVariable String productId, @RequestParam int quantity, Authentication authentication) {
        String userId = authentication.getName();
        return cartService.updateCartItem(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{productId}")
    public Cart removeItemFromCart(@PathVariable String productId, Authentication authentication) {
        String userId = authentication.getName();
        return cartService.removeItemFromCart(userId, productId);
    }
}
