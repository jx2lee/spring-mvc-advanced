package io.github.jx2lee.springmvctypeconverter.typeconverter.converter;


import io.github.jx2lee.springmvctypeconverter.converter.IntegerToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.IpPortToStringConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIntegerConverter;
import io.github.jx2lee.springmvctypeconverter.converter.StringToIpPortConverter;
import io.github.jx2lee.springmvctypeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter stringToIpPortConverter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = stringToIpPortConverter.convert(source);
        Assertions.assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter ipPortToStringConverter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = ipPortToStringConverter.convert(source);
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");
    }
}
