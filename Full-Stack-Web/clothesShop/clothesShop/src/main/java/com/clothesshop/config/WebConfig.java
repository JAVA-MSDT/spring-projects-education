package com.clothesshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${clothes.images.folder}")
    private String clothesImagesFolder;

    @Value("${users.images.folder}")
    private String userImagesFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/clothes/**", "/images/users/**")
                .addResourceLocations("file:" + clothesImagesFolder, "file:" + userImagesFolder);
    }
}
