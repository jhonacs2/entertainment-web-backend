package js.entertainment.web.galleryservice.repository;

import js.entertainment.web.galleryservice.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
