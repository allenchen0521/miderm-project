package com.iiiedu.eeit109.order.bean;

import java.io.Serializable;

import com.iiiedu.eeit109.shoppingcart.bean.Product;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private int orderitem_id;
    private Product product;
    private int orderitem_count;
    private int subtotal;
    private Orders orders;

    public int getOrderitem_id() {
        return orderitem_id;
    }
    public void setOrderitem_id(int orderitem_id) {
        this.orderitem_id = orderitem_id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getOrderitem_count() {
        return orderitem_count;
    }
    public void setOrderitem_count(int orderitem_count) {
        this.orderitem_count = orderitem_count;
    }
    public int getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
    public Orders getOrders() {
        return orders;
    }
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
