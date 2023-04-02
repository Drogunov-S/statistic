package ru.drogunov;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.Reader;
import ru.drogunov.reader.ReaderJson;
import ru.drogunov.service.StatisticService;

import java.io.IOException;

public class Starter {

    static String fileJson = "/home/sergey/Загрузки/Telegram Desktop/tz/out.json";
    static String fileCsv = "/home/sergey/Загрузки/Telegram Desktop/tz/out.csv";

    public static void main(String[] args) throws IOException {
        //TODO Настройки памяти VM -Xmx512m
        memory();
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new ReaderJson(fileJson, objectMapper);
        StatisticService statisticService = new StatisticService(reader);
        Statistic statisticFile = statisticService.getStatisticFile();
        System.out.println(statisticFile);
        memory();
    }

    public static void memory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("///////////////////////////////////////");
        System.out.println("TOTAL " + runtime.totalMemory()/1048576);
        System.out.println("MAX " + runtime.maxMemory()/1048576);
        System.out.println("FREE " + runtime.freeMemory()/1048576);
        System.out.println("///////////////////////////////////////");
    }
}
