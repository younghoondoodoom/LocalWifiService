package com.example.localwifiservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.localwifiservice.entity.LocationHistory;
import java.util.List;
import org.junit.jupiter.api.Test;

class HistoryRepositoryTest {

    HistoryRepository historyRepository;

    @Test
    public void insertHistoryTest() throws Exception {
        //given
        historyRepository = HistoryRepository.getHistoryRepository();
        LocationHistory history = new LocationHistory(37.5544069, 126.8998666, null);
        LocationHistory history2 = new LocationHistory(38.5544069, 127.8998666, null);
        //when
        boolean b = historyRepository.insertHistory(history);
        boolean b2 = historyRepository.insertHistory(history2);

        //then
        List<LocationHistory> historyList = historyRepository.getHistoryList();

        assertThat(b).isEqualTo(true);
        assertThat(b2).isEqualTo(true);
        assertThat(history.toString()).isEqualTo(
            historyList.get(0).toString());
        assertThat(history2.toString()).isEqualTo(
            historyList.get(1).toString());
    }
    
    @Test
    public void getHistoryListTest() throws Exception {
        //given
        historyRepository = HistoryRepository.getHistoryRepository();

        //when
        List<LocationHistory> historyList = historyRepository.getHistoryList();

        //then
        assertThat(historyList.get(0).getLat()).isEqualTo(37.5544069);
        assertThat(historyList.get(0).getLnt()).isEqualTo(126.8998666);
        assertThat(historyList.get(1).getLat()).isEqualTo(38.5544069);
        assertThat(historyList.get(1).getLnt()).isEqualTo(127.8998666);
    }
}