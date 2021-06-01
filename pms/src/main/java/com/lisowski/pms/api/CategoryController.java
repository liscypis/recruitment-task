package com.lisowski.pms.api;

import com.lisowski.pms.payload.CategoryRequestDTO;
import com.lisowski.pms.services.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl cs) {
        this.categoryService = cs;
    }

    @GetMapping ("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping ("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping ("/category")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryRequestDTO request,
                                          @RequestParam("id") String id) {
        categoryService.updateCategory(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/categories")
    public ResponseEntity<?> deleteAllCategories() {
        categoryService.deleteAll();
        return ResponseEntity.ok().build();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
