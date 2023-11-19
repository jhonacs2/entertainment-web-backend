package js.entertainment.web.galleryservice.service;

import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    @Override
    public Image findById(Long id) {
        return galleryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("IMAGE NOT FOUND"));
    }
}
