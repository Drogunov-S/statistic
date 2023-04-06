package ru.drogunov;

import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.ReaderFactory;
import ru.drogunov.service.StatisticServiceImp;

import java.io.IOException;

public class Start {

    static String fileJson = "/home/sergey/Загрузки/Telegram Desktop/tz/out.json";
    static String fileCsv = "/home/sergey/Загрузки/Telegram Desktop/tz/out.csv";
    public static void main(String[] args) throws IOException {
        memory();
        ReaderFactory readerFactory = new ReaderFactory();
        StatisticServiceImp statisticService = new StatisticServiceImp(readerFactory);
        Statistic statistics = statisticService.getStatistics(fileJson);
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
