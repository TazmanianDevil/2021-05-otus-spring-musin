package ru.otus.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreFeignConfiguration {

    @Bean
    public ErrorDecoder genreErrorDecoder() {
        return new GenreFeignErrorDecoder();
    }

}