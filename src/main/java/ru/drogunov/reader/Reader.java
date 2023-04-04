package ru.drogunov.reader;

import java.io.IOException;

public interface Reader {
    String getPathToFile();

    boolean hasNext() throws IOException;

    void beginArray() throws IOException;

    void endArray() throws IOException;

    void beginObject() throws IOException;

    void endObject() throws IOException;

    void skipField() throws IOException;

    String nextName() throws IOException;

    String getString() throws IOException;

    Long getLong() throws IOException;

    //TODO Подумать над выбросом
    void close() throws IOException;
}
