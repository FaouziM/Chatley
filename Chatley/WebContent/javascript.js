var statusRequest = new XMLHttpRequest();
var updateStatusRequest = new XMLHttpRequest();
var addVriendRequest = new XMLHttpRequest();
var getFriendRequest = new XMLHttpRequest();
//status 0

var body = document.getElementsByTagName("body")[0];
initieer();
//body.onload = (function() {updateStatus(true)});
//anonymous function
//waarom? omdat functie(arg) niet valt te passen als callback


//einde html dingetjes en globale variabelen
//------------------------------------------------------------------------------------
//begin statusget en update gedeelte

function initieer() {
    var updateKnop = document.getElementById("updateStatusKnop");
    var addVriendKnop = document.getElementById("addContactKnop");

    updateKnop.onclick = updateStatus;
    addVriendKnop.onclick = addVriend;
    getStatus();
    getVrienden();
}



function updateStatus() {
    var statusVeld = document.getElementById("statusText");
    var statusText = statusVeld.value;
    statusVeld.value = "";
    var info = "nieuweStatus=" + encodeURIComponent(statusText);
    updateStatusRequest.open("POST", "Controller?action=updateStatus", true);
    updateStatusRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    updateStatusRequest.send(info);
    updateStatusRequest.onreadystatechange = getStatusDataGeenTimeout;
}

function getStatusDataGeenTimeout() {
    if (updateStatusRequest.readyState === 4) {
        //status 4
        if (updateStatusRequest.status === 200) {
            var response = updateStatusRequest.responseText;
            var statusVeld = document.getElementById("statusVeld");
            statusVeld.innerHTML = response;
        }
    }
}

function getStatus() {
    statusRequest.open("GET", "Controller?action=getStatus", true);
    //status 1

    statusRequest.onreadystatechange = getStatusData;
    statusRequest.send(null);
    //status 2
}
//status 3
function getStatusData() {
    if (statusRequest.readyState === 4) {
        //status 4
        if (statusRequest.status === 200) {
            var response = statusRequest.responseText;
            var statusVeld = document.getElementById("statusVeld");
            statusVeld.innerHTML = response;
            setTimeout("getStatus()", 6000);
        }
    }
}

//einde status gedeelte
//-----------------------------------------------------------------------------
//begin add vriend gedeelte

function addVriend() {
    document.getElementById("contactError").value = "";
    var contactnaamveld = document.getElementById("contactnaam");
    var contactnaamtext = contactnaamveld.value;
    contactnaamveld.value = "";
    var info = "email=" + encodeURIComponent(contactnaamtext);
    addVriendRequest.open("POST", "Controller?action=addVriend", true);
    addVriendRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    addVriendRequest.send(info);
    addVriendRequest.onreadystatechange = getContactLijstData;
}

function getContactLijstData() {
    if (addVriendRequest.readyState === 4) {
        if (addVriendRequest.status === 200) {
            var response = JSON.parse(addVriendRequest.responseText);
            if (response.hasOwnProperty("Error")) {
                document.getElementById("contactError").innerHTML = response["Error"];
                return;
            }

            document.getElementById("contactError").innerHTML = "";
            var contactenLijstTableBody = document.getElementById("contactTableBody");
            //contactentable leegmaken
            var aantalRijen = contactenLijstTableBody.rows.length;
            for (var i = 0; i < aantalRijen; i++) {
                contactenLijstTableBody.deleteRow(0);
            }
            for (var j = 0; j < response.Person.length; j++) {
                var rij = contactenLijstTableBody.insertRow();
                var vak1 = rij.insertCell(0);
                var vak2 = rij.insertCell(1);
                vak1.className = "persoonNaam";
                vak1.innerHTML = response.Person[j]["naam"];
                vak2.innerHTML = response.Person[j]["status"];
                maakNamenClickable();
            }

        }
    }
}

function getVrienden() {
    getFriendRequest.open("GET", "Controller?action=getFriends", true);
    getFriendRequest.send();
    getFriendRequest.onreadystatechange = getContactLijstDataMetTimeout;
}

function getContactLijstDataMetTimeout() {
    if (getFriendRequest.readyState === 4) {
        if (getFriendRequest.status === 200) {
            var response = JSON.parse(getFriendRequest.responseText);
            var contactenLijstTableBody = document.getElementById("contactTableBody");
            //contactentable leegmaken
            var aantalRijen = contactenLijstTableBody.rows.length;
            for (var i = 0; i < aantalRijen; i++) {
                contactenLijstTableBody.deleteRow(0);
            }

            for (var j = 0; j < response.Person.length; j++) {
                var rij = contactenLijstTableBody.insertRow();
                var vak1 = rij.insertCell(0);
                var vak2 = rij.insertCell(1);
                vak1.className = "persoonNaam";
                vak1.innerHTML = response.Person[j]["naam"];
                vak2.innerHTML = response.Person[j]["status"];
                maakNamenClickable();
            }
        }
        setTimeout("getVrienden()", 5000);
    }
}