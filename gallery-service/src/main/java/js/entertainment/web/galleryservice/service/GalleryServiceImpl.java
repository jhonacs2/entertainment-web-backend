package js.entertainment.web.galleryservice.service;

import js.entertainment.web.galleryservice.domain.Gallery;
import js.entertainment.web.galleryservice.repository.GalleryRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Gallery save(Gallery gallery) {
        return galleryRepository.save(gallery);
    }
}
