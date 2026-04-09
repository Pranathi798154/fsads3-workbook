package com.klu.studentcrud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI studentCrudOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Student CRUD API Documentation")
                .description("Swagger/OpenAPI documentation for the FSAD Skill 16 full-stack student management system")
                .version("1.0.0")
                .contact(new Contact().name("Department of CSE, CS&IT, AI&DS"))
                .license(new License().name("Academic Use")));
    }
}
