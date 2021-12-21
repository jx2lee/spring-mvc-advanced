package io.github.jx2lee.springmvctypeconverter.converter;

import io.github.jx2lee.springmvctypeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        // IpPort 객체 -> String 반환
        log.info("convert source: {}", source);
        return source.getIp() + ":" + source.getPort();
    }
}
