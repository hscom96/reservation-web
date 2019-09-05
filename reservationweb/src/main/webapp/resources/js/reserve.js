document.addEventListener("DOMContentLoaded", function() {
	getDisplayInfoSet()
	updateBackBtn();
});


function getDisplayInfoSet() {
	var httpRequest = new XMLHttpRequest();
	var displayInfoSet;
	var displayId = getParam("id");
	
	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {
			displayInfoSet = JSON.parse(httpRequest.responseText);
			updateHeader(displayInfoSet);
			updateDetailInfo(displayInfoSet);
			updateReserveSelectList(displayInfoSet)
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
y}


function updateHeader(displayInfoSet){
	var productImage = document.querySelector("img.img_thumb");
	productImage.src = "/reservationweb/resources/" + displayInfoSet.productImages.shift().saveFileName;
}

function updateDetailInfo(displayInfoSet){
	var showTimeTxt = document.querySelector("#showTime");
	
	showTimeTxt.innerHTML = displayInfoSet.displayInfo.openingHours;
}

function runReserveSelect(){
	
}


function updateReserveSelectList(displayInfoSet){
	var reserveTemplate = document.querySelector("#reserveTemplate").innerHTML;
	var bindTemplate = Handlebars.compile(reserveTemplate);
	var reserveSelectList = document.querySelector(".section_booking_ticket .ticket_body");
	
	Handlebars.registerHelper('changeType', function(type) {
	    return changeProductTypeName(type);
	});
	
	reserveSelectList.innerHTML  = bindTemplate(displayInfoSet.productPrices);
}




function changeProductTypeName(name){
	switch(name){
		case 'A':
			return '성인';
		case 'Y':
			return '청소년';
		case 'B':
			return '유아';
		case 'S':
			return '세트';
		case 'D':
			return '장애인';
		case 'C':
			return '지역주민';
		case 'E':
			return '얼리버드';
		case 'D':
			return '평일';
		case 'V':
			return 'VIP';
		default:
			return name+'석';
	}
}

function updateBackBtn(){
	var backBtn = document.querySelector("a.btn_back");
	backBtn.setAttribute('href', '/reservationweb/detail?id='+getParam('id'));
};

