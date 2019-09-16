package com.iiiedu.eeit109.order.bean;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersDao {
    public int addOrders(Orders orders) throws SQLException;
    public void addOrderItemList(List<OrderItem> orderItemList) throws SQLException;
    public void createConn() throws SQLException;
    public void closeConn() throws SQLException;
}
