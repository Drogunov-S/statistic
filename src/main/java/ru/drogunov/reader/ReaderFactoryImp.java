package ru.drogunov.reader;

import java.io.IOException;

public class ReaderFactoryImp implements ReaderFactory {


    @Override
    public Reader getReader(String pathToFile) throws IOException {
        if (pathToFile.endsWith(".csv")) {
            return new ReaderCsv(pathToFile, ",");
        } else if (pathToFile.endsWith(".json")) {
            return new ReaderJson(pathToFile);
        }
        throw new IOException("Unsupported file type or bad file path");
    }


}
