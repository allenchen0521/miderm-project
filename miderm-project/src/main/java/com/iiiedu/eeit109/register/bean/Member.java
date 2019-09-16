package com.iiiedu.eeit109.register.bean;

import java.io.Serializable;

public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    private int mem_id;
    private String mem_username;
    private String mem_password;
    private String mem_name;
    private int mem_level;

    public Member() {
    }
    
    public Member(String mem_username, String mem_password, String mem_name) {
        this.mem_username = mem_username;
        this.mem_password = mem_password;
        this.mem_name = mem_name;
    }

    public int getMem_id() {
        return mem_id;
    }

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_username() {
        return mem_username;
    }

    public void setMem_username(String mem_username) {
        this.mem_username = mem_username;
    }

    public String getMem_password() {
        return mem_password;
    }

    public void setMem_password(String mem_password) {
        this.mem_password = mem_password;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public int getMem_level() {
        return mem_level;
    }

    public void setMem_level(int mem_level) {
        this.mem_level = mem_level;
    }

}
