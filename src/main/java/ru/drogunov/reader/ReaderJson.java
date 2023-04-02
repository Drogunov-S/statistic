package ru.drogunov.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.drogunov.entity.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderJson/*<T>*/ implements Reader/*<T>*/ {
    private Class<?> clazz;
    private final String filePath;
    private final ObjectMapper objectMapper;
    private final JsonNode jsonNode;

    //TODO Подумать что сделать с ошибками
    public ReaderJson(String filePath, ObjectMapper objectMapper) throws IOException {
//        this.clazz = (Class<T>) clazz.getClass();
        this.objectMapper = objectMapper;
        this.filePath = filePath;
        this.jsonNode = objectMapper.readTree(new BufferedReader(new FileReader(filePath)));
    }

    @Override
    public int getSize() {
        return jsonNode.size();
    }

    @Override
    public Element get(int index) {
        try {
            return objectMapper.readValue(jsonNode.get(index).traverse(), Element.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public JsonNode getJsonNode(int index) {
//    jsonNode.
        return jsonNode.get(index);
    }


}
