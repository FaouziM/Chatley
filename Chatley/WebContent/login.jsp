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
