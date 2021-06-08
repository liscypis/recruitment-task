package com.lisowski.pms.services.servicesImpl;

import com.lisowski.pms.dto.ProductResponseDTO;
import com.lisowski.pms.entity.Category;
import com.lisowski.pms.entity.Product;
import com.lisowski.pms.payload.ProductRequestDTO;
import com.lisowski.pms.repository.CategoryRepository;
import com.lisowski.pms.repository.ProductRepository;
import com.lisowski.pms.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid categoryId"));

        Product product = modelMapper.map(requestDTO, Product.class);
        if (productRepository.findByProductName(product.getProductName()).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product name is taken");

        product.updateGrossPrice();
        product.setCategory(category);

        return modelMapper.map(productRepository.save(product), ProductResponseDTO.class);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid productId"));
        productRepository.delete(product);
    }

    @Override
    public void updateProduct(ProductRequestDTO requestDTO, String productId, String categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid productId"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid categoryId"));
        productRepository.findByProductName(requestDTO.getProductName())
                .ifPresent(p -> {
                    if (!p.equals(product))
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Product name is taken");
                });

            modelMapper.map(requestDTO, product);

            product.setCategory(category);
            product.updateGrossPrice();
            System.out.println(product);
            productRepository.save(product);
    }

    @Override
    public ProductResponseDTO getProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid productId"));
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getAvailableProducts() {
        return productRepository.findByAvailable(true).stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }
}
