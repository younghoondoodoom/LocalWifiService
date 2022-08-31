package com.example.localwifiservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.localwifiservice.entity.Wifi;
import java.util.List;
import org.junit.jupiter.api.Test;

class WifiRepositoryTest {

    WifiRepository wifiRepository;

    @Test
    public void getWifiListTest() throws Exception {
        //given
        wifiRepository = WifiRepository.getWifiRepository();

        //when
        List<Wifi> wifiList = wifiRepository.getWifiList();
        Wifi wifi = wifiList.get(0);

        //then
        assertThat(wifi.getBorough()).isEqualTo("서대문구");
        assertThat(wifi.getManagement_no()).isEqualTo("ARI00001");
        assertThat(wifi.getWork_datetime()).isEqualTo("2022-08-31 10:58:00.0");

    }

}