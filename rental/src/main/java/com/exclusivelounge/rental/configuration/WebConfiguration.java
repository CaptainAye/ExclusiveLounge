package com.exclusivelounge.rental.configuration;

import com.exclusivelounge.rental.converters.AssetTypeToStringConverter;
import com.exclusivelounge.rental.converters.StringToAssetTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer{
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToAssetTypeConverter());
        registry.addConverter(new AssetTypeToStringConverter());

    }
}
