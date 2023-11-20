package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.common.ThumbnailSize;
import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.exceptions.ImageRetrivalException;
import js.entertainment.web.galleryservice.service.ImageService;
import js.entertainment.web.galleryservice.utils.Command;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Scope("prototype")
@Log4j2
public class GetImage implements Command<Resource> {
    private final ImageService galleryService;

    @Value("${image.director.path}")
    private String imagesPath;

    @Setter
    private Long imageId;

    private Image imageEntity;

    public GetImage(ImageService galleryRepository) {
        this.galleryService = galleryRepository;
    }

    @Override
    public Resource execute() {
        imageEntity = galleryService.findById(imageId);
        return getImageFromPath();
    }

    private Resource getImageFromPath() {
        try {
            String pathImage = String.format(
                    "%s%s/%s.png",
                    imagesPath,
                    imageEntity.getUuid(),
                    ThumbnailSize.THUMBNAIL_SMALL);
            Path imagePath = Paths.get(pathImage);
            return new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            log.error("ERROR CREATING RESOURCE URL", e);
            throw new ImageRetrivalException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
        }
    }
}
