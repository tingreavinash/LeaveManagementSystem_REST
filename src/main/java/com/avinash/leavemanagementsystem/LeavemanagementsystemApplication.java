package com.avinash.leavemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LeavemanagementsystemApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(LeavemanagementsystemApplication.class, args);

    }
	
/*
	@Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
     return new OpenAPI()
          .info(new Info()
          .title("REST Server - Leave Management System")
          .version(appVersion)
          .description(appDesciption)
          .termsOfService("http://swagger.io/terms/")
          .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
*/


}
