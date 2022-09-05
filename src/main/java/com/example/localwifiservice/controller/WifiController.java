package com.example.localwifiservice.controller;

import com.example.localwifiservice.entity.Wifi;
import com.example.localwifiservice.repository.WifiRepository;
import com.example.localwifiservice.serializer.WifiSerializer;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/load-wifi")
public class WifiController extends HttpServlet {

    private final String key = "5555776a7264756437317a56427555";
    WifiRepository wifiRepository = WifiRepository.getWifiRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int startIdx = 1;
        int endIdx = 1000;
        int totalCount = 0;

        while (startIdx < endIdx) {
            String apiUrl = "http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/"
                + String.valueOf(startIdx) + "/" + String.valueOf(endIdx) + "/";
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                resp.append(inputLine);
            }
            br.close();

            Gson gson = new Gson();
            WifiSerializer wifiSerializer = gson.fromJson(resp.toString(), WifiSerializer.class);
            totalCount = wifiSerializer.getTbPublicWifiInfo().getList_total_count();

            for (Wifi wifi : wifiSerializer.getWifiList()) {
                wifiRepository.insertWifi(wifi);
            }

            startIdx = endIdx + 1;
            endIdx = startIdx + 999;
            if (endIdx > totalCount) {
                endIdx = totalCount;
            }
        }

        response.sendRedirect("load-wifi.jsp");
    }
}
