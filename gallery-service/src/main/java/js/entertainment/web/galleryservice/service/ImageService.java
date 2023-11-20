package js.entertainment.web.galleryservice.service;

import js.entertainment.web.galleryservice.domain.Image;

public interface ImageService {
    Image save(Image gallery);

    Image findById(Long id);
}
