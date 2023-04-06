package ru.drogunov.service;

import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.Reader;
import ru.drogunov.reader.ReaderCsv;
import ru.drogunov.reader.ReaderFactory;
import ru.drogunov.reader.ReaderJsonGson;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class StatisticServiceImp {
    private Reader reader;
    private final ReaderFactory readerFactory;
    private String pathToFile;

    public StatisticServiceImp(ReaderFactory readerFactory) {
        this.readerFactory = readerFactory;
    }


    public Statistic getStatistics(String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        reader = readerFactory.getReader(pathToFile);

        reader.beginArray();
        Statistic statistic = process();
        reader.endArray();
        reader.close();

        return statistic;
    }

    private Statistic process() throws IOException {
        long duplicates = 0L;
        Map<String, BigInteger> sumElementsWeightInGroup = new HashMap<>();
        long minWeight = Long.MAX_VALUE;
        long maxWeight = Long.MIN_VALUE;

        for (int i = 0; reader.hasNext(); i++) {
            String group = null;
            String type = null;
            long number = 0L;
            long weight = 0L;

            reader.beginObject();
            String fieldName = reader.nextName();
            if (fieldName == null) {
                System.out.println(i);
                System.out.println(reader.getString());
                System.out.println();

            }
            if (fieldName.equals("group")) {
                group = reader.getString();
                fieldName = reader.nextName();
            }
            if (fieldName.equals("type")) {
                type = reader.getString();
                fieldName = reader.nextName();
            }
            if (fieldName.equals("number")) {
                number = reader.getLong();
                fieldName = reader.nextName();
            }
            if (fieldName.equals("weight")) {
                weight = reader.getLong();
            }
            reader.endObject();
            duplicates += counterDuplicate(group, type);
            calculateSumWeightInGroup(group, weight, sumElementsWeightInGroup);
            maxWeight = checkMaxWeight(maxWeight, weight);
            minWeight = checkMinWeight(minWeight, weight);
        }

        return new Statistic(duplicates, sumElementsWeightInGroup, minWeight, maxWeight);
    }

    private long counterDuplicate(String group, String type) throws IOException {
        long counter = 0L;

        Reader readerInner = readerFactory.getReader(pathToFile);

        readerInner.beginArray();
        for (int i = 0; readerInner.hasNext(); i++) {
            //TODO Подумать над наллами
            int passCounter = 4;
            String comparisonGroup = null;
            String comparisonType = null;

            readerInner.beginObject();
            String fieldName = readerInner.nextName();
            if (fieldName.equals("group")) {
                comparisonGroup = readerInner.getString();
                fieldName = readerInner.nextName();
                passCounter--;
            } else {
                skipValues(readerInner, passCounter);
                continue;
            }
            if (fieldName.equals("type")) {
                comparisonType = readerInner.getString();
                passCounter--;
            } else {
                skipValues(readerInner, passCounter);
                continue;
            }
            skipValues(readerInner, passCounter);
            if (group.equals(comparisonGroup) && type.equals(comparisonType)) {
                counter++;
            }
        }
        readerInner.endArray();
        readerInner.close();

        return counter - 1;
    }

    private void skipValues(Reader innerReader, int passCounter) throws IOException {
        for (int i = 0; i < passCounter; i++) {
            innerReader.skipField();
        }
        innerReader.endObject();
    }

    private void calculateSumWeightInGroup(String group, long weightElement, Map<String, BigInteger> map) {
        BigInteger weight = new BigInteger(String.valueOf(weightElement));
        BigInteger sumWeight = map.putIfAbsent(group, weight);
        if (nonNull(sumWeight)) {
            map.put(group, sumWeight.add(weight));
        }
    }

    private long checkMaxWeight(long maxWeight, long currentWeight) {
        return Math.max(maxWeight, currentWeight);
    }

    private long checkMinWeight(long minWeight, long currentWeight) {
        return Math.min(minWeight, currentWeight);
    }
}
