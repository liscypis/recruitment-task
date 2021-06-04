package com.lisowski.pms.api;

import com.lisowski.pms.payload.ProductRequestDTO;
import com.lisowski.pms.services.servicesImpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO request, @RequestParam("categoryId") String categoryId) {
        return ResponseEntity.ok(productService.createProduct(request, categoryId));
    }

    @PutMapping("/product")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductRequestDTO request,
                                         @RequestParam("productId") String productId,
                                         @RequestParam("categoryId") String categoryId) {
        productService.updateProduct(request, productId, categoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok().build();
    }


}
