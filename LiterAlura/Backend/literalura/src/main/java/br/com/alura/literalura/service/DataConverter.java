package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> dataClass) {
        try {
            return mapper.readValue(json,dataClass);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
