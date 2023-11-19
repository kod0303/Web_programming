<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Список музыкантов</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<% 
	request.setCharacterEncoding("UTF-8");
	String experience = (String)session.getAttribute("experience");
	String lang = request.getParameter("lang");
	
	if (lang == null) lang = "ru";
	if (!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
		response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
		"Параметр lang может принимать значения en или ru \"" + lang + "\"");
		return;
	}
	if (experience.equals("")) experience = null;
	ResourceBundle res = ResourceBundle.getBundle("Musicians", new Locale(lang));
	%>
	<h1> 
		<%=res.getString("title") %>
		<%=(experience == null)? " " : (res.getString("condition") + " " + experience) %>
	</h1>
	<%
	Object[][] list_of_musicians = new Object[][] {
		{"Иванов Юрий", "Альт", 3}, 
		{"Ежова Арина", "Скрипка", 5}, 
		{"Гришин Артем", "Труба", 9}
		};
	%>
	<table border='1'>
	<tr>
		<td><b><%=res.getString("name") %></b></td>
		<td><b><%=res.getString("instrument") %></b></td>
		<td><b><%=res.getString("experience") %></b></td>
	</tr>
	<%
	for (Object[] temp : list_of_musicians)
		 if (experience == null || (int)temp[2] <= Integer.parseInt(experience))
			 out.println("<tr><td>" + temp[0] + "</td><td>" + temp[1] + "</td><td>"
		     + Integer.toString((int)temp[2]) + "</td></tr>");
	%>
	</table>
</body>
</html>
