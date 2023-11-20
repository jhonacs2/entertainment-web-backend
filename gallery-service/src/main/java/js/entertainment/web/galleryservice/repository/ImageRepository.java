package js.entertainment.web.galleryservice.repository;

import js.entertainment.web.galleryservice.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
