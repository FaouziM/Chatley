<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Registreren</title>
            <link rel="stylesheet" type="text/css" href="stijl.css">
        </head>

        <body id="body">
        	<form method="POST" action="Controller?action=registreren">
        		<label for="voornaam">Voornaam: </label><input id="voornaam" name="voornaam" type="text"><br>
        		<label for="naam">Naam: </label><input id="naam" name="naam" type="text"><br>
        		<label for="email">Email-adres: </label><input id="email" name="email" type="text"><br>
        		<label for="geslacht">Geslacht: </label>
        		<select id="geslacht" name="geslacht">
        			<option value="m">Man</option>
        			<option value="v">Vrouw</option>
        		</select><br>
        		<label for="leeftijd">Leeftijd: </label><input id="leeftijd" name="leeftijd" type="number"><br>
        		<label for="passwoord">Passwoord: </label><input id="passwoord" name="passwoord" type="password"><br>
        		<label for="passwoord2">Passwoord herhalen: </label><input id="passwoord2" name="passwoord2" type="password"><br>
        		<input type="submit" value="Registreren!">
        	</form>    
        </body>
        
        </html>