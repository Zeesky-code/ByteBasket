package com.estu.ByteBasket.product;

import com.estu.ByteBasket.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(){
        return ResponseEntity.ok("Testing");
    }
}
