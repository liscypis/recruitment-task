package com.lisowski.pms.services.servicesImpl;

import com.lisowski.pms.dto.CategoryResponseDTO;
import com.lisowski.pms.entity.Category;
import com.lisowski.pms.entity.Product;
import com.lisowski.pms.payload.CategoryRequestDTO;
import com.lisowski.pms.repository.CategoryRepository;
import com.lisowski.pms.repository.ProductRepository;
import com.lisowski.pms.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        checkCategoryNameIsUsed(request);

        Category category = modelMapper.map(request, Category.class);
        System.out.println(category);
        return modelMapper.map(categoryRepository.save(category),CategoryResponseDTO.class);
    }


    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid id"));

        deleteCategoryFromProducts(category);

        categoryRepository.delete(category);
    }



    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
        List<Product> productList = productRepository.findAll();
        for (Product p: productList){
            p.setCategory(null);
            productRepository.save(p);
        }
    }

    @Override
    public void updateCategory(CategoryRequestDTO request, String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid id"));
        checkCategoryNameIsUsed(request);

        category.setName(request.getName());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(cat -> modelMapper.map(cat, CategoryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid id"));
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    private void checkCategoryNameIsUsed(CategoryRequestDTO request) {
        if(categoryRepository.findCategoryByName(request.getName()).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "The name is already in use.");
    }

    private void deleteCategoryFromProducts(Category category) {
        List<Product> productList = productRepository.findByCategory(category);
        if(!productList.isEmpty()){
            for (Product p : productList){
                p.setCategory(null);
                productRepository.save(p);
            }
        }
    }
}
