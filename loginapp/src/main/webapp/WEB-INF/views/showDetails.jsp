<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
<style type="text/css">
	table, tr, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
	td {
		text-align: left;
	}
</style>
</head>
<body>
	<div style="width: 90%; margin-left: auto; margin-right: auto; margin-top: 100px; text-align: center;">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Salary</th>
				<th>Address</th>
			</tr>
			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.salary}</td>
					<td>${employee.address.city} - ${employee.address.pinCode}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>