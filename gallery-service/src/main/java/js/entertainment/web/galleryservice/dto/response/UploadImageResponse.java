package js.entertainment.web.galleryservice.dto.response;


import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageResponse {
    private Long imageId;
    private UUID imageUuid;
    private String imageName;
}
