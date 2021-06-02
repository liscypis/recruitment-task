package com.lisowski.pms.api;

import com.lisowski.pms.payload.ProductRequestDTO;
import com.lisowski.pms.services.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
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


}
