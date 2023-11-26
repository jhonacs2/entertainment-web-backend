package js.entertainment.web.movie.repository;

import js.entertainment.web.movie.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteById(Long id);
}
