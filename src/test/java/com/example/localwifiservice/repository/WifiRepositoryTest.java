package com.example.localwifiservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.localwifiservice.entity.Wifi;
import java.util.List;
import org.junit.jupiter.api.Test;

class WifiRepositoryTest {

    WifiRepository wifiRepository;

    @Test
    public void insertWifiTest() throws Exception {
        //given
        wifiRepository = WifiRepository.getWifiRepository();
        Wifi wifi = new Wifi(0.0, "ARI00001", "서대문구", "상수도사업본부", "서소문로 51", "본관 1F", "1",
            "7-1. 커뮤니티 - 행정", "서울시(AP)", "공공WiFi", "임대망", "2014", "실내", "",
            37.5544069, 126.8998666, "2022-08-31 10:58:00.0");

        Wifi wifi2 = new Wifi(0.0, "ARI00002", "서대문구", "상수도사업본부", "서소문로 51", "본관 1F", "1",
            "7-1. 커뮤니티 - 행정", "서울시(AP)", "공공WiFi", "임대망", "2014", "실내", "",
            38.5544069, 126.8998666, "2022-08-31 10:58:00.0");

        //when
        boolean b = wifiRepository.insertWifi(wifi);
        boolean b2 = wifiRepository.insertWifi(wifi2);

        //then
        assertThat(b).isEqualTo(true);
        assertThat(b2).isEqualTo(true);
        assertThat(wifi.toString()).isEqualTo(wifiRepository.getWifiList(37.5544069, 126.8998666).get(0).toString());
        assertThat(wifi.toString()).isEqualTo(wifiRepository.getWifiList(37.5544069, 126.8998666).get(1).toString());
    }

    @Test
    public void getWifiListTest() throws Exception {
        //given
        wifiRepository = WifiRepository.getWifiRepository();

        //when
        List<Wifi> wifiList = wifiRepository.getWifiList(37.5544069, 126.8998666);

        //then
        assertThat(wifiList.get(0).getBorough()).isEqualTo("서대문구");
        assertThat(wifiList.get(0).getManagement_no()).isEqualTo("ARI00001");
        assertThat(wifiList.get(0).getWork_datetime()).isEqualTo("2022-08-31 10:58:00.0");
        assertThat(wifiList.get(1).getManagement_no()).isEqualTo("ARI00002");

    }

}