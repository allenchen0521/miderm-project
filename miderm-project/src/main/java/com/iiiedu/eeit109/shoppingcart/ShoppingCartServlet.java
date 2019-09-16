package com.iiiedu.eeit109.shoppingcart;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiiedu.eeit109.shoppingcart.bean.Product;
import com.iiiedu.eeit109.shoppingcart.bean.ProductDaoJdbcImpl;

@WebServlet("/shoppingcart/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProductDaoJdbcImpl pdao = new ProductDaoJdbcImpl();
	    try {
            pdao.createConn();
            ArrayList<Product> products = pdao.queryAll();
            request.setAttribute("products", products);
            RequestDispatcher rd = request.getRequestDispatcher("/shoppingcart/shoppingcart.jsp");
            rd.forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pdao.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
