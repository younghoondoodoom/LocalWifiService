package com.example.localwifiservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionManager {

    private static ConnectionManager connectionManager;
    private Connection conn;

    private final String DATABASE_DRIVER = "org.sqlite.JDBC";
    private final String DATABASE_URL = "jdbc:sqlite:/Users/choiyounghoon/Desktop/study/LocalWifiService/publicWifi.sqlite";

    public static synchronized ConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        try {
            // jdbc driver class 로드
            Class.forName(DATABASE_DRIVER);

            // DB 연결 객체 생성
            conn = DriverManager.getConnection(this.DATABASE_URL);

            // 로그 출력
            System.out.println("connected");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return conn;
    }

    public void close(ResultSet rs){
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void close(PreparedStatement pst) {
        if (pst == null) {
            return;
        }
        try {
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void close(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

}
