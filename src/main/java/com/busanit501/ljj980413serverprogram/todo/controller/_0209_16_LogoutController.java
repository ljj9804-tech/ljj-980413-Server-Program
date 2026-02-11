package com.busanit501.ljj980413serverprogram.todo.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name="_0209_16_LogoutController", urlPatterns = "/logout_0209")
public class _0209_16_LogoutController extends HttpServlet {
    // 로그 아웃 처리.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("로그아웃 처리를 담당하는 doPost 입니다. ");

        HttpSession session = req.getSession();

        session.removeAttribute("loginInfo");

        session.invalidate();

        resp.sendRedirect("/login_0209");

    }
}
