package com.jianghuling.lostandfound.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

import static com.jianghuling.lostandfound.Constant.UPLOAD_IMG_LOC;

@Configuration
public class FileRscConfig implements WebMvcConfigurer {
    ;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/lostimages/**").addResourceLocations("/home/jianghuling/lostandfound/itemimages");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("5MB");
        factory.setLocation(UPLOAD_IMG_LOC);
        //设置总上传数据总大小
        factory.setMaxRequestSize("1000MB");
        return factory.createMultipartConfig();
    }
}
