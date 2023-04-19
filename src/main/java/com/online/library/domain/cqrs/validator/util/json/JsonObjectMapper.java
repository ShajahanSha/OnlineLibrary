package com.online.library.domain.cqrs.validator.util.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class JsonObjectMapper {
    public static final ThreadLocal<ObjectMapper> SAFE_OBJECT_MAPPER;

    static {
        SAFE_OBJECT_MAPPER = ThreadLocal.withInitial(() -> {
            ObjectMapper OBJECT_MAPPER = new ObjectMapper();

            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
            LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(formatter);
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
            javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

            OBJECT_MAPPER.registerModule(javaTimeModule);
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
            OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return OBJECT_MAPPER;
        });
    }
}
