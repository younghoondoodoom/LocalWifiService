package com.example.localwifiservice.repository;

import com.example.localwifiservice.entity.Wifi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WifiRepository {

    private final ConnectionManager cm;

    private static final String SQL_SELECT_ALL = "select * from wifi";

    public WifiRepository(ConnectionManager cm) {
        this.cm = cm;
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
                Long id = rs.getLong("id");
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
                Wifi wifi = new Wifi(id, management_no, borough, name, road_name_address,
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
}
