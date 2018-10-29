package com.zhefan.yummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@ServletComponentScan
@SpringBootApplication
public class HotelApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HotelApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

}
