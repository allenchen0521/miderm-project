package com.iiiedu.eeit109.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.iiiedu.eeit109.register.bean.Member;

public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    private int order_id;
    private Date order_time;
    private int total;
    private Member member;
    private String address;
    
    private List<OrderItem> orderItemlist;

    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public Date getOrder_time() {
        return order_time;
    }
    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<OrderItem> getOrderItemlist() {
        return orderItemlist;
    }
    public void setOrderItemlist(List<OrderItem> orderItemlist) {
        this.orderItemlist = orderItemlist;
    }

}
