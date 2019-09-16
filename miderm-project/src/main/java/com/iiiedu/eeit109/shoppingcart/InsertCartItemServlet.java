package com.iiiedu.eeit109.shoppingcart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.shoppingcart.bean.Product;
import com.iiiedu.eeit109.shoppingcart.bean.ProductDaoJdbcImpl;

@WebServlet("/shoppingcart/InsertCartItemServlet")
public class InsertCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(false);

	    if(session==null) {
	        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
	        return;
	    }
	    
	    // 會員登入成功 即可取得購物車
	    Cart cart = (Cart)session.getAttribute("cart");
	    if(cart==null) {
	        cart = new Cart();
	        session.setAttribute("cart", cart);
	    }
	    
	    int prod_id = Integer.parseInt(request.getParameter("prod_id"));
	    int count = Integer.parseInt(request.getParameter("count"));
	    
	    ProductDaoJdbcImpl pdao = new ProductDaoJdbcImpl();
	    try {
            pdao.createConn();
            Product product = pdao.findById(prod_id);
            
            // 建立購物車項目
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(count);
            
            // 加入購物車
            cart.add(cartItem);
            response.sendRedirect(request.getContextPath() + "/shoppingcart/ShoppingCartServlet");
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
