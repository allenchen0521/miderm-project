package com.iiiedu.eeit109.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.register.bean.MemberDaoJdbcImpl;

@WebServlet("/admin/UpdateMemberProcessServlet")
public class UpdateMemberProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int mem_id = Integer.parseInt(request.getParameter("mem_id"));
        String mem_name = request.getParameter("mem_name");
        int mem_level = Integer.parseInt(request.getParameter("mem_level"));
        Member m = new Member();
        m.setMem_id(mem_id);
        m.setMem_name(mem_name);
        m.setMem_level(mem_level);
        
        MemberDaoJdbcImpl mdao = new MemberDaoJdbcImpl();
        try {
            mdao.createConn();
            mdao.update(m);
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
