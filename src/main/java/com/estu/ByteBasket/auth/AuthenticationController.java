package com.estu.ByteBasket.auth;

import com.estu.ByteBasket.cart.Cart;
import com.estu.ByteBasket.cart.CartItem;
import com.estu.ByteBasket.cart.CartRepository;
import com.estu.ByteBasket.cart.CartService;
import com.estu.ByteBasket.product.Product;
import com.estu.ByteBasket.product.ProductService;
import com.estu.ByteBasket.user.Role;
import com.estu.ByteBasket.user.User;
import com.estu.ByteBasket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
        @RequestParam("username") String username,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam("role") String role) {

        // Process the form data
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setEmail(email);
        request.setPassword(password);
        request.setRole(Role.valueOf(role));
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody AuthenticationRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + service.authenticate(request));
        headers.add("Location", "/profile"); // Redirect to profile page
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();

    }
}
