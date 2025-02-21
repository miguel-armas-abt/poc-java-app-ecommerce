package com.demo.poc.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonFileReader {

    public static <T> T readObjectFromFile(String filePath, TypeReference<T> typeReference) {
        try {
            InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath);
            return new ObjectMapper().readValue(inputStream, typeReference);
        } catch (IOException ioException) {
            throw new RuntimeException("error reading json file: " + ioException.getMessage(), ioException);
        }
    }

    public static <T> List<T> readListFromFile(String filePath, TypeReference<List<T>> typeReference) {
        try {
            InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath);
            return new ObjectMapper().readValue(inputStream, typeReference);
        } catch (IOException ioException) {
            throw new RuntimeException("error reading json file: " + ioException.getMessage(), ioException);
        }
    }
}
