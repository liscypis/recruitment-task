package com.lisowski.pms.api;

import com.lisowski.pms.payload.CategoryRequestDTO;
import com.lisowski.pms.services.servicesImpl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/category")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryRequestDTO request,
                                          @RequestParam("id") String id) {
        categoryService.updateCategory(request, id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping ("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/categories")
    public ResponseEntity<?> deleteAllCategories() {
        categoryService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
