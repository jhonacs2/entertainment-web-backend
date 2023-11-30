package js.entertainment.web.movie.controller;

import js.entertainment.web.movie.dto.request.CategoryRequest;
import js.entertainment.web.movie.dto.response.CategoryResponse;
import js.entertainment.web.movie.usecase.CreateCategory;
import js.entertainment.web.movie.usecase.DeleteCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CreateCategory category;
    private final DeleteCategory deleteCategory;

    public CategoryController(CreateCategory category, DeleteCategory deleteCategory) {
        this.category = category;
        this.deleteCategory = deleteCategory;
    }

    @PostMapping(value = "/v1/category")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        category.setCategoryRequest(categoryRequest);
        return ResponseEntity.ok(category.execute());
    }

    @DeleteMapping(value = "/v1/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        deleteCategory.execute(id);
        return ResponseEntity.noContent().build();
    }
}
