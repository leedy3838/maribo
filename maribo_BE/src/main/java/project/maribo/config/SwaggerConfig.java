package project.maribo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Maribo API",
                description = "게시판 서비스 API 명세서",
                version = "1.0.0"))
@Configuration
public class SwaggerConfig {
}
