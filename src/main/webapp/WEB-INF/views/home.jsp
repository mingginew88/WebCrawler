<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<table>
	<thead>
		<tr>
			<th>Enterprise Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dataList" items="${dataList}" varStatus="status">
			<tr>
				<td>${dataList}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


</body>
</html>
