package com.anderson.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket estoqueApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.anderson.estoque"))
                .paths(regex("/produto.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaInfo());
    }

    public ApiInfo metaInfo(){

        Contact contato = new Contact("Anderson", "https://www.linkedin.com/in/anderson-correia", "anderson_souza33@outlook.com.br");

        return new ApiInfoBuilder()
                .title("Estoque API REST")
                .description("com essa api você poderá realizar operações básicas de um estoque")
                .version("1.0")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contato)
                .build();
    }
}
