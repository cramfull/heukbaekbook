package com.nhnacademy.heukbaekbookshop.image;

import com.nhnacademy.heukbaekbookshop.image.ImageManagerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageManagerConfig {

    @Bean
    @ConfigurationProperties(prefix = "cloud.nhn.image-manager")
    public ImageManagerProperties imageManagerProperties() {
        return new ImageManagerProperties();
    }
}
