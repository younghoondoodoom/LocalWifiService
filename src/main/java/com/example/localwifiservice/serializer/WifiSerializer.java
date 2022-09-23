package com.example.localwifiservice.serializer;

import com.example.localwifiservice.entity.Wifi;
import com.example.localwifiservice.serializer.WifiSerializer.TbPublicWifiInfo.Data;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class WifiSerializer {

    private TbPublicWifiInfo TbPublicWifiInfo;

    @Getter
    public static class TbPublicWifiInfo {

        private int list_total_count;
        private Result RESULT;
        private List<Data> row;

        @Getter
        public static class Result {

            String CODE;
            String MESSAGE;
        }


        @Getter
        public static class Data {

            Long id;
            String X_SWIFI_MGR_NO;
            String X_SWIFI_WRDOFC;
            String X_SWIFI_MAIN_NM;
            String X_SWIFI_ADRES1;
            String X_SWIFI_ADRES2;
            String X_SWIFI_INSTL_FLOOR;
            String X_SWIFI_INSTL_TY;
            String X_SWIFI_INSTL_MBY;
            String X_SWIFI_SVC_SE;
            String X_SWIFI_CMCWR;
            String X_SWIFI_CNSTC_YEAR;
            String X_SWIFI_INOUT_DOOR;
            String X_SWIFI_REMARS3;
            Double LAT;
            Double LNT;
            String WORK_DTTM;
        }

    }

    public List<Wifi> getWifiList() {
        ArrayList<Wifi> wifis = new ArrayList<>();

        for (Data data : TbPublicWifiInfo.row) {
            Wifi wifi = new Wifi(0.0, data.X_SWIFI_MGR_NO, data.X_SWIFI_WRDOFC,
                data.X_SWIFI_MAIN_NM,
                data.X_SWIFI_ADRES1, data.X_SWIFI_ADRES2,
                data.X_SWIFI_INSTL_FLOOR,
                data.X_SWIFI_INSTL_TY, data.X_SWIFI_INSTL_MBY,
                data.X_SWIFI_SVC_SE,
                data.X_SWIFI_CMCWR, data.X_SWIFI_CNSTC_YEAR,
                data.X_SWIFI_INOUT_DOOR,
                data.X_SWIFI_REMARS3, data.LAT, data.LNT, data.WORK_DTTM);
            wifis.add(wifi);
        }
        return wifis;
    }

}