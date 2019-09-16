package com.iiiedu.eeit109.register.bean;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public interface IMemberDao {
    public void add(Member m) throws SQLException;
    public void update(Member m) throws SQLException;
    public void delete(int id) throws SQLException;
    public Member findById(int id) throws SQLException;
    public List<Member> queryAll() throws SQLException;
    public Member checkedUser(Member m) throws SQLException;
    public void createConn() throws SQLException, NamingException ;
    public void closeConn() throws SQLException;
}
