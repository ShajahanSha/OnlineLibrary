package com.online.library.domain.cqrs.validator.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;

public class JsonSerializer<T>
{
    ObjectMapper mapper = new ObjectMapper();

    private ObjectMapper mapperInstance ()
    {
        mapper.registerModule(new JodaModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public T deserialize (String json, Class<T> classType) throws IOException
    {
        return this.mapperInstance().readValue(json, classType);
    }

    public String serialize (T obj) throws JsonProcessingException
    {
        return this.mapperInstance().writeValueAsString(obj);
    }
}
