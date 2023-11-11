package js.entertainment.web.galleryservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageReq {
    MultipartFile image;
}
