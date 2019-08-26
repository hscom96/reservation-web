document.addEventListener("DOMContentLoaded", function() {
	updateDisplayInfoSet();
});


function updateDisplayInfoSet() {
	var httpRequest = new XMLHttpRequest();
	var displayInfoSet;
	var displayId = getParam("id");

	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {
			displayInfoSet = JSON.parse(httpRequest.responseText);
			
			updateSliderImage(displayInfoSet);
			changeSlider();
			updateProductContent(displayInfoSet);
			changeProductContent();
			updateInfoTab(displayInfoSet);
			changeInfoTab();
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
}

// 해당 name의 파라미터 값 반환
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

// 슬라이더 이미지 등록
function updateSliderImage(displayInfoSet) {
	var slider = document.querySelector(".group_visual .detail_swipe");
	var imageTemplate = document.querySelector("#slideItemTemplate").innerHTML;
	var bindTemplate = Handlebars.compile(imageTemplate);
	
	var data = {
			"productDescription":displayInfoSet.displayInfo.productDescription,
			"productImages":displayInfoSet.productImages
	};
	
	
	slider.innerHTML =  bindTemplate(data);
}

// 슬라이더 동작
function changeSlider() {
	var sliderBox = document.querySelector("#sliderWrap");
	var slider = document.querySelector(".group_visual .detail_swipe");
	var images = document.querySelectorAll(".group_visual .visual_img .item");
	var totalCount = images.length;
	var sliderWidth = 414;
	var sliderIndex = 0;

	var paginition_current = document
			.querySelector(".figure_pagination #current");
	var paginition_total = document
			.querySelector(".figure_pagination .off #total");

	var nextBtn = document.querySelector("div.nxt");
	var prevBtn = document.querySelector("div.prev");

	pagination();
	removeSlideBtn();

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

	function removeSlideBtn() {
		if (totalCount <= 1) {
			nextBtn.parentNode.removeChild(nextBtn);
			prevBtn.parentNode.removeChild(prevBtn);
		}
	}
}

function updateProductContent(displayInfoSet){
	$('.store_details .dsc').html(displayInfoSet.displayInfo.productContent);
}

function changeProductContent(){
	$('a.bk_more._open').on('click', function(){
		$('a.bk_more._open').css('display','none');
		$('a.bk_more._close').css('display','block');
		$('div.store_details').removeClass('close3');
	});
	$('a.bk_more._close').on('click', function(){
		$('a.bk_more._open').css('display','block');
		$('a.bk_more._close').css('display','none');
		$('div.store_details').addClass('close3');
	});
}

function updateInfoTab(displayInfoSet){
	
}



// 상세정보-오시는길 탭 변경
function changeInfoTab() {
	var infoTab = document.querySelector(".section_info_tab .info_tab_lst");

	infoTab.addEventListener('click', function(evt) {
		if (evt.target.tagName === "SPAN" || evt.target.tagName === "A") {
			changeTab(evt);
			showContent();
		}
	});

	function changeTab(evt) {
		var detailTab = document
				.querySelector(".info_tab_lst ._detail .anchor span");
		var locationTab = document
				.querySelector(".info_tab_lst ._path .anchor span");
		var currentTab = document.querySelector("a.anchor.active span");

		if (evt.target === detailTab || evt.target === locationTab) {
			if (!(evt.target === currentTab)) {
				currentTab.parentNode.classList.remove('active');
				evt.target.parentNode.classList.add('active');
			}
		} else if (evt.target === detailTab.parentNode
				|| evt.target === locationTab.parentNode) {
			if (!(evt.target === currentTab.parentNode)) {
				currentTab.parentNode.classList.remove('active');
				evt.target.classList.add('active');
			}
		}
	}
	
	function showContent(){
		var detailContent = document.querySelector("div.detail_area_wrap");
		var locationContent = document.querySelector("div.detail_location");
		var currentTab = document.querySelector("a.anchor.active span").innerHTML;
		
		if(currentTab === "상세정보"){
			detailContent.classList.remove("hide");
			locationContent.classList.add("hide");
		}else if(currentTab === "오시는길"){
			detailContent.classList.add("hide");
			locationContent.classList.remove("hide");
		}
	}
}