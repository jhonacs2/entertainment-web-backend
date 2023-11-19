package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.service.GalleryService;
import js.entertainment.web.galleryservice.utils.Command;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Log4j2
public class GetImage implements Command<Image> {
    private final GalleryService galleryService;

    @Setter
    private Long imageId;

    public GetImage(GalleryService galleryRepository) {
        this.galleryService = galleryRepository;
    }

    @Override
    public Image execute() {
        return galleryService.findById(imageId);
    }
}
