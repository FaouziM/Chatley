var statusRequest = new XMLHttpRequest();
var updateStatusRequest = new XMLHttpRequest();
//status 0

var body = document.getElementsByTagName("body")[0];
var updateKnop = document.getElementById("updateStatusKnop");

updateKnop.onclick = updateStatus;
body.onload = getStatus;
//body.onload = (function() {updateStatus(true)});
//anonymous function
//waarom? omdat functie(arg) niet valt te passen als callback


function updateStatus(){
	var statusVeld = document.getElementById("statusText");
	var statusText = statusVeld.value;
	statusVeld.value = "";
	var info = "nieuweStatus=" + encodeURIComponent(statusText);
	updateStatusRequest.open("POST", "Controller?action=updateStatus", true);
	updateStatusRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	updateStatusRequest.send(info);
	updateStatusRequest.onreadystatechange = getStatusDataGeenTimeout;
}

function getStatusDataGeenTimeout(){
	if (updateStatusRequest.readyState == 4){
		//status 4
		if (updateStatusRequest.status == 200){
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
function getStatusData(){
	if (statusRequest.readyState == 4){
		//status 4
		if (statusRequest.status == 200){
			var response = statusRequest.responseText;
			var statusVeld = document.getElementById("statusVeld");
			statusVeld.innerHTML = response;
			setTimeout("getStatus()", 6000);
		}
	}
}
	

