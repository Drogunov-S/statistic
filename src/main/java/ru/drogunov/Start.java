package ru.drogunov;

import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.Reader;
import ru.drogunov.reader.ReaderJsonJackson;
import ru.drogunov.service.StatisticService;

import java.io.IOException;

public class Start {

    static String fileJson = "/home/sergey/Загрузки/Telegram Desktop/tz/out.json";
    static String fileCsv = "/media/sergey/dev/IDEA/IdeaProjects/statistic/src/main/resources/out.json";
    public static void main(String[] args) throws IOException {
        memory();
        Reader reader = new ReaderJsonJackson(fileJson);
        StatisticService statisticService = new StatisticService(reader);
        Statistic statistics = statisticService.getStatistics();
        System.out.println(statistics);
        memory();
    }

    public static void memory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("///////////////////////////////////////");
        System.out.println("TOTAL " + runtime.totalMemory() / 1048576);
        System.out.println("MAX " + runtime.maxMemory() / 1048576);
        System.out.println("FREE " + runtime.freeMemory() / 1048576);
        System.out.println("///////////////////////////////////////");
    }
}
