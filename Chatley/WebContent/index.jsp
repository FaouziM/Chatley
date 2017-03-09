<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Chatley</title>
    <link rel="stylesheet" type="text/css" href="stijl.css">
    <script src="javascript.js"></script>
</head>

<body onload="getStatus()">
    <section id="status">
        <p>
            <span id="statusVeld"></span>
            <input type="text" id="statustext"></input>
            <input type="button" value="Update!" />
        </p>
    </section>

    <br>
    <br>

    <section id="contacten">
        <table>
            <tr>
                <th colspan="2">Contacten</th>
            </tr>
            <tr>
                <td>Stanley</td>
                <td>Online</td>
            </tr>
        </table>
    </section>

    <br>
    <br>

    <section id="contacttoevoegen">
        <input type="text" id="contactnaam"></input>
        <input type="button" value="Voeg toe" />
    </section>

</body>

</html>