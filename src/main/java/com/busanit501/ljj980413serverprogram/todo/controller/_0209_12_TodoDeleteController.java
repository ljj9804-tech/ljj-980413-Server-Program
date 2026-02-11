package com.busanit501.ljj980413serverprogram.todo.controller;


import com.busanit501.ljj980413serverprogram.todo.service._0209_2_TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name="_0209_12_TodoDeleteController", urlPatterns = "/todo/delete_0209")
public class _0209_12_TodoDeleteController extends HttpServlet {
    // 삭제 처리

    private _0209_2_TodoService todoService = _0209_2_TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String tnoStr = req.getParameter("tno");
        Long tno = Long.parseLong(tnoStr);

        try {
            todoService.remove(tno);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("삭제 오류");
        }
    resp.sendRedirect("/todo/list_0209");
    }

}
