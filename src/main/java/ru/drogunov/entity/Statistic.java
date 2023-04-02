package ru.drogunov.entity;

import java.math.BigInteger;
import java.util.Map;

public class Statistic {
    private long duplicates;
    private Map<String, BigInteger> sumWeightInGroup;
    private long minWeight;
    private long maxWeight;

    public Statistic() {
    }

    public Statistic(long duplicates, Map<String, BigInteger> sumWeightInGroup, long minWeight, long maxWeight) {
        this.duplicates = duplicates;
        this.sumWeightInGroup = sumWeightInGroup;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    //TODO Сделать конструктор


    @Override
    public String toString() {
        return "/////////////////////////////\n" +
                "Statistic:\n" +
                "duplicates=" + duplicates +
                "\n" + printSumWeightByGroup() +
                ",\nminWeight=" + minWeight +
                ",\nmaxWeight=" + maxWeight +
                "\n/////////////////////////////";
    }

    private String printSumWeightByGroup() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Sum weight by group:\n")
                .append("\t\tGrope\t\t")
                .append("|\t\t")
                .append("Sum\n")
                .append("--------------------------\n");
        this.sumWeightInGroup.forEach((group, bigInteger) -> {
            stringBuilder
                    .append("\t")
                    .append(group)
                    .append(": ")
                    .append(bigInteger.toString())
                    .append(";")
                    .append("\n");
        });
        return stringBuilder.toString();
    }

    public long getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(long duplicates) {
        this.duplicates = duplicates;
    }

    public Map<String, BigInteger> getSumWeightInGroup() {
        return sumWeightInGroup;
    }

    public void setSumWeightInGroup(Map<String, BigInteger> sumWeightInGroup) {
        this.sumWeightInGroup = sumWeightInGroup;
    }

    public long getMinWeightCsv() {
        return minWeight;
    }

    public void setMinWeightCsv(long minWeightCsv) {
        this.minWeight = minWeightCsv;
    }

    public long getMaxWeightJson() {
        return maxWeight;
    }

    public void setMaxWeightJson(long maxWeightJson) {
        this.maxWeight = maxWeightJson;
    }

}
