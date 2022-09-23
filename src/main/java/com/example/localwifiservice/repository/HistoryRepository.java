package com.example.localwifiservice.repository;

import com.example.localwifiservice.entity.LocationHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryRepository {

    private final ConnectionManager cm;
    private static HistoryRepository historyRepository;

    private static final String SQL_SELECT_ALL = "select * from location_history order by inquiry_date desc";
    private static final String SQL_INSERT_HISTORY = "insert into location_history (lat, lnt) values (?, ?)";
    private static final String SQL_DELETE_HISTORY = "delete from location_history where id = ?";

    public static synchronized HistoryRepository getHistoryRepository() {
        if (historyRepository == null) {
            historyRepository = new HistoryRepository(
                ConnectionManager.getConnectionManager());
        }
        return historyRepository;
    }

    public boolean insertHistory(LocationHistory history) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = cm.getConnect();
            pst = conn.prepareStatement(SQL_INSERT_HISTORY);

            pst.setString(1, String.valueOf(history.getLat()));
            pst.setString(2, String.valueOf(history.getLnt()));

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            cm.close(pst);
            cm.close(conn);
        }

        return true;
    }

    public List<LocationHistory> getHistoryList() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        ArrayList<LocationHistory> historyList = new ArrayList<>();
        try {
            conn = cm.getConnect();
            pst = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pst.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                double lat = rs.getDouble("lat");
                double lnt = rs.getDouble("lnt");
                Timestamp inquiry_date = rs.getTimestamp("inquiry_date");
                LocationHistory history = new LocationHistory(id, lat, lnt,
                    inquiry_date);

                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            cm.close(rs);
            cm.close(pst);
            cm.close(conn);
        }

        return historyList;
    }

    public boolean deleteHistory(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = cm.getConnect();
            pst = conn.prepareStatement(SQL_DELETE_HISTORY);
            pst.setString(1, String.valueOf(id));

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }

        return true;
    }

}
