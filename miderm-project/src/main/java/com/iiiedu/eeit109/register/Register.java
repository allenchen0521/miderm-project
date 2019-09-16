package com.iiiedu.eeit109.register;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.register.bean.MemberDaoJdbcImpl;

@WebServlet("/register/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mem_username = request.getParameter("mem_username");
		String mem_password = request.getParameter("mem_password");
		String mem_name = request.getParameter("mem_name");
		String doubleCheck = request.getParameter("doubleCheck");
		HashMap<String,String> errorMsg = new HashMap<String,String>();
		
		if(mem_username == null || mem_username.length() == 0) {
            errorMsg.put("errUserName","請輸入您的帳號");
        }
		
		if(mem_name == null || mem_name.length() == 0) {
            errorMsg.put("errName","請輸入您的姓名");
        }
        
        if(mem_password == null || mem_password.length() == 0) {
            errorMsg.put("errPassword","請輸入您的密碼");
        }
        
        if(doubleCheck == null || !doubleCheck.equals(mem_password)) {
            errorMsg.put("errCheck","驗證密碼不正確");
        }
        
        if(errorMsg.size()!=0) {
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }
        
        MemberDaoJdbcImpl mdao = new MemberDaoJdbcImpl();
        try {
            mdao.createConn();
            Member member = new Member(mem_username,mem_password,mem_name);
            mdao.add(member);
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
