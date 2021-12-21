package io.github.jx2lee.springmvctypeconverter.config;

import io.github.jx2lee.springmvctypeconverter.converter.IntegerToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.IpPortToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIntegerConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
    }
}
