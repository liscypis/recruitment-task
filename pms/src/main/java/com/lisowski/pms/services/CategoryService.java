package com.lisowski.pms.services;

import com.lisowski.pms.dto.CategoryResponseDTO;
import com.lisowski.pms.payload.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO request);
    void deleteCategory(String id);
    void deleteAll();
    void updateCategory(CategoryRequestDTO request, String id);
    List<CategoryResponseDTO> getCategories();
    CategoryResponseDTO getCategory(String id);
}
