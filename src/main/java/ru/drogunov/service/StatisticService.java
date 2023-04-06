package ru.drogunov.service;

import ru.drogunov.entity.Statistic;

import java.io.IOException;

public interface StatisticService {
    Statistic getStatistics(String pathToFile) throws IOException;
}
