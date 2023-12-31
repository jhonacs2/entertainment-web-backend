package js.entertainment.web.galleryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import js.entertainment.web.galleryservice.dto.response.UploadImageResponse;
import js.entertainment.web.galleryservice.usecase.GetImage;
import js.entertainment.web.galleryservice.usecase.UploadImage;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageController {
    private final UploadImage uploadImage;
    private final GetImage getImageThumbnail;

    public ImageController(UploadImage uploadImage, GetImage getImage) {
        this.uploadImage = uploadImage;
        this.getImageThumbnail = getImage;
    }

    @Operation(summary = "Upload Image")
    @PostMapping(value = "/v1/gallery/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        uploadImage.setImage(file);
        return ResponseEntity.ok(uploadImage.execute());
    }

    @Operation(summary = "Get Image Thumbnail by imageId")
    @GetMapping(value = "/v1/gallery/{imageId}/thumbnail")
    public ResponseEntity<Resource> getImage(@PathVariable("imageId") Long imageId) {
        getImageThumbnail.setImageId(imageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(getImageThumbnail.execute());
    }
}
