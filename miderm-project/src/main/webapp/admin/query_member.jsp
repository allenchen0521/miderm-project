<%@page import="com.iiiedu.eeit109.register.bean.MemberDaoJdbcImpl"%>
<%@page import="com.iiiedu.eeit109.register.bean.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<% 
MemberDaoJdbcImpl mdao = new MemberDaoJdbcImpl();
mdao.createConn();
List<Member> members = mdao.queryAll();

Gson gson = new Gson();
String memberJson = gson.toJson(members);
out.print(memberJson);

mdao.closeConn();
%>