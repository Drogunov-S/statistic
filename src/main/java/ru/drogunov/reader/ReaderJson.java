package ru.drogunov.reader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.FileReader;
import java.io.IOException;

public class ReaderJson implements Reader {

    private final String pathToFile;
    private final JsonParser jsonParser;

    public ReaderJson(String pathToFile) throws IOException {
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
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken==JsonToken.START_ARRAY) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken == JsonToken.START_OBJECT) {
            return true;
        }
        if (currentToken == JsonToken.END_ARRAY) {
            jsonParser.nextToken();
        }
        return jsonParser.hasCurrentToken();
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
