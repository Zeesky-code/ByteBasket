package com.estu.ByteBasket.product;

import com.estu.ByteBasket.user.User;
import com.estu.ByteBasket.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySellerId(@PathVariable String sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct( @ModelAttribute Product product,
                              Authentication authentication) throws IOException {
        String username = authentication.getName();
        User seller = userRepository.findByUsername(username).orElseThrow();

        //product.setImage(product.getImage().getBytes()); // Convert image to byte array
        product.setSellerId(seller.getId());
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product,
                                                 Authentication authentication) throws IOException {
        String username = authentication.getName();
        User seller = userRepository.findByUsername(username).orElseThrow();
        product.setSellerId(seller.getId());

        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

