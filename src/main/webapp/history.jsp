<%@ page import="com.example.localwifiservice.repository.HistoryRepository" %>
<%@ page import="com.example.localwifiservice.entity.LocationHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>위치 히스토리 목록</title>
</head>
<body>

<h1>
    위치 히스토리 목록
</h1>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war">홈 | </a>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war/history.jsp">위치 히스토리 목록 | </a>
<a href="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war/load-wifi">Open API 와이파이 정보
    가져오기</a>
<br>

<table id="wifi-table">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        HistoryRepository historyRepository = HistoryRepository.getHistoryRepository();

        List<LocationHistory> historyList = historyRepository.getHistoryList();
    %>

    <%
        for (int i = 0; i < historyList.size(); i++) {
    %>
    <tr>
        <td><%= historyList.get(i).getId()%>
        </td>
        <td><%= historyList.get(i).getLat()%>
        </td>
        <td><%= historyList.get(i).getLnt()%>
        </td>
        <td><%= historyList.get(i).getInquiry_at()%>
        </td>
        <td>
            <form action="/Gradle___com_example___LocalWifiService_1_0_SNAPSHOT_war/history"
                  method="post">
                <button type="submit">삭제</button>
                <input type="hidden" name="id" value="<%= historyList.get(i).getId()%>"/>
            </form>
        </td>
    </tr>
    <%
        }
    %>


</table>
</body>
</html>
