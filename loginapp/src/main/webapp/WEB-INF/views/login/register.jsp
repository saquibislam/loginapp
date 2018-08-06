<%-- 
    Document   : index
    Created on : Jul 13, 2018, 10:32:45 AM
    Author     : saquibul.islam
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[SaqApp - Registration]</title>
	<link rel="stylesheet" type="text/css" href="<spring:url value="/resources/static/css/saq-app-styles.css" />">
	<script src="<spring:url value="/resources/static/jquery-3.3.1.min.js" />"></script>
	<script src="<spring:url value="/resources/static/AesUtil.js" />"></script>
	<script src="<spring:url value="/resources/static/aes.js" />"></script>
	<script src="<spring:url value="/resources/static/pbkdf2.js" />"></script>
	<script src="<spring:url value="/resources/static/login.js" />"></script>
</head>
<body onload="onLoadRegistrationPage();" style="overflow: hidden;">
	<div class="login-page" style="padding: 2% 0 0">
		<p>
			<font color="${msgColor}">${messageDesc}</font>
		</p>
        <input type="hidden" id="PassKey" value="${AES_KEY}" />
		<div class="form">
			<form:form cssClass="register-form" action="register" method="post"
				onsubmit="return validateRegistration();" modelAttribute="employee" autocomplete="off">
				
				<form:input type="text" placeholder="Username" id="username" path="username" />
				<form:input type="password" placeholder="Password" id="password" path="password" />
				<form:input type="text" placeholder="Employee Name" id="emp_name" path="name" />
				<form:input type="text" placeholder="Salary" id="salary" path="salary" />
				<form:select name="city" path="address.city">
					<option value="">---Select City---</option>
					<form:options items="${cityList}" />
				</form:select>
				<form:input type="text" placeholder="Pin Code" id="pinCode" path="address.pinCode" />
				<button>create</button>
				<p class="message">
					Already registered? <a href="javascript:showLogin();">Sign In</a>
				</p>
				
			</form:form>
			<form name="redirect-form" style="display: none;"></form>
		</div>
	</div>
</body>
</html>