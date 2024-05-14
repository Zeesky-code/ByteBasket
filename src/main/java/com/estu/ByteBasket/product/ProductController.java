package com.estu.ByteBasket.product;

import com.estu.ByteBasket.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createProduct(){
        return ResponseEntity.ok("Testing");
    }
    @GetMapping("/")
    public ResponseEntity<String> getAllProducts(){
        return ResponseEntity.ok("Testing list of products");
    }
}
