package com.example.localwifiservice.repository;

import com.example.localwifiservice.entity.Wifi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiRepository {

    private final ConnectionManager cm;
    private static WifiRepository wifiRepository;

   private static final String SQL_SELECT_ALL = "select * from wifi";
    private static final String SQL_INSERT_WIFI =
        "insert into wifi (management_no, borough, name, road_name_address, detail_address, floor, install_type, install_institution,"
            + " service_classification, network_type, installed_year, inout, connection_env, lat, lnt, work_datetime)"
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


    public static synchronized WifiRepository getWifiRepository() {
        if (wifiRepository == null) {
            wifiRepository = new WifiRepository(ConnectionManager.getConnectionManager());
        }
        return wifiRepository;
    }

    public List<Wifi> getWifiList() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Wifi> wifiList = new ArrayList<>();

        try {
            conn = cm.getConnect();
            pst = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                String management_no = rs.getString("management_no");
                String borough = rs.getString("borough");
                String name = rs.getString("name");
                String road_name_address = rs.getString("road_name_address");
                String detail_address = rs.getString("detail_address");
                String floor = rs.getString("floor");
                String install_type = rs.getString("install_type");
                String install_institution = rs.getString("install_institution");
                String service_classification = rs.getString("service_classification");
                String network_type = rs.getString("network_type");
                String installed_year = rs.getString("installed_year");
                String inout = rs.getString("inout");
                String connection_env = rs.getString("connection_env");
                Double lat = rs.getDouble("lat");
                Double lnt = rs.getDouble("lnt");
                String work_datetime = rs.getString("work_datetime");
                Wifi wifi = new Wifi(management_no, borough, name, road_name_address,
                    detail_address, floor,
                    install_type, install_institution, service_classification, network_type,
                    installed_year, inout, connection_env, lat, lnt, work_datetime);
                wifiList.add(wifi);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            cm.close(rs);
            cm.close(pst);
            cm.close(conn);
        }
        return wifiList;
    }

    public boolean insertWifi(Wifi wifi) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = cm.getConnect();
            pst = conn.prepareStatement(SQL_INSERT_WIFI);

            pst.setString(1, wifi.getManagement_no());
            pst.setString(2, wifi.getBorough());
            pst.setString(3, wifi.getName());
            pst.setString(4, wifi.getRoad_name_address());
            pst.setString(5, wifi.getDetail_address());
            pst.setString(6, wifi.getFloor());
            pst.setString(7, wifi.getInstall_type());
            pst.setString(8, wifi.getInstall_institution());
            pst.setString(9, wifi.getService_classification());
            pst.setString(10, wifi.getNetwork_type());
            pst.setString(11, wifi.getInstalled_year());
            pst.setString(12, wifi.getInout());
            pst.setString(13, wifi.getConnection_env());
            pst.setString(14, String.valueOf(wifi.getLat()));
            pst.setString(15, String.valueOf(wifi.getLnt()));
            pst.setString(16, wifi.getWork_datetime());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
        return true;
    }
}
