document.addEventListener("DOMContentLoaded", function() {
	getDisplayInfo();
	updateSliderImage();
	runSlider();
});

var displayInfo;

// displayId의 displayInfo 요청
function getDisplayInfo() {
	var httpRequest = new XMLHttpRequest();
	var displayId = getParam("id");

	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {
			displayInfo = JSON.parse(httpRequest.responseText);

		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, false);
	httpRequest.send();
}

//해당 name의 파라미터 값 반환
function getParam(sname) {
	var params = location.search.substr(location.search.indexOf("?") + 1);
	var sval = "";
	params = params.split("&");
	for (var i = 0; i < params.length; i++) {
		temp = params[i].split("=");
		if ([ temp[0] ] == sname) {
			sval = temp[1];
		}
	}
	return sval;
}

function updateSliderImage() {
	var slider = document.querySelector(".group_visual .detail_swipe");
	var imageTemplate = document.querySelector("#slideItemTemplate").innerHTML;
	var bindTemplate = Handlebars.compile(imageTemplate);
	var innerHTML = "";
	displayInfo.productImages.forEach(function(item, index) {
		innerHTML += bindTemplate(item);
	});
	slider.innerHTML = innerHTML;
}

function runSlider() {
	var sliderBox = document.querySelector("#sliderWrap");
	var slider = document.querySelector(".group_visual .detail_swipe");
	var images = document.querySelectorAll(".group_visual .visual_img .item");
	var totalCount = images.length;
	var sliderWidth = 414;
	var sliderIndex = 0;

	var paginition_current = document
			.querySelector(".figure_pagination #current");
	var paginition_total = document.querySelector(".figure_pagination .off #total");

	var nextBtn = document.querySelector("div.nxt");
	var prevBtn = document.querySelector("div.prev");

	pagination();
	
	nextBtn.addEventListener('click', function() {
		plusSlides(1);
		pagination();
	});
	prevBtn.addEventListener('click', function() {
		plusSlides(-1);
		pagination();
	});

	function plusSlides(n) {
		showSlides(sliderIndex += n);
	}

	function showSlides(n) {
		slideIndex = n;
		if (sliderIndex == -1) {
			sliderIndex = totalCount - 1;
		} else if (slideIndex === totalCount) {
			sliderIndex = 0;
		}

		slider.style.left = -(sliderWidth * sliderIndex) + 'px';
	}

	function pagination() {
		paginition_current.innerHTML = sliderIndex + 1;
		paginition_total.innerHTML = totalCount;
	}
}