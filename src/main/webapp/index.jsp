<%@ page import="com.example.localwifiservice.repository.WifiRepository" %>
<%@ page import="com.example.localwifiservice.entity.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.localwifiservice.entity.LocationHistory" %>
<%@ page import="com.example.localwifiservice.repository.HistoryRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<style>
  #wifi-table {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 13px;
    border-collapse: collapse;
    width: 100%;
  }

  #wifi-table td, #wifi-table th {
    border: 1px solid #ddd;
    padding: 8px;
  }

  #wifi-table tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  #wifi-table tr:hover {
    background-color: #ddd;
  }

  #wifi-table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: center;
    background-color: #04AA6D;
    color: white;
  }

</style>
<head>
    <title>와이파이 정보 구하기</title>
</head>

<body>
<h1>
    와이파이 정보 구하기
</h1>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war">홈 | </a>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war/history.jsp">위치 히스토리 목록 | </a>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war/load-wifi">Open API 와이파이 정보 가져오기</a>
<br>

<form action="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war" method="get">
    <td>LAT:</td>
    <input type="text" name="lat">
    <td>, LNT:</td>
    <input type="text" name="lnt">
    <button onclick="">내 위치 가져오기</button>
    <button type="submit">근처 Wifi 정보 보기</button>
</form>

<table id="wifi-table">
    <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>Wifi접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일지</th>
    </tr>
    <%
        WifiRepository wifiRepository = WifiRepository.getWifiRepository();
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        if (lat == null || lnt == null || lat.equals("") || lnt.equals("")) {
            lnt = "37.5544069";
            lat = "126.8998666";
        } else {
            LocationHistory history = new LocationHistory(null, Double.parseDouble(lat),
                    Double.parseDouble(lnt), null);
            HistoryRepository historyRepository = HistoryRepository.getHistoryRepository();
            historyRepository.insertHistory(history);
        }
        List<Wifi> wifiList = wifiRepository.getWifiList(Double.parseDouble(lat),
                Double.parseDouble(lnt));
    %>

    <%
        if (wifiList.isEmpty() || request.getParameter("lat") == null
                || request.getParameter("lnt") == null) {
    %>
    <tr>
        <td colspan="17" style="text-align: center; background-color: #ffffff">
            위치 정보를 입력한 후에 조회 해주세요
        </td>
    </tr>
    <%
        } else {
    %>


    <%
        for (int i = 0; i < 20; i++) {
    %>
    <tr>
        <td>
            <%= wifiList.get(i).getDistance()%>
        </td>
        <td>
            <%= wifiList.get(i).getManagement_no()%>
        </td>
        <td>
            <%= wifiList.get(i).getBorough()%>
        </td>
        <td>
            <%= wifiList.get(i).getName()%>
        </td>
        <td>
            <%= wifiList.get(i).getRoad_name_address()%>
        </td>
        <td>
            <%= wifiList.get(i).getDetail_address()%>
        </td>
        <td>
            <%= wifiList.get(i).getFloor()%>
        </td>
        <td>
            <%= wifiList.get(i).getInstall_type()%>
        </td>
        <td>
            <%= wifiList.get(i).getInstall_institution()%>
        </td>
        <td>
            <%= wifiList.get(i).getService_classification()%>
        </td>
        <td>
            <%= wifiList.get(i).getNetwork_type()%>
        </td>
        <td>
            <%= wifiList.get(i).getInstalled_year()%>
        </td>
        <td>
            <%= wifiList.get(i).getInout()%>
        </td>
        <td>
            <%= wifiList.get(i).getConnection_env()%>
        </td>
        <td>
            <%= wifiList.get(i).getLat()%>
        </td>
        <td>
            <%= wifiList.get(i).getLnt()%>
        </td>
        <td>
            <%= wifiList.get(i).getWork_datetime()%>
        </td>
    </tr>
    <%
        }
    %>
    <%
        }
    %>

</table>
</body>
</html>