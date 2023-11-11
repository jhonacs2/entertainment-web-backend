package js.entertainment.web.galleryservice.controller;

import js.entertainment.web.galleryservice.usecase.UploadImage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class GalleryController {
    private final UploadImage uploadImage;

    public GalleryController(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

    @PostMapping(value = "/v1/gallery/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        uploadImage.setImage(file);
        uploadImage.execute();
        return ResponseEntity.ok("");
    }
}
