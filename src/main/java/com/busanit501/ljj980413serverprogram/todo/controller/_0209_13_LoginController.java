package com.busanit501.ljj980413serverprogram.todo.controller;


import com.busanit501.ljj980413serverprogram.todo.dto._0209_18_MemberDTO;
import com.busanit501.ljj980413serverprogram.todo.service._0209_21_MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@WebServlet(name="_0209_13_LoginController", urlPatterns = "/login_0209")
public class _0209_13_LoginController extends HttpServlet {
    // 앞에 만들었던, 멤버서비스의 기능을 의존하고, 부탁하고, 용역주기.
    _0209_21_MemberService memberService = _0209_21_MemberService.INSTANCE;

    // 로그인 화면 필요.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("로그인 화면을 제공하는 컨트롤러입니다.");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }
    // 로그인 처리 필요.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("로그인 처리를 담당하는 doPost 입니다. ");

        // 화면에서, 전달받은 mid, mpw 정보를 가져오기. 무조건 문자열이다.
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        //===================================================================

        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");
        try {
            _0209_18_MemberDTO memberDTO = memberService.login(mid, mpw);
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                log.info("생성된 uuid 값 확인: " + uuid);
                memberService.updateUuid(mid,uuid);

                memberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);

                rememberCookie.setMaxAge(60 * 60 * 24 * 7);
                rememberCookie.setPath("/");

                resp.addCookie(rememberCookie);
            }

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list_0209");
        } catch (Exception e) {
           resp.sendRedirect("/login_0209?result=error");
        }

        //===================================================================
    } //doPost
}
