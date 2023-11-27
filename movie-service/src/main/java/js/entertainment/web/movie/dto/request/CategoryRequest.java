package js.entertainment.web.movie.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Category is Mandatory")
    private String category;
}
