package com.atlasculinary.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Tên cơ chế bảo mật mà Swagger sẽ nhận dạng
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // 1. Cấu hình tiêu đề/phiên bản
                .info(new Info().title("Atlas Culinary API").version("v1.0"))

                // 2. Thêm yêu cầu bảo mật: tất cả các API đều yêu cầu "bearerAuth"
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // 3. Định nghĩa cơ chế bảo mật "bearerAuth"
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP) // Loại HTTP (Header)
                                .scheme("bearer") // Tiền tố
                                .bearerFormat("JWT") // Định dạng
                                .description("Nhập JWT Token (Bearer token) của bạn vào đây")));
    }
}