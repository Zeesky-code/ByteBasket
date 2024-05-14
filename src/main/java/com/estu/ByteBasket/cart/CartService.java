package com.estu.ByteBasket.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });
    }

    public Cart addItemToCart(String userId, CartItem cartItem) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart updateCartItem(String userId, String productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String userId, String productId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }
}
