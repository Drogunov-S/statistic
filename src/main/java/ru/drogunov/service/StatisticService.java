package ru.drogunov.service;

import com.fasterxml.jackson.databind.JsonNode;
import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.Reader;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class StatisticService {

    private Reader reader;

    public StatisticService(Reader reader) {
        this.reader = reader;
    }

    //TODO FACTORY READER

    public Statistic getStatisticFile() {
        long duplicates = 0l;
        Map<String, BigInteger> sumElementsWeightInGroup = new HashMap<>();
        long minWeight = Long.MAX_VALUE;
        long maxWeight = Long.MIN_VALUE;

        for (int i = 0; i < reader.getSize(); i++) {
            JsonNode jsonNode = reader.getJsonNode(i);
            long weight = jsonNode.get("weight").asLong();
            String  group = jsonNode.get("group").asText();
            String  type = jsonNode.get("type").asText();
            minWeight = checkMinWeight(minWeight, weight);
            maxWeight = checkMaxWeight(maxWeight, weight);
            calculateSumWeightInGroup(group, weight, sumElementsWeightInGroup);
            duplicates = counterDuplicate(group, type);
        }
        Statistic statistic = new Statistic(duplicates, sumElementsWeightInGroup, minWeight, maxWeight);
        return statistic;
    }

    private void calculateSumWeightInGroup(String group, long weightElement, Map<String, BigInteger> storage) {
        BigInteger weight = new BigInteger(String.valueOf(weightElement));
        BigInteger sumWeight = storage.putIfAbsent(group, weight);
        if (nonNull(sumWeight)) {
            storage.put(group, sumWeight.add(weight));
        }
    }

    private long counterDuplicate(String group, String type) {
        long counter = 0l;
        for (int i = 0; i < reader.getSize(); i++) {
            JsonNode jsonNode = reader.getJsonNode(i);
            String groupNext = jsonNode.get("group").asText();
            String typeNext = jsonNode.get("type").asText();
            if (group.equals(groupNext) && typeNext.equals(type)) {
                counter++;
            }
        }
        return counter -1;
    }

/*

    public Statistic getStatisticFile() {
        long duplicates = 0l;
        Map<String, BigInteger> sumElementsWeightInGroup = new HashMap<>();
        long minWeight = Long.MAX_VALUE;
        long maxWeight = Long.MIN_VALUE;

        for (int i = 0; i < reader.getSize(); i++) {
            Element element = reader.get(i);
            long weight = element.getWeight();
            minWeight = checkMinWeight(minWeight, weight);
            maxWeight = checkMaxWeight(maxWeight, weight);
            calculateSumWeightInGroup(element, sumElementsWeightInGroup);
            duplicates = counterDuplicate(element);
        }
        Statistic statistic = new Statistic(duplicates, sumElementsWeightInGroup, minWeight, maxWeight);
        return statistic;
    }
*/


//    private long counterDuplicate(Element element) {
//        long counter = 0l;
//        for (int i = 0; i < reader.getSize(); i++) {
//            Element nextElement = reader.get(i);
//            if (element.equals(nextElement)) {
//                counter++;
//            }
//        }
//        return counter - 1;
//    }
/*
    private void calculateSumWeightInGroup(Element element, Map<String, BigInteger> storage) {
        String group = element.getGroup();
        BigInteger weight = new BigInteger(String.valueOf(element.getWeight()));
        BigInteger sumWeight = storage.putIfAbsent(group, weight);
        if (nonNull(sumWeight)) {
            storage.put(element.getGroup(), sumWeight.add(weight));
        }
    }*/

    private long checkMaxWeight(long maxWeight, long currentWeight) {
        return Math.max(maxWeight, currentWeight);
    }

    private long checkMinWeight(long minWeight, long currentWeight) {
        return Math.min(minWeight, currentWeight);
    }


}
