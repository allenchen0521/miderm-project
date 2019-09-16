package com.iiiedu.eeit109.shoppingcart.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDaoJdbcImpl implements IProductDao {

    private DataSource ds;
    private Connection conn;

    public ProductDaoJdbcImpl() {
    }

    @Override
    public ArrayList<Product> queryAll() throws SQLException {
        String sqlstr = "Select * From product";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        ResultSet rs = state.executeQuery();

        ArrayList<Product> products = new ArrayList<Product>();
        Product product = null;
        while(rs.next()) {
            product = new Product();
            product.setProd_id(rs.getInt("prod_id"));
            product.setProd_title(rs.getString("prod_title"));
            product.setProd_price(rs.getInt("prod_price"));
            product.setProd_image(rs.getString("prod_image"));
            products.add(product);
        }
        return products;
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sqlstr = "Select * From product Where prod_id=?";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setInt(1, id);
        ResultSet rs = state.executeQuery();
        Product product = null;
        
        if(rs.next()) {
            product = new Product();
            product.setProd_id(rs.getInt("prod_id"));
            product.setProd_title(rs.getString("prod_title"));
            product.setProd_price(rs.getInt("prod_price"));
            product.setProd_image(rs.getString("prod_image"));
        }
        return product;
    }
    
    @Override
    public void createConn() throws SQLException {
        try {
            Context context = new InitialContext();
            ds = (DataSource)context.lookup("java:/comp/env/jdbc/midermproject");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
    }

    public void closeConn() throws SQLException {
        conn.close();
    }
    
}
