package ru.drogunov;

import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.ReaderFactory;
import ru.drogunov.reader.ReaderFactoryImp;
import ru.drogunov.service.StatisticServiceImp;
import ru.drogunov.util.Input;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Input in = new Input(new BufferedReader(new InputStreamReader(System.in)));
        System.out.println("Введите путь к файлу или \"Exit\" для выхода: ");
        String input = in.readLine();
        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("Анализ файла.");
            ReaderFactory readerFactory = new ReaderFactoryImp();
            StatisticServiceImp statisticService = new StatisticServiceImp(readerFactory);
            Statistic statistics;
            try {
                statistics = statisticService.getStatistics(input);
                System.out.println("Анализ завершён.");
                System.out.println(statistics);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            System.out.println("Введите путь к новому файлу или \"Exit\" для выхода: ");
            input = in.readLine();
        }
    }
}
