package js.entertainment.web.galleryservice.dto.response;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UploadImageResponse {
    private UUID id;
    private String imageName;
}
