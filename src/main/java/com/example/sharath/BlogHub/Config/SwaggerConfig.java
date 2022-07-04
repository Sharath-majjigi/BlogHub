package com.example.sharath.BlogHub.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("BlogHub:Backend Server","Server Created and Maintained By SHARATH MAJJIGI","1.0","Terms of Service","Sharath Majjigi",null,null);
    }
    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(null).build());
    }
    private List<SecurityReference> securityReferences(){

        AuthorizationScope scope=new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scope}));
    }

}
