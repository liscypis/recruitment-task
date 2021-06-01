package com.lisowski.pms.services;

import com.lisowski.pms.dto.CategoryResponseDTO;
import com.lisowski.pms.entity.Category;
import com.lisowski.pms.payload.CategoryRequestDTO;
import com.lisowski.pms.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository cr, ModelMapper mm) {
        this.categoryRepository = cr;
        this.modelMapper = mm;
    }

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
        categoryRepository.delete(category);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
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
                .map(user -> modelMapper.map(user, CategoryResponseDTO.class))
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
}
