package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.dto.response.UploadImageResponse;
import js.entertainment.web.galleryservice.service.GalleryService;
import js.entertainment.web.galleryservice.utils.Command;
import js.entertainment.web.galleryservice.utils.Utils;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;


@Service
@Scope("prototype")
@Log4j2
public class UploadImage implements Command<UploadImageResponse> {
    public UploadImage(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Value("${image.director.path}")
    private String imagesPath;

    @Setter
    private MultipartFile image;

    private Image galleryEntity;

    private File imageFile;

    private final GalleryService galleryService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UploadImageResponse execute() throws IOException {
        galleryEntity = galleryService.save(buildGallery());
        createFolder();
        return buildUploadImageResponse();
    }

    private Image buildGallery() {
        var gallery = new Image();
        gallery.setImageName(image.getOriginalFilename());
        gallery.setUuid(UUID.randomUUID().toString());
        return gallery;
    }

    private UploadImageResponse buildUploadImageResponse() {
        return UploadImageResponse
                .builder()
                .imageid(galleryEntity.getId())
                .imageName(galleryEntity.getImageName())
                .imageUuid(UUID.fromString(galleryEntity.getUuid()))
                .build();
    }

    private void createFolder() throws IOException {
        try {
            Path path = Paths.get(String.format("%s%s", imagesPath, galleryEntity.getUuid()));
            Files.createDirectory(path);
            Path uploadPath = Path.of(path + "\\" + image.getOriginalFilename());
            imageFile = Utils.convertMultiPartToFile(image);
            Files.copy(imageFile.toPath(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            createThumbnail();
        } catch (FileAlreadyExistsException e) {
            log.error(e);
            throw e;
        } catch (AccessDeniedException e) {
            log.error(e);
            throw e;
        } catch (IOException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    private void createThumbnail() throws IOException {
        try (FileInputStream originalImageInputStream = new FileInputStream(imageFile)) {
            Path path = Paths.get(String.format("%s%s", imagesPath, galleryEntity.getUuid()));
            Path uploadPath = Path.of(path + "\\" + "thumbnailSmall.png");
            BufferedImage imageMultiPart = ImageIO.read(originalImageInputStream);
            BufferedImage thumbnailImage = Scalr.resize(imageMultiPart, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 400, Scalr.OP_ANTIALIAS);

            File thumbnailFile = new File(uploadPath.toUri());
            ImageIO.write(thumbnailImage, "png", thumbnailFile);
            imageMultiPart.flush();
            thumbnailImage.flush();
        } catch (IOException e) {
            log.error("ERROR CREATING THUMBNAIL", e);
            throw e;
        } finally {
            if (imageFile.delete()) {
                log.info("Temporal Image " + image.getOriginalFilename() + " deleted");
            } else {
                log.info("Temporal Image " + image.getOriginalFilename() + "was not deleted");

            }
        }
    }
}
