package js.entertainment.web.galleryservice.usecase;

import js.entertainment.web.galleryservice.domain.Gallery;
import js.entertainment.web.galleryservice.service.GalleryService;
import js.entertainment.web.galleryservice.utils.Command;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Scope("prototype")
public class UploadImage implements Command<String> {
    private final GalleryService galleryService;

    @Value("${image.director.path}")
    private String imagesPath;

    @Setter
    private MultipartFile image;

    public UploadImage(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @Override
    public String execute() {
        var gallery = galleryService.save(buildGallery());
//        TODO: Create new Directory with UUID
//        try {
//            Path path = Paths.get(imagesPath + "/fess");
//            Files.createDirectory(path);
//            System.out.println("Directory is created!");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return null;
    }

    private Gallery buildGallery() {
        var gallery = new Gallery();
        gallery.setImageName(image.getOriginalFilename());
        gallery.setUUId(UUID.randomUUID().toString());
        return gallery;
    }
}
