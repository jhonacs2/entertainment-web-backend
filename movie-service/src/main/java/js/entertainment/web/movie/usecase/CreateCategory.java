package js.entertainment.web.movie.usecase;

import js.entertainment.web.movie.domain.Category;
import js.entertainment.web.movie.dto.request.CategoryRequest;
import js.entertainment.web.movie.dto.response.CategoryResponse;
import js.entertainment.web.movie.exceptions.DuplicateEntryException;
import js.entertainment.web.movie.service.CategoryService;
import js.entertainment.web.movie.utils.Command;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Log4j2
public class CreateCategory implements Command<CategoryResponse> {
    private final CategoryService categoryService;

    @Setter
    private CategoryRequest categoryRequest;

    public CreateCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryResponse execute() {
        try {
            var category = categoryService.save(buildCategory());
            return buildCategoryResponse(category);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new DuplicateEntryException("Duplicate Entry Movie", HttpStatus.CONFLICT);
        }
    }

    private Category buildCategory() {
        var category = new Category();
        category.setCategory(categoryRequest.getCategory());
        return category;
    }

    private CategoryResponse buildCategoryResponse(Category category) {
        var categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setCategory(category.getCategory());
        return categoryResponse;
    }
}
