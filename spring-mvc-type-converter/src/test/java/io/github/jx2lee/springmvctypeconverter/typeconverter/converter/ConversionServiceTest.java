package io.github.jx2lee.springmvctypeconverter.typeconverter.converter;

import io.github.jx2lee.springmvctypeconverter.converter.IntegerToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.IpPortToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIntegerConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIpPortConverter;
import io.github.jx2lee.springmvctypeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        String ipPortString = conversionService.convert(ipPort, String.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");



    }
}
