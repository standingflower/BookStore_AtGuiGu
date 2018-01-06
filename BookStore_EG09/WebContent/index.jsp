<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	index.jsp现在没什么用，我们希望当你去访问index.jsp时，
	直接就转发去到
	http://localhost:8080/BookStore_EG05/client/BookClientServlet?method=findBook
	
	现在只有一访问index.jsp直接转发BookClientServlet?method=findBook
 --%>

<jsp:forward page="/client/BookClientServlet?method=findBook"></jsp:forward>
 
