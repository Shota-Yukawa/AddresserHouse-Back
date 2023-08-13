package com.ah.residence.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static <T> T deserializeJson(Object jsonObject, Class<T> targetClass) {
        return objectMapper.convertValue(jsonObject, targetClass);
    }
}
