<section>

    <h2>
			<c:out value="${user.voornaam}" />
	</h2>
		
</section>
<section id="status">
    <p>
        <span id="statusVeld"></span> -
        <input type="text" id="statusText"></input>
        <input id="updateStatusKnop" type="button" value="Update!" />
    </p>
</section>

<br>
<br>

<section id="contacten">
    <table id="contactenTable">
        <thead>
            <tr>
                <th colspan="2">Contacten</th>
            </tr>
        </thead>

        <tbody id="contactTableBody">
        </tbody>
    </table>
</section>
<br>

<section id="contacttoevoegen">
    <div style="background-color: red; width: 50%; margin: auto;" id="contactError"></div>
    <br>
    <input type="text" placeholder="email van vriend"  name="email"></input>
    <input type="button" id="addContactKnop" value="Voeg toe" />
</section>
<br>
<a href="Controller?action=logout">Uitloggen</a>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="JQueryChat.js"></script>
<script src="javascript.js"></script>