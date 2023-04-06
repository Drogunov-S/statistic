package ru.drogunov.reader;

import java.io.IOException;

public interface ReaderFactory {
    Reader getReader(String pathToFile) throws IOException;
}
