package com.example.loverbe.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JacksonMapper {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.serializerByType(Page.class, new JsonSerializer<Page<?>>() {
                    @Override
                    public void serialize(Page<?> page, JsonGenerator jsonGenerator, SerializerProvider serializers)
                            throws IOException {
                        Map<String, Object> content = new HashMap<>();
                        content.put("content", page.getContent());
                        content.put("total_elements", page.getTotalElements());
                        content.put("total_pages", page.getTotalPages());
                        content.put("size", page.getSize());
                        content.put("page", page.getNumber());
                        jsonGenerator.writeObject(content);
                    }
                });
            }
        };
    }
}