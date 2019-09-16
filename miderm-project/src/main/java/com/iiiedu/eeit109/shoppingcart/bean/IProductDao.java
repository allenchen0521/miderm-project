package com.iiiedu.eeit109.shoppingcart.bean;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductDao {
    public ArrayList<Product> queryAll() throws SQLException;
    public Product findById(int id) throws SQLException;
    public void createConn() throws SQLException;
    public void closeConn() throws SQLException;
}
