package com.iiiedu.eeit109.order.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class OrdersDaoJdbcImpl implements IOrdersDao{

    private DataSource ds;
    private Connection conn;

    @Override
    public int addOrders(Orders orders) throws SQLException {
        String sqlstr = "Insert into orders(order_time,total,mem_id,address) values(?,?,?,?)";
        PreparedStatement state = conn.prepareStatement(sqlstr,Statement.RETURN_GENERATED_KEYS);

        Timestamp timestamp = new Timestamp(orders.getOrder_time().getTime());
        state.setTimestamp(1, timestamp);
        state.setInt(2, orders.getTotal());
        state.setInt(3, orders.getMember().getMem_id());
        state.setString(4, orders.getAddress());
        state.executeUpdate();
        
        int id=0;
        ResultSet generatedKeys = state.getGeneratedKeys();
        if(generatedKeys.next()) { id=generatedKeys.getInt(1);}

        state.close();
        return id;
    }

    @Override
    public void addOrderItemList(List<OrderItem> orderItemList) throws SQLException {
        String sqlstr = "Insert into orderitem(prod_id,orderitem_count,subtotal,order_id) values(?,?,?,?)";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        
        for(OrderItem orderItem:orderItemList) {
            state.setInt(1, orderItem.getProduct().getProd_id());
            state.setInt(2, orderItem.getOrderitem_count());
            state.setInt(3, orderItem.getSubtotal());
            state.setInt(4, orderItem.getOrders().getOrder_id());
            state.executeUpdate();
        }

        state.close();
    }

    @Override
    public void createConn() throws SQLException {
        try {
            InitialContext context = new InitialContext();
            ds =(DataSource)context.lookup("java:/comp/env/jdbc/midermproject");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void closeConn() throws SQLException {
        conn.close();
    }
    
}
