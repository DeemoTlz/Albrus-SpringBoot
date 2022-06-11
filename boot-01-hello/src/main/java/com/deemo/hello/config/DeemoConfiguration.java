package com.deemo.hello.config;

import com.deemo.hello.bean.Cat;
import com.deemo.hello.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

@Configuration
public class DeemoConfiguration {

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * 开启矩阵变量
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /**
             * 添加自定义 Converter
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Dog>() {
                    @Override
                    public Dog convert(String source) {
                        String[] values = source.split(",");
                        Dog dog = new Dog();
                        dog.setId(Long.valueOf(values[0]));
                        dog.setName(values[1]);
                        return dog;
                    }
                });
            }

            /**
             * 添加自定义 HttpMessageConverter
             */
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                // converters.add(new AlbrusHttpMessageConverter());
            }
        };
    }

}
