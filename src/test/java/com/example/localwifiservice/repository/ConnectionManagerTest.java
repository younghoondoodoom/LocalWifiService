package com.example.localwifiservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

    ConnectionManager connectionManager;

    @Test
    public void getConnTest() throws Exception {
        //given
        connectionManager = ConnectionManager.getConnectionManager();

        //when
        Connection conn = connectionManager.getConn();
        String sql_insert = "insert into location_history (lat, lnt) values (123.231, 222.231)";
        String sql_select = "select * from location_history";

        PreparedStatement pstm = conn.prepareStatement(sql_insert);
        pstm.executeUpdate();

        pstm.close();

        pstm = conn.prepareStatement(sql_select);
        ResultSet rs = pstm.executeQuery();

        //then
        assertThat(conn).isNotEqualTo(null);
        assertThat(rs.getDouble("lat")).isEqualTo(123.231);
        assertThat(rs.getDouble("lnt")).isEqualTo(222.231);
    }

}