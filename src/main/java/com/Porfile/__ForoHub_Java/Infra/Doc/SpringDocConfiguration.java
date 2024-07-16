package com.Porfile.__ForoHub_Java.Infra.Doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
     return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                                    new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
            .info(new Info()
                        .title("API FORO HUB")
                        .description("API Rest de la aplicación FORO HUB, que contiene las funcionalidades de CRUD de TOPICOS, RESPUESTA, CURSOS Y USUARIOS, así como programación y cancelación de consultas.")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("backend@voll.med"))
            .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia")));  }

public void message(){
        System.out.println("Baerer esta trabajando.... ");
}
}
