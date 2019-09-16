package com.iiiedu.eeit109.login;

import java.io.IOException;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.register.bean.MemberDaoJdbcImpl;

@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mem_username = request.getParameter("mem_username");
        String mem_password = request.getParameter("mem_password");
        
        HashMap<String,String> errorMsg = new HashMap<String,String>();
        String requestUrl = "";
        if(mem_username == null || mem_username.length() == 0) {
            errorMsg.put("errUserName","請輸入您的帳號");
        }
        
        if(mem_password == null || mem_password.length() == 0) {
            errorMsg.put("errPassword","請輸入您的密碼");
        }
        
        if(errorMsg.size()!=0) {
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }
        
        // Check User
        MemberDaoJdbcImpl mdao = new MemberDaoJdbcImpl();
        try {
            mdao.createConn();
            Member member = new Member();
            member.setMem_username(mem_username);
            member.setMem_password(mem_password);
            Member checkMember = mdao.checkedUser(member);
            if(checkMember != null ) {
                System.out.println("success");
                HttpSession session = request.getSession(false);
                session.setAttribute("Member", checkMember);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                System.out.println("failed");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                return;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                mdao.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
