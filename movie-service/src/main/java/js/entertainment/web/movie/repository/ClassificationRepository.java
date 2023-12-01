package js.entertainment.web.movie.repository;

import js.entertainment.web.movie.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
}
