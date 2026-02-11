package com.busanit501.ljj980413serverprogram.todo.controller;

import com.busanit501.ljj980413serverprogram.todo.dto._0209_18_MemberDTO;
import com.busanit501.ljj980413serverprogram.todo.service._0209_21_MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "_0209_22_MamberRegController", urlPatterns = "/member/register")
public class _0209_22_MamberRegController extends HttpServlet {

    private _0209_21_MemberService memberService = _0209_21_MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("회원가입 화면 요청(GET)");
        req.getRequestDispatcher("/WEB-INF/todo/memberReg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        log.info("회원가입 처리 중(POST)");

        _0209_18_MemberDTO dto = _0209_18_MemberDTO.builder()
                .mid(req.getParameter("mid"))
                .mpw(req.getParameter("mpw"))
                .mname(req.getParameter("mname"))
                .build();

        try {

            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");

            memberService.register(dto);
            log.info("회원가입 성공: " + dto.getMname());

            // 가입 성공 후 로그인 페이지로 리다이렉트
            resp.sendRedirect("/login_0209");
        } catch (Exception e) {
            log.error("회원가입 에러: " + e.getMessage());
            resp.sendRedirect("/member/register?error=fail");
        }
    }
}