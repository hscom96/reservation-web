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
			updateProductContent(displayInfoSet);
			updateEventInfo(displayInfoSet);
			updateReviewHeader(displayInfoSet);
			updateReview(displayInfoSet);
			updateMoreReviewBtn(displayInfoSet);
			updateDetailTab(displayInfoSet);
			runSlider();
			changeProductContent();
			changeDetailTab();
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
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
function runSlider() {
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

function updateEventInfo(displayInfoSet){
	var discountEvent = document.querySelector(".section_event .event_info_box .event_info .in_dsc");
	var insertHTML = "[네이버예약 특별할인] <br>";
	displayInfoSet.productPrices.forEach(value=>{
		insertHTML += value.priceTypeName+"석 "
			});
	
	discountEvent.innerHTML = insertHTML;
}

function updateReviewHeader(displayInfoSet){
	var averageStar = document.querySelector(".grade_area .graph_mask .graph_value");
	var averageScore = document.querySelector(".section_review_list .short_review_area .grade_area .text_value span"); 
	var totalReviewCount = document.querySelector(".grade_area .join_count em");
	
	averageStar.style.width = 100*displayInfoSet.averageScore.toFixed(1)/5.0+"%"; 
	averageScore.innerHTML = displayInfoSet.averageScore.toFixed(1);
	totalReviewCount.innerHTML = displayInfoSet.comments.length +"건";
}

function updateReview(displayInfoSet){
	var reviewBox = document.querySelector("ul.list_short_review");
	var reviewTemplate = document.querySelector("#reviewTemplate").innerHTML;
	var bindTemplate = Handlebars.compile(reviewTemplate);
	
	//reviewBox.innerHTML  = bindTemplate(displayInfoSet.comments);
}

function updateMoreReviewBtn(displayInfoSet){
	var moreReviewBtn = document.querySelector("a.btn_review_more");
	moreReviewBtn.setAttribute('href', "/reservationweb/review?id=" + displayInfoSet.displayInfo.displayInfoId);
}

// 상품설명 저장
function updateProductContent(displayInfoSet){
	$('.store_details .dsc').html(displayInfoSet.displayInfo.productContent);
}

// 상품설명 펼치기-닫기
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

// 카태고리탭 정보저장
function updateDetailTab(displayInfoSet){
	var content = document.querySelector(".detail_area .detail_info .detail_info_group .detail_info_lst .in_dsc");
	var title = document.querySelector(".box_store_info .store_name");
	var new_addr = document.querySelector(".store_addr_wrap .store_addr");
	var old_addr = document.querySelector(".store_addr_wrap .store_addr .addr_old_detail");
	var store_addr = document.querySelector("p.store_addr.addr_detail");
	var store_tel= document.querySelector("a.store_tel");
	var map = document.querySelector("img.store_map");
	
	content.innerHTML = displayInfoSet.displayInfo.productContent;
	title.innerHTML = displayInfoSet.displayInfo.categoryName +" - " + displayInfoSet.displayInfo.productDescription
	new_addr.innerHTML = displayInfoSet.displayInfo.placeStreet;
	old_addr.innerHTML = displayInfoSet.displayInfo.placeLot;
	store_addr.innerHTML = displayInfoSet.displayInfo.placeName;
	store_tel.innerHTML = displayInfoSet.displayInfo.telephone;
	map.src = "resources/" +  displayInfoSet.displayInfoImage.saveFileName;
}



// 상세정보-오시는길 탭 변경
function changeDetailTab() {
	var infoTab = document.querySelector(".section_info_tab .info_tab_lst");

	infoTab.addEventListener('click', function(evt) {
		if (evt.target.tagName === "SPAN" || evt.target.tagName === "A") {
			changeTab(evt);
			showContent();
		}
	});

	// 카테고리탭변경
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
	
	// 카테고리탭 내용 전환
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