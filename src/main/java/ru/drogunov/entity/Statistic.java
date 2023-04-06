package ru.drogunov.entity;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "\n" +
                "Statistic:\n" +
                "duplicates=" + duplicates +
                "\n" + printSumWeightByGroup() +
                ",\nminWeight=" + minWeight +
                ",\nmaxWeight=" + maxWeight +
                "\n";
    }

    private String printSumWeightByGroup() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Sum weight by group:\n")
                .append("\t\tGrope\t\t")
                .append("|\t\t")
                .append("Sum\n")
                .append("--------------------------\n");
        this.sumWeightInGroup.forEach((group, bigInteger) -> stringBuilder
                .append("\t")
                .append(group)
                .append(": ")
                .append(bigInteger.toString())
                .append(";")
                .append("\n"));
        return stringBuilder.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return duplicates == statistic.duplicates && minWeight == statistic.minWeight && maxWeight == statistic.maxWeight && Objects.equals(sumWeightInGroup, statistic.sumWeightInGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duplicates, sumWeightInGroup, minWeight, maxWeight);
    }
}
