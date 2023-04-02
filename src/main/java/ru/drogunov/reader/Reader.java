package ru.drogunov.reader;

import com.fasterxml.jackson.databind.JsonNode;
import ru.drogunov.entity.Element;

public interface Reader {

    int getSize();

    Element get(int index);

    JsonNode getJsonNode(int index);
}
