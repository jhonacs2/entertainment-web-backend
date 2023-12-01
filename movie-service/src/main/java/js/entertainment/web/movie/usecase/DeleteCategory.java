package js.entertainment.web.movie.usecase;

import js.entertainment.web.movie.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Log4j2
public class DeleteCategory {
    private final CategoryService categoryService;

    public DeleteCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(Long categoryId) {
        try {
            var category = categoryService.findById(categoryId);
            categoryService.deleteById(category.getId());
        } catch (EntityNotFoundException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
