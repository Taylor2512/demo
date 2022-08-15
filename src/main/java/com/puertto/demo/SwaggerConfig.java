package com.puertto.demo;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc;

import javax.validation.constraints.Email;

import org.springdoc.core.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.*;
import org.springframework.web.filter.*;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")

                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("/admin/**")
                // .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))

                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {

        io.swagger.v3.oas.models.info.Contact Contact = new io.swagger.v3.oas.models.info.Contact();
        Contact.email("jhonmontenegr2512@gmail.com");
        Contact.url("www.google.com");
        Contact.name("Aplicacion template");
        return new OpenAPI().components(new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info().title("Api Taylor")
                        .description("Plantilla de Swagger")
                        .contact(Contact)
                        .version("v2.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentacion Spring Boot")

                        .url("https://springshop.wiki.github.org/docs"));
    }

    // @Bean
    // public OpenAPI customOpenAPI() {
    // return new OpenAPI()
    // .components(new Components()
    // .addSecuritySchemes("bearer-key",
    // new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
    // .bearerFormat("JWT")));
    // }

    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

}
