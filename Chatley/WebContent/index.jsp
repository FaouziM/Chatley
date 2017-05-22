<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Chatley</title>
            <link rel="stylesheet" type="text/css" href="stijl.css">
        </head>

        <body id="body">
            <section id="loginOfChat">
                <c:if test="${user.voornaam == null}">
                    <%@include file="login.jsp"%>
                </c:if>
                <c:if test="${user.voornaam != null}">
                    <%@include file="chat.jsp"%>
                </c:if>
            </section>

            <br>
            <hr>
            <br>

            <section id=blogSection>
                <h1>BLOG</h1>

                <div id="weer">
                    <h3>Hoe is het weer nu waar je woont?</h3>
                    <section id="weerComments">

                    </section>
                    <p>
                        <label for="weerNaam">Je naam: </label>
                        <input type=text id="weerNaam">
                        <br>
                        <label for="weerText">Het weer: </label>
                        <input type=text id="weerText">
                        <br>
                        <input type="button" id="weerSubmitKnop" value="Post!" class="reactieKnop">
                    </p>
                </div>

                <div id="film">
                    <h3>Wat is de laatste film waar je naar keek?</h3>
                    <section id="filmComments">

                    </section>
                    <p>
                        <label for="filmNaam">Je naam: </label>
                        <input type=text id="filmNaam">
                        <br>
                        <label for="filmText">Je film: </label>
                        <input type=text id="filmText">
                        <br>
                        <input type="button" id="filmSubmitKnop" value="Post!" class="reactieKnop">
                    </p>
                </div>

                <div id="muziek">
                    <h3>Wat is het laatste muzieknummer waar je naar luisterde?</h3>
                    <section id="muziekComments">

                    </section>
                    <p>
                        <label for="muziekNaam">Je naam: </label>
                        <input type=text id="muziekNaam">
                        <br>
                        <label for="muziekText">Titel en artiest: </label>
                        <input type=text id="muziekText">
                        <br>
                        <input type="button" id="muziekSubmitKnop" value="Post!" class="reactieKnop">
                    </p>
                </div>

            </section>
            <div id="chatWindowContainer"></div>
        </body>
        <footer>
            <script src="webSocketsJavascript.js"></script>
        </footer>

        </html>