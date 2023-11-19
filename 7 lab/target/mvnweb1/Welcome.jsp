<%@ page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Введите стаж работы</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<form method = get action = "ExperienceProcessor">
		<h2>Введите максимальный<br>стаж работы</h2>
		<input type = "text" name = "experience"
		<% 
			Cookie [] c = request.getCookies();
			if(c != null)
				for(int i = 0; i < c.length; i++)
					if("experience".equals(c[i].getName())) {
					// Запись значения в поле ввода если найден Cookie
					out.print(" value='" + URLDecoder.decode(c[i].getValue(), "UTF-8") + "' ");
					break;
					}
		%>
		> <br>
		<input type = "submit" value = "Ввод">
		</form>
</body>
</html>