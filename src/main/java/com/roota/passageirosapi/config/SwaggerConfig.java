package com.roota.passageirosapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("API de Passageiros")
                        .description("API REST desenvolvida com Spring Boot para cadastro de passageiros.")
                        .version("1.0")

                        .contact(new Contact()
                                .name("Daiana de Souza Alves")
                                .email("daianaalves.ctt@gmail.com")))

                .externalDocs(new ExternalDocumentation()
                        .description("Repositório GitHub")
                        .url("https://github.com/DaianaSouzaAlves/passageiros-api"));
    }
}
