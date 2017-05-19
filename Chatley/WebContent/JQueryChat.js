$(document).ready(function () {
    maakNamenClickable();
    setTimeout(function () {
        startPollingNaarNieuweBerichten();
    }, 3000);
});


function maakNamenClickable() {
    $(".persoonNaam").off("click"); //haal alle "click" event handlers af van een naam
    $(".persoonNaam").on("click", function () { //zet 1 click event handler op de naam
        openChatWindow(this.innerHTML);
    });
}

function openChatWindow(naamPartner) {
    console.log(naamPartner);

    if (!isErAlEenChatWindow(naamPartner)) {
        var chatWindow = document.createElement("div");
        chatWindow.className = "chatWindow";
        chatWindow.setAttribute("id", naamPartner + "Chat");

        var navbar = document.createElement("div");
        navbar.className = "chatWindowNavBar";
        chatWindow.appendChild(navbar);

        var contactNaamVeld = document.createElement("span");
        contactNaamVeld.innerHTML = naamPartner;
        navbar.appendChild(contactNaamVeld);

        var closeButton = document.createElement("div");
        closeButton.className = "closeButton";
        closeButton.innerHTML = "X";
        laatCloseButtonWerken(closeButton);
        navbar.appendChild(closeButton);

        var berichtenVak = document.createElement("div");
        berichtenVak.className = "berichtenVak";
        chatWindow.appendChild(berichtenVak);

        var inputDiv = document.createElement("div");
        inputDiv.className = "inputDiv";
        chatWindow.appendChild(inputDiv);

        var inputField = document.createElement("textarea");
        inputField.className = "inputField";
        inputDiv.appendChild(inputField);

        var sendButton = document.createElement("button");
        sendButton.className = "sendButton";
        sendButton.innerHTML = "Zend!";
        sendButton.addEventListener("click", function () {
            zendBericht(sendButton, naamPartner);
        });

        inputDiv.appendChild(sendButton);


        $("#chatWindowContainer").append(chatWindow);
        getOudeBerichten(naamPartner);
    }

}

function zendBericht(sendButton, naamPartner) {
    var textVeld = sendButton.parentNode.getElementsByClassName("inputField")[0];
    var berichtText = textVeld.value;
    textVeld.value = "";
    var berichtObject = new Object;
    berichtObject.bericht = berichtText;
    berichtObject.ontvanger = naamPartner;
    var berichtObjectJSON = JSON.stringify(berichtObject);
    console.log(berichtObjectJSON);
}

function getOudeBerichten(naam) {
    $.ajax({
        url: "Controller?action=getOudeBerichten&partner=" + naam,
        type: "GET",
        success: function (json) {
            console.log(json);
            ontvangChatBericht(json, naam);
        }
    })
}

function ontvangChatBericht(json, naamPartner) {
    var berichten = $.parseJSON(json);
    //voorbeeld JSON bericht : { "Bericht":[{"zender": "Bob","ontvanger": "Fao","bericht": "hoi"}]}

    for (var i = 0; i < berichten.Bericht.length; i++) {
        addBerichtBijVenster(berichten.Bericht[i], naamPartner);
    }
}

function addBerichtBijVenster(berichtObject, naamPartner) {
    var bericht = berichtObject["bericht"];
    var ontvanger = berichtObject["ontvanger"];
    var zender = berichtObject["zender"];

    var berichtenRuimte = document.getElementById(naamPartner + "Chat").getElementsByClassName("berichtenVak")[0];
    var berichtDiv = document.createElement("div");
    if (zender === naamPartner) {
        berichtDiv.className = "berichtVanPartner";
    } else {
        berichtDiv.className = "berichtVanMij";
    }
    berichtDiv.className += " berichtDiv";
    berichtDiv.innerHTML = bericht;
    berichtenRuimte.appendChild(berichtDiv);
}

function startPollingNaarNieuweBerichten() {
    var contactLijstNamen = document.getElementById("contactTableBody").getElementsByClassName("persoonNaam");
    for (var i = 0; i < contactLijstNamen.length; i++) {
        pollNaarNieuweBerichten(contactLijstNamen[i].innerHTML);
    }
}

function pollNaarNieuweBerichten(naamPartner) {
    $.ajax({
        url: "Controller?action=getNieuweBerichten&partner=" + naamPartner,
        type: "GET",
        success: function (json) {
            ontvangChatBericht(json, naamPartner);
        }
    });
    setTimeout(function () {
        pollNaarNieuweBerichten(naamPartner);
    }, 2000);
}


function isErAlEenChatWindow(naam) {
    var chatWindows = document.getElementsByClassName("chatWindow");
    var volledigeNaam = naam + "Chat";

    console.log("Vlak voor loop");
    for (var i = 0; i < chatWindows.length; i++) {
        if (chatWindows[i].getAttribute("id") === volledigeNaam) {
            return true;
        }
    }
    return false;

}

function laatCloseButtonWerken(closeButton) {
    closeButton.addEventListener("click", function () {
        sluitChatVenster(closeButton);
    });
}

function sluitChatVenster(closeButton) {
    var parentParent = closeButton.parentNode.parentNode;
    parentParent.parentNode.removeChild(parentParent);
    //var IdVanDezeVenster = parentParent.id;
    //var naam = parentParent.getElementsByTagName("span")[0].innerHTML;
    //console.log("sluit venster met naam: " + naam);
}