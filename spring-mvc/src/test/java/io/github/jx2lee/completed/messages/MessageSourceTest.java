package io.github.jx2lee.completed.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage() {
        //given
        String result = messageSource.getMessage("hello", null, null);

        //when
        //then
        assertThat(result).isEqualTo("안녕");
    }
    
    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("ttt", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = messageSource.getMessage("tttt", null, "test", null);
        assertThat(result).isEqualTo("test");
    }

    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[]{"jx2lee"}, null);
        assertThat(result).isEqualTo("안녕 jx2lee");
    }

    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        
    }
    
    @Test
    void enLang() {
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

}
