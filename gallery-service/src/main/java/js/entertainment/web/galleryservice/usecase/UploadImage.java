package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.domain.Gallery;
import js.entertainment.web.galleryservice.service.GalleryService;
import js.entertainment.web.galleryservice.utils.Command;
import js.entertainment.web.galleryservice.utils.Utils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@Scope("prototype")
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
