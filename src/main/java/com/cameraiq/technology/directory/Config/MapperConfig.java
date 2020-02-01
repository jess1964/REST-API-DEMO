package com.cameraiq.technology.directory.Config;

import com.cameraiq.technology.directory.Annotations.MapperLink;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@AutoConfigureAfter({DBConfig.class})
@PropertySource("classpath:application.properties") // just in case i need to get something extra
public class MapperConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer1() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("DirectorySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.cameraiq.technology.directory.Mapper");
        mapperScannerConfigurer.setAnnotationClass(MapperLink.class);
        return mapperScannerConfigurer;
    }
}
