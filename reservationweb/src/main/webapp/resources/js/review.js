document.addEventListener(
		"DOMContentLoaded",
		function(){
			updateDisplayInfoSet();
		}
);


function updateDisplayInfoSet() {
	var httpRequest = new XMLHttpRequest();
	var displayInfoSet;
	var displayId = getParam("id");

	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {
			displayInfoSet = JSON.parse(httpRequest.responseText);
			
			updateBackBtn(displayInfoSet);
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
}

function updateBackBtn(displayInfoSet){
	var backBtn = document.querySelector("a.btn_back");
	backBtn.setAttribute('href', '/reservationweb/detail?id='+displayInfoSet.displayInfo.displayInfoId);
};