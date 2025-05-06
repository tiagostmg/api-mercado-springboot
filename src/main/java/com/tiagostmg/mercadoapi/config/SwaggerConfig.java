package com.tiagostmg.mercadoapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI mercadoOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mercado API")
                        .description("API para sistema de mercado")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do GitHub")
                        .url("https://github.com/tiagostmg/api-mercado-springboot"));
    }
}