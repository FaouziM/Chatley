var socket;
//var pathArray = window.location.href.split('Chatley');
//var pathP = pathArray[0] + '/' + pathArray[1];
var path = "localhost:8080/Chatley/";
//console.log(pathP);
initieerWebSocket();

function initieerWebSocket() {
    socket = new WebSocket("ws://" + path + "blog");

    socket.onmessage = function (event) {
        getReactie(event);
    };

    socket.onopen = function (event) {
        //er moet nog niks gebeuren
    }

    socket.onclose = function (event) {
        //er moet nog niks gebeuren
        //misschien gebruiker inlichten als socket dicht is
    }

    socket.onerror = function (event) {
        //er moet nog niks gebeuren
    }

    var reactieKnoppen = document.getElementsByClassName("reactieKnop");

    for (var i = 0; i < reactieKnoppen.length; i++) {
        reactieKnoppen[i].topic = reactieKnoppen[i].parentNode.parentNode.id;
        reactieKnoppen[i].onclick = function (event) {
            zendReactie(event);
        };
    };
}

function zendReactie(event) {
    var onderwerp = event.target.topic;


    var teVerzenden = {};
    teVerzenden["onderwerp"] = onderwerp;

    var idVanTeGettenNaam = onderwerp + "Naam";
    var naamTextVak = document.getElementById(idVanTeGettenNaam)
    teVerzenden["naam"] = naamTextVak.value;
    naamTextVak.value = "";

    var idVanTeGettenText = onderwerp + "Text";
    var textTextVak = document.getElementById(idVanTeGettenText);
    teVerzenden["text"] = textTextVak.value;
    textTextVak.value = "";

    var jsonAlsString = JSON.stringify(teVerzenden);
    //voorbeeld JSON : {"onderwerp":"film","naam":"boiki","text":"opopopo"}
    console.log(jsonAlsString);

    socket.send(jsonAlsString);
}

function getReactie(event) {
    var ontvangenJSON = event.data;
    var ontvangenData = JSON.parse(ontvangenJSON);
    addReactieDiv(ontvangenData);
    /*
    var ontvangenNaam = ontvangenData["naam"];
    var ontvangenOnderwerp = ontvangenData["onderwerp"];
    var ontvangenText = ontvangenData["text"];
    */

}

function addReactieDiv(data) {
    var div = document.createElement("div");
    div.className = "commentDiv";

    var pNaam = document.createElement("p");
    pNaam.innerHTML = data["naam"] + " zei:"
    pNaam.className = "naamP";
    div.appendChild(pNaam);

    var pText = document.createElement("p");
    pText.innerHTML = data["text"];
    pText.className = "textP";
    div.appendChild(pText);

    var onderwerpPlusComments = data["onderwerp"] + "Comments";
    document.getElementById(onderwerpPlusComments).appendChild(div);

}