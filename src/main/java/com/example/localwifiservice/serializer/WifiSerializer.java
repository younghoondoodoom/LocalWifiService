package com.example.localwifiservice.serializer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WifiSerializer {

    private TbPublicWifiInfo TbPublicWifiInfo;

    @Getter
    public static class TbPublicWifiInfo {

        private Long list_total_count;
        private Result RESULT;
        private List<Data> row;

        @Getter
        public static class Result {

            String CODE;
            String MESSAGE;
        }


        @Getter
        public class Data {

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


}