package io.github.jx2lee.springmvctypeconverter.formatter;

import io.github.jx2lee.springmvctypeconverter.converter.IpPortToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIpPortConverter;
import io.github.jx2lee.springmvctypeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionServiceTest() {
        // given
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        // add converter
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        // add formatter
        conversionService.addFormatter(new MyNumberFormatter());

        // when
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        String resultString = conversionService.convert(1000, String.class);
        Long resultLong = conversionService.convert("1,000", Long.class);

        // then
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(resultString).isEqualTo("1,000");
        assertThat(resultLong).isEqualTo(1000L);
    }
}
