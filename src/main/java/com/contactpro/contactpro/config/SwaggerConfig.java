package com.contactpro.contactpro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ContractPro API")
                        .version("1.0.0")
                        .description("Documentação da API do sistema de gestão de contratos corporativos"));
    }
}
