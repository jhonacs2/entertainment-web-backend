package js.entertainment.web.galleryservice.service;

import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.repository.GalleryRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Image save(Image gallery) {
        return galleryRepository.save(gallery);
    }
}
