package openapi;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.localwifiservice.serializer.WifiSerializer;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class JsonToObjectTest {

    @Test
    public void jsonToObjectTest() throws Exception {
        //given
        String apiUrl = "http://openapi.seoul.go.kr:8088/" + "5555776a7264756437317a56427555" + "/json/TbPublicWifiInfo/1/1/";
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        //when
        Gson gson = new Gson();
        WifiSerializer wifi = gson.fromJson(response.toString(), WifiSerializer.class);

        //then
        assertThat(wifi.getTbPublicWifiInfo().getList_total_count()).isEqualTo(17804);
        assertThat(wifi.getTbPublicWifiInfo().getRow().get(0).getX_SWIFI_ADRES1()).isEqualTo(
            "서소문로 51");
    }
}