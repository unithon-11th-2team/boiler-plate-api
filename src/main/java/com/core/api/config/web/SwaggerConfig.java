package com.core.api.config.web;

import com.core.api.auth.AuthUser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.core.api.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @PostConstruct
    public void init() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(AuthUser.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        AUTH_TOKEN_HEADER_KEY,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.APIKEY)
                                                .in(SecurityScheme.In.HEADER)
                                                .name(AUTH_TOKEN_HEADER_KEY)
                                                .description("인증이 필요한 경우 ex) X-CORE-AUTH-TOKEN xxxxxxx")
                                )
                )
                .security(List.of(new SecurityRequirement().addList(AUTH_TOKEN_HEADER_KEY)))
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info().title("극락 API").description("극락가자..");
    }
}
