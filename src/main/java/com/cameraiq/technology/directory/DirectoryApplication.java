package com.cameraiq.technology.directory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan("com.cameraiq.technology.directory.Mapper")
public class DirectoryApplication extends  SpringBootServletInitializer{

    public static void main(String [] args){
        SpringApplication.run(DirectoryApplication.class);}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DirectoryApplication.class);
    }
}

