package js.entertainment.web.galleryservice.controller;

import js.entertainment.web.galleryservice.domain.Image;
import js.entertainment.web.galleryservice.dto.response.UploadImageResponse;
import js.entertainment.web.galleryservice.usecase.GetImage;
import js.entertainment.web.galleryservice.usecase.UploadImage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class GalleryController {
    private final UploadImage uploadImage;
    private final GetImage getImageThumbnail;

    public GalleryController(UploadImage uploadImage, GetImage getImage) {
        this.uploadImage = uploadImage;
        this.getImageThumbnail = getImage;
    }

    @PostMapping(value = "/v1/gallery/upload")
    public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        uploadImage.setImage(file);
        return ResponseEntity.ok(uploadImage.execute());
    }

    @GetMapping(value = "/v1/gallery/{imageId}/thumbnail")
    public ResponseEntity<Image> getImage(@PathVariable("imageId") Long imageId) {
        getImageThumbnail.setImageId(imageId);
        return ResponseEntity.ok(getImageThumbnail.execute());
    }
}
