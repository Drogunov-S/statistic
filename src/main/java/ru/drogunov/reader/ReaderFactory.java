package ru.drogunov.reader;

import java.io.IOException;

public class ReaderFactory {


    public Reader getReader(String pathToFile) throws IOException {
        if (pathToFile.endsWith(".csv")) {
            return new ReaderCsv(pathToFile, ",");
        } else if (pathToFile.endsWith(".json")) {
            return new ReaderJsonGson(pathToFile);
        }
        throw new IOException("Unsupported file type");
    }


}
