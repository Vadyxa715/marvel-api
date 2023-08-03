package com.kurdev.marvel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kurdev"})
//@EnableSwagger2
//@EnableWebMvc
public class MarvelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarvelApplication.class, args);
    }
//    public Docket apis() {
////        return new Docket(DocumentationType.SWAGGER_2);
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.kurdev")).build();
//    }
}
