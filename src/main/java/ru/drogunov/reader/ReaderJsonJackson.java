package ru.drogunov.reader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.FileReader;
import java.io.IOException;

public class ReaderJsonJackson implements Reader {

    private final String pathToFile;
    private final JsonParser jsonParser;

    public ReaderJsonJackson(String pathToFile) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        this.pathToFile = pathToFile;
        this.jsonParser = jsonFactory.createParser(new FileReader(pathToFile));
    }


    @Override
    public String getPathToFile() {
        return pathToFile;
    }

    @Override
    public boolean hasNext() throws IOException {
        JsonToken jsonToken = jsonParser.getCurrentToken();
//        if (jsonToken == JsonToken.START_ARRAY) {
        //TODO Не работает с вложенными массивами
//            hasNext();
//        }
        if (jsonToken == JsonToken.END_ARRAY) {
            jsonParser.nextToken();
            return jsonParser.hasCurrentToken();
        }
        return jsonParser.hasCurrentToken();
    }

    @Override
    public void beginArray() throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            jsonParser.nextToken();
            jsonParser.nextToken();
        } else {
            jsonParser.nextToken();
        }
    }

    @Override
    public void endArray() throws IOException {
        jsonParser.nextToken();
    }

    @Override
    public void beginObject() throws IOException {
        jsonParser.nextToken();
    }

    @Override
    public void endObject() throws IOException {
        jsonParser.nextToken();
    }

    @Override
    public void skipField() throws IOException {
        jsonParser.nextToken();
    }

    @Override
    public String nextName() throws IOException {
        String fieldName = jsonParser.getCurrentName();
        jsonParser.nextToken();
        return fieldName;
    }

    @Override
    public String getString() throws IOException {
        String text = jsonParser.getText();
        jsonParser.nextToken();
        return text;
    }

    @Override
    public Long getLong() throws IOException {
        long longValue = jsonParser.getLongValue();
        jsonParser.nextToken();
        return longValue;
    }

    @Override
    public void close() throws IOException {
        jsonParser.close();
    }
}
