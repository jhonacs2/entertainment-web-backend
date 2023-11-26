package js.entertainment.web.movie.service;

import js.entertainment.web.movie.domain.Category;

public interface CategoryService {
    Category save(Category category);

    void deleteById(Long id);
}
