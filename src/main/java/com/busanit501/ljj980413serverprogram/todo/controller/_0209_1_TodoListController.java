package com.busanit501.ljj980413serverprogram.todo.controller;

import com.busanit501.ljj980413serverprogram.todo.dto._0209_6_TodoDTO;
import com.busanit501.ljj980413serverprogram.todo.service._0209_2_TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "_0209_1_TodoListController", urlPatterns = "/todo/list_0209")
@Log4j2
public class _0209_1_TodoListController extends HttpServlet {

    private _0209_2_TodoService todoService = _0209_2_TodoService.INSTANCE;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("===전체 목록 조회=====");
        try {
            ServletContext context = getServletContext();

            String adminEmail = context.getInitParameter("adminEmail");

            context.setAttribute("globalMessage", "안녕하세요, 모두와 공유하는 메시지입니다.");

            req.setAttribute("adminEmailDirect", adminEmail);

            // DB로 데이터를 가져오기.
            List<_0209_6_TodoDTO> dtoList = todoService.listAll();

            req.setAttribute("dtoList", dtoList);

            req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
                    .forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }
}
