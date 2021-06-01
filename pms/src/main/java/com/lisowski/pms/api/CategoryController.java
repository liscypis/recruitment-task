package com.lisowski.pms.api;

import com.lisowski.pms.payload.CategoryRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CategoryController {

    @GetMapping ("/categories")
    public ResponseEntity<?> getCategories() {
        return null;
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO request) {
        return null;
    }

    @PutMapping ("/category/{id}")
    public ResponseEntity<?> editCategory(@RequestBody CategoryRequestDTO request, @PathVariable String id) {
        return null;
    }

    @DeleteMapping ("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        return null;
    }


}
