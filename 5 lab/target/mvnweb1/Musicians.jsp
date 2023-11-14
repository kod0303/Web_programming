<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Список музыкантов</title>
</head>
<body>
<% 
	request.setCharacterEncoding("UTF-8");
	String experience = request.getParameter("experience");
	String lang = request.getParameter("lang");
	
	if (lang == null) lang = "ru";
	if (!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
		response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
		"Параметр lang может принимать значения ru или en \"" + lang + "\"");
		return;
	}
	ResourceBundle res = ResourceBundle.getBundle("musicians", new Locale(lang));
	%>
	
	<h1> 
		<%=res.getString("title") %>
		<%=(experience == null)? " " : (res.getString("condition") + ' ' + experience) %>
	</h1>
	<%@include file="ListData.jsp"%>
</body>
</html>