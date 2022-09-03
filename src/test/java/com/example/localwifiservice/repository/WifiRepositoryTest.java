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

    @Test
    public void insertWifiTest() throws Exception {
        //given
        wifiRepository = WifiRepository.getWifiRepository();
        Wifi wifi = new Wifi("ARI00001", "서대문구", "상수도사업본부", "서소문로 51", "본관 1F", "1",
            "7-1. 커뮤니티 - 행정", "서울시(AP)", "공공WiFi", "임대망", "2014", "실내", "",
            126.96675, 37.561924, "2022-08-31 10:58:00.0");

        //when
        boolean b = wifiRepository.insertWifi(wifi);

        //then
        assertThat(b).isEqualTo(true);
        assertThat(wifi.toString()).isEqualTo(wifiRepository.getWifiList().get(0).toString());
    }

}