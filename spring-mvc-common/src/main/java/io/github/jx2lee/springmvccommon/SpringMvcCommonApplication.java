package io.github.jx2lee.springmvccommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcCommonApplication.class, args);
    }

    // @Bean
    // public MessageSource messageSource() {
    //     ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    //     messageSource.setBasenames("messages", "errors");
    //     messageSource.setDefaultEncoding("utf-8");
    //     return messageSource;
    // }
}
