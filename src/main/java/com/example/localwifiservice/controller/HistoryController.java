package com.example.localwifiservice.controller;

import com.example.localwifiservice.repository.HistoryRepository;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/history")
public class HistoryController extends HttpServlet {

    HistoryRepository historyRepository = HistoryRepository.getHistoryRepository();

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        historyRepository.deleteHistory(id);
        response.sendRedirect("history.jsp");
    }

}
