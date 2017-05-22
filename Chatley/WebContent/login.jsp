<c:if test="${fout != null}">
	<div style="background-color: red">
		<c:out value="${fout}" />
	</div>
</c:if>
<section id="loginsection">
    <form method="post" action="Controller?action=login">
        <p>
            <label for="username">Email: </label>
            <input id="username" name="email" type="text" />
        </p>
        <p>
            <label for="password">Password: </label>
            <input id="password" name="password" type="text" />
        </p>
        <input type="submit" />
    </form>
    
    <a href="Controller?action=getRegisterPage">Registreren</a>
</section>
