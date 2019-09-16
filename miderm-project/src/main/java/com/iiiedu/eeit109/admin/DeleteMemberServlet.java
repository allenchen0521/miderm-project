package com.iiiedu.eeit109.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.register.bean.MemberDaoJdbcImpl;

@WebServlet("/admin/DeleteMemberServlet.do")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int mem_id = Integer.parseInt(request.getParameter("mem_id"));
	    System.out.println("mem_id:"+mem_id);
	    MemberDaoJdbcImpl mdao = new MemberDaoJdbcImpl();
	    try {
            mdao.createConn();
            Member member = mdao.findById(mem_id);
            request.setAttribute("member", member);
            RequestDispatcher rd = request.getRequestDispatcher("admin_deleteMember.jsp");
            rd.forward(request, response);
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
