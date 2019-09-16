package com.iiiedu.eeit109.register.bean;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDaoJdbcImpl implements IMemberDao {

    private DataSource ds;
    private Connection conn;

    public MemberDaoJdbcImpl() {
    }

    @Override
    public void add(Member m) throws SQLException {
        String sqlstr = "Insert into Member(mem_username, mem_password, mem_name) "
                + "Values(?,?,?)";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setString(1, m.getMem_username());
        state.setString(2, m.getMem_password());
        state.setString(3, m.getMem_name());

        state.executeUpdate();
        state.close();
    }

    @Override
    public void update(Member m) throws SQLException {
        String sqlstr = "Update Member Set mem_name=?, mem_level=? Where mem_id=?";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setString(1, m.getMem_name());
        state.setInt(2, m.getMem_level());
        state.setInt(3, m.getMem_id());

        state.executeUpdate();
        state.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlstr = "Delete From Member Where mem_id=?";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setInt(1, id);
        
        state.executeUpdate();
        state.close();
    }
    
    @Override
    public Member findById(int id) throws SQLException {
        String sqlstr = "Select * From member Where mem_id=?";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setInt(1, id);
        ResultSet rs = state.executeQuery();
        
        Member member = null;
        if(rs.next()) {
            member = new Member();
            member.setMem_id(rs.getInt("mem_id"));
            member.setMem_username(rs.getString("mem_username"));
            member.setMem_name(rs.getString("mem_name"));
            member.setMem_level(rs.getInt("mem_level"));
        }
        
        return member;
    }

    @Override
    public List<Member> queryAll() throws SQLException {
        String sqlstr = "Select * From Member";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        ResultSet rs = state.executeQuery();
        
        List<Member> members = new ArrayList<Member>();
        Member member = null;
        while(rs.next()) {
            member = new Member();
            member.setMem_id(rs.getInt("mem_id"));
            member.setMem_username(rs.getString("mem_username"));
            member.setMem_password(rs.getString("mem_password"));
            member.setMem_name(rs.getString("mem_name"));
            member.setMem_level(rs.getInt("mem_level"));
            members.add(member);
        }
        rs.close();
        state.close();
        return members;
    }
    
    @Override
    public Member checkedUser(Member m) throws SQLException {
        String sqlstr = "Select * From member Where mem_username=? and mem_password=?";
        PreparedStatement state = conn.prepareStatement(sqlstr);
        state.setString(1, m.getMem_username());
        state.setString(2, m.getMem_password());
        ResultSet rs = state.executeQuery();
        Member member = null;
        if(rs.next()) {
            member = new Member();
            member.setMem_id(rs.getInt("mem_id"));
            member.setMem_username(rs.getString("mem_username"));
            member.setMem_name(rs.getString("mem_name"));
            member.setMem_level(rs.getInt("mem_level"));
        }
        rs.close();
        state.close();
        return member;
    }

    @Override
    public void createConn() throws SQLException, NamingException {
        Context context = new InitialContext();
        ds = (DataSource)context.lookup("java:/comp/env/jdbc/midermproject");
        conn = ds.getConnection();
    }

    @Override
    public void closeConn() throws SQLException {
        conn.close();
    }

}
