var statusObject = new XMLHttpRequest();
//status 0

function getStatus() {
	statusObject.open("GET", "Controller?action=getStatus", true);
	//status 1
	
	statusObject.onreadystatechange = getStatusData;
	statusObject.send(null);
	//status 2
}

//status 3
function getStatusData(){
	if (statusObject.readyState == 4){
		//status 4
		if (statusObject.status == 200){
			var response = statusObject.responseText;
			var statusVeld = document.getElementById("statusVeld");
			statusVeld.innerHTML = response;
		}
	}
	setTimeout("getStatus()", 6000);
}

