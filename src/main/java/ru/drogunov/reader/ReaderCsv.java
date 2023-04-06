package ru.drogunov.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReaderCsv implements Reader{
    private final String filePath;
    private final String separator;
    private final BufferedReader reader;
    private final List<String> columns;
    private List<String> cashValues;
    private int cursor = -1;

    public ReaderCsv(String filePath, String separator) throws IOException {
        this.filePath = filePath;
        this.separator = separator;
        this.reader = new BufferedReader(new FileReader(filePath));
        this.columns = Arrays.asList(reader.readLine().split(separator));
    }


    @Override
    public String getPathToFile() {
        return filePath;
    }

    @Override
    public boolean hasNext() throws IOException {
        return reader.ready();
    }

    @Override
    public void beginObject() throws IOException {
        if (cashValues == null || cashValues.isEmpty()) {
            cashValues = Arrays.asList(reader.readLine().split(separator));
        }
    }

    @Override
    public void endObject() {
        cashValues = null;
        cursor = -1;
    }

    @Override
    public void skipField() {
        cursor++;
    }

    @Override
    public String nextName() {
        return columns.get(++cursor);
    }

    @Override
    public String getString() {
        return cashValues.get(cursor);
    }

    @Override
    public Long getLong() {
        return Long.parseLong(cashValues.get(cursor));
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
