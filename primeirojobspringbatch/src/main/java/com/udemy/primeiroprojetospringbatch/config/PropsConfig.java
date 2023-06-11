package com.udemy.primeiroprojetospringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {


    @Bean
    public PropertySourcesPlaceholderConfigurer config() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        //Prod:
        //configurer.setLocation(new FileSystemResource("/etc/config/primeirojobspringbatch/application.properties"));
        //Local (WSL):
        configurer.setLocation(new FileSystemResource("//wsl.localhost/Ubuntu/etc/config/primeirojobspringbatch/application.properties"));
        return configurer;
    }
}
