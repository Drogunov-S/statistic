package ru.drogunov.service;

import org.junit.jupiter.api.Test;
import ru.drogunov.entity.Statistic;
import ru.drogunov.reader.ReaderFactory;
import ru.drogunov.reader.ReaderFactoryImp;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticServiceImpTest {

    final ReaderFactory readerFactory = new ReaderFactoryImp();
    final StatisticService statisticService = new StatisticServiceImp(readerFactory);

    @Test
    void getStatistics() throws IOException {
        Map<String, BigInteger> groups = new HashMap<>();
        groups.put("rRSimomZUin", new BigInteger("5806952731065304053"));
        Statistic expectedStatistic = new Statistic(0, groups, 5806952731065304053L, 5806952731065304053L);
        URL resource = this.getClass().getResource("/jsonForTestStatisticService.json");
        Statistic statistics = statisticService.getStatistics(resource.getFile());
        assertEquals(expectedStatistic, statistics);
    }
}
