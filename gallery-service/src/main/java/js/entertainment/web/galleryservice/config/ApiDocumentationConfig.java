package js.entertainment.web.galleryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {
   @Value("${build.version}")
   private String projectVersion;

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gallery Service")
                        .description("This gallery service provides an example API for routing and managing images. It enables uploading, downloading, and organizing images within the gallery. Additionally, it offers functionalities such as tagging, searching, and sorting images.")
                        .version(projectVersion));

    }
}
