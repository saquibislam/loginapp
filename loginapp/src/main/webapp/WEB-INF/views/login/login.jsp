<%-- 
    Document   : index
    Created on : Jul 13, 2018, 10:32:45 AM
    Author     : saquibul.islam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>[SaqApp - Login]</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/saq-app-styles.css" />">
	<script src="<c:url value="/resources/static/jquery-3.3.1.min.js" />"></script>
	<script src="<c:url value="/resources/static/AesUtil.js" />"></script>
	<script src="<c:url value="/resources/static/aes.js" />"></script>
	<script src="<c:url value="/resources/static/pbkdf2.js" />"></script>
	<script src="<c:url value="/resources/static/login.js" />"></script>
</head>
<body>
	<div class="login-page">
		<p>
			<font color="red">${errorMessage}</font>
		</p>
        <input type="hidden" id="PassKey" value="${AES_KEY}" />
		<div class="form">
			<form class="login-form" method="post" action="login" onsubmit="return encryptLogin();" autocomplete="off">
				<input type="text" placeholder="Username" id="username" name="username" value=""/>
				<input type="password" placeholder="Password" id="password" name="password" value=""/>
				<button type="submit">login</button>
				<p class="message">
					Not registered? <a href="javascript:showRegisteration();">Create an account</a>
				</p>
			</form>
			<form name="redirect-form" style="display: none;"></form>
		</div>
	</div>
</body>
</html>