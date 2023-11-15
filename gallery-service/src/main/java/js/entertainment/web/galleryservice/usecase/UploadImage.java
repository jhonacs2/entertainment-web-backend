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
    private final GalleryService galleryService;

    @Value("${image.director.path}")
    private String imagesPath;

    @Setter
    private MultipartFile image;

    private Gallery galleryEntity;

    public UploadImage(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

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
            File fi = Utils.convertMultiPartToFile(image);
            Files.copy(fi.toPath(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            createThumbnail();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void createThumbnail() {
        try {
            Path path = Paths.get(String.format("%s%s", imagesPath, galleryEntity.getUUId()));
            Path uploadPath = Path.of(path + "\\" + "h" + image.getOriginalFilename());
            BufferedImage imageMultiPart = ImageIO.read(new FileInputStream(Utils.convertMultiPartToFile(image)));
            BufferedImage thumbnailImage = Scalr.resize(imageMultiPart, 400);

            File thumbnailFile = new File(uploadPath.toUri());
            System.out.println(thumbnailFile);
            ImageIO.write(thumbnailImage, "png", thumbnailFile);
            imageMultiPart.flush();
            thumbnailImage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
