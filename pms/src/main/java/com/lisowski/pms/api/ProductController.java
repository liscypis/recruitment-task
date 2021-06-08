package com.lisowski.pms.api;

import com.lisowski.pms.payload.ProductRequestDTO;
import com.lisowski.pms.services.servicesImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO request, @RequestParam("categoryId") String categoryId) {
        return ResponseEntity.ok(productService.createProduct(request, categoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/product")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductRequestDTO request,
                                         @RequestParam("productId") String productId,
                                         @RequestParam("categoryId") String categoryId) {
        productService.updateProduct(request, productId, categoryId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/availableProducts")
    public ResponseEntity<?> getAvailableProducts() {
        return ResponseEntity.ok(productService.getAvailableProducts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products")
    public ResponseEntity<?> deleteProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok().build();
    }


}
