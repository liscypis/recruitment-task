package com.lisowski.pms.services;

import com.lisowski.pms.dto.ProductResponseDTO;
import com.lisowski.pms.payload.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO, String categoryId);
    void deleteAllProducts();
    void deleteProduct(String productId);
    void updateProduct(ProductRequestDTO requestDTO, String productId, String categoryId);
    ProductResponseDTO getProduct(String productId);
    List<ProductResponseDTO> getProducts();
    List<ProductResponseDTO> getAvailableProducts();
    List<ProductResponseDTO> searchProduct(String name);
}
