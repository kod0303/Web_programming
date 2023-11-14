<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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