package com.iiiedu.eeit109.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiiedu.eeit109.order.bean.OrderItem;
import com.iiiedu.eeit109.order.bean.Orders;
import com.iiiedu.eeit109.order.bean.OrdersDaoJdbcImpl;
import com.iiiedu.eeit109.register.bean.Member;
import com.iiiedu.eeit109.shoppingcart.Cart;
import com.iiiedu.eeit109.shoppingcart.CartItem;

@WebServlet("/shoppingcart/InsertOrderServlet")
public class InsertOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session == null) {
		    System.out.println("session is null");
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp");
		    return;
		}
		
		Member member = (Member)session.getAttribute("Member");
		if(member == null) {
		    System.out.println("session is null");
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
		}

		// 取得購物車
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
            System.out.println("session is null");
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
        }
		String address = request.getParameter("address");
		
		// 於購物車建立訂單
		Orders orders = new Orders();
		orders.setOrder_time(new Date());
		orders.setTotal(cart.getTotal());

		System.out.println("memberID:" + member.getMem_id());
		orders.setMember(member);
		orders.setAddress(address);
		
		OrdersDaoJdbcImpl odao = new OrdersDaoJdbcImpl();
		try {
		    odao.createConn();
		    // 取得新增成功的訂單編號
            int order_id = odao.addOrders(orders);
            orders.setOrder_id(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		// 欲購物車項目建立訂單項目
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(CartItem cartItem:cart.getCartItemList()) {
		    OrderItem orderItem = new OrderItem();
		    orderItem.setProduct(cartItem.getProduct());
		    orderItem.setOrderitem_count(cartItem.getCount());
		    orderItem.setSubtotal(cartItem.getSubtotal());
		    orderItem.setOrders(orders);
		    orderItemList.add(orderItem);
		}
		
		session.removeAttribute("cart");
		try {
            odao.addOrderItemList(orderItemList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                odao.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		orders.setOrderItemlist(orderItemList);

		session.setAttribute("orders", orders);
		response.sendRedirect(request.getContextPath() + "/shoppingcart/showorder.jsp");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
