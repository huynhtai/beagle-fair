package ch.smartlinksa.intern.business.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {}

    private static final ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        return mapper;
    }

    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = getObjectMapper();
        try {
            mapper.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

    }

    public static <T> T convertJsonToObject(String json, Class<T> className) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, className);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> T convertJsonToObject(String json, TypeReference<T> type) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, type);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> List<T> convertJsonToCollection(String json, Class<T> className) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CollectionType ct = objectMapper.getTypeFactory().constructCollectionType(List.class, className);
            return objectMapper.readValue(json, ct);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T,O> T convertJsonToObject(String json,Class<T> genericClass, Class<O> bodyClass){
        ObjectMapper mapper = getObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(genericClass,bodyClass);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
