package ru.drogunov.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderJsonGson implements Reader {
    private final String pathToFile;
    private final Gson gson;
    private final JsonReader reader;

    public ReaderJsonGson(String pathToFile) throws FileNotFoundException {
        this.pathToFile = pathToFile;
        this.gson = new GsonBuilder().create();
        this.reader = gson.newJsonReader(new FileReader(pathToFile));

    }

    @Override
    public String getPathToFile() {
        return pathToFile;
    }

    @Override
    public boolean hasNext() throws IOException {
        return reader.hasNext();
    }

    @Override
    public void beginArray() throws IOException {
        reader.beginArray();
    }

    @Override
    public void endArray() throws IOException {
        reader.endArray();
    }

    @Override
    public void beginObject() throws IOException {
        reader.beginObject();
    }

    @Override
    public void endObject() throws IOException {
        reader.endObject();
    }

    @Override
    public void skipField() throws IOException {
        reader.skipValue();
        reader.skipValue();
    }

    @Override
    public String nextName() throws IOException {
        return reader.nextName();
    }

    @Override
    public String getString() throws IOException {
        return reader.nextString();
    }

    @Override
    public Long getLong() throws IOException {
        return reader.nextLong();
    }

    //TODO Подумать над выбросом
    @Override
    public void close() throws IOException {
        reader.close();
    }

}
