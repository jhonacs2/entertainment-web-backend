package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.domain.Gallery;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@Scope("prototype")
@Log4j2
public class UploadImage implements Command<String> {
    public UploadImage(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Value("${image.director.path}")
    private String imagesPath;

    @Setter
    private MultipartFile image;

    private Gallery galleryEntity;

    private File imageFile;

    private final GalleryService galleryService;

    @Override
    public String execute() {
        galleryEntity = galleryService.save(buildGallery());
        createFolder();
        return null;
    }

    private Gallery buildGallery() {
        var gallery = new Gallery();
        gallery.setImageName(image.getOriginalFilename());
        gallery.setUUId(UUID.randomUUID().toString());
        return gallery;
    }

    private void createFolder() {
        try {
            Path path = Paths.get(String.format("%s%s", imagesPath, galleryEntity.getUUId()));
            Files.createDirectory(path);
            Path uploadPath = Path.of(path + "\\" + image.getOriginalFilename());
            imageFile = Utils.convertMultiPartToFile(image);
            Files.copy(imageFile.toPath(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            createThumbnail();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void createThumbnail() {
        try (FileInputStream originalImageInputStream = new FileInputStream(imageFile)) {
            Path path = Paths.get(String.format("%s%s", imagesPath, galleryEntity.getUUId()));
            Path uploadPath = Path.of(path + "\\" + "thumbnailSmall.png");
            BufferedImage imageMultiPart = ImageIO.read(originalImageInputStream);
            BufferedImage thumbnailImage = Scalr.resize(imageMultiPart, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 400, Scalr.OP_ANTIALIAS);

            File thumbnailFile = new File(uploadPath.toUri());
            ImageIO.write(thumbnailImage, "png", thumbnailFile);
            imageMultiPart.flush();
            thumbnailImage.flush();
        } catch (IOException e) {
            log.error(e);
        } finally {
            if (imageFile.delete()) {
                log.info("Temporal Image " + image.getOriginalFilename() + " deleted");
            } else {
                log.info("Temporal Image " + image.getOriginalFilename() + "was not deleted");

            }
        }
    }
}
