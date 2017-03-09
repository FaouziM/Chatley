<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Chatley</title>
</head>
<body>
<c:if test="${fout != null}">
	<div style="background-color: red">
		<c:out value="${fout}" />
	</div>
</c:if>
<section id="loginsection">
    <form method="post" action="Controller?action=login">
        <p>
            <label for="username">Username: </label>
            <input id="username" name="username" type="text" />
        </p>
        <p>
            <label for="password">Password: </label>
            <input id="password" name="password" type="text" />
        </p>
        <input type="submit" />
    </form>
</section>
</body>
</html>