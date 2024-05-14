package com.estu.ByteBasket.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCart(Authentication authentication) {
        String userId = authentication.getName();
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody CartItem cartItem, Authentication authentication) {
        String userId = authentication.getName();
        return cartService.addItemToCart(userId, cartItem);
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
