package js.entertainment.web.movie.controller;

import js.entertainment.web.movie.dto.request.CategoryRequest;
import js.entertainment.web.movie.dto.response.CategoryResponse;
import js.entertainment.web.movie.usecase.CreateCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CreateCategory category;

    public CategoryController(CreateCategory category) {
        this.category = category;
    }

    @PostMapping(value = "/v1/category")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        category.setCategoryRequest(categoryRequest);
        return ResponseEntity.ok(category.execute());
    }
}
