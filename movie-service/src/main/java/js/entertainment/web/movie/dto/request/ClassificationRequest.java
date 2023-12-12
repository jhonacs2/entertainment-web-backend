package js.entertainment.web.movie.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClassificationRequest {
    @NotBlank(message = "Classification is Mandatory")
    private String classification;
}
