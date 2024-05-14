package com.estu.ByteBasket.user;

import com.estu.ByteBasket.auth.AuthenticationService;
import com.estu.ByteBasket.cart.Cart;
import com.estu.ByteBasket.cart.CartItem;
import com.estu.ByteBasket.cart.CartRepository;
import com.estu.ByteBasket.product.Product;
import com.estu.ByteBasket.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService service;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @GetMapping("/profile")
    public String profilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            // Handle user not found
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        boolean isAdmin = user.getRole().equals(Role.ADMIN);
        model.addAttribute("isAdmin", isAdmin);
        if (isAdmin) {
            List<Product> products = productService.getProductsBySellerId(user.getId());
            model.addAttribute("products", products);
        }
        List<CartItem> cartItems = cartRepository.findByUserId(user.getId()).orElse(new Cart()).getItems();
        model.addAttribute("cartItems", cartItems);
        return "profile";
    }
}
