document.addEventListener("DOMContentLoaded", function() {
	updateDisplayInfoSet();
});

function updateDisplayInfoSet() {
	let httpRequest = new XMLHttpRequest();
	let displayInfoSet;
	let displayId = getParam("id");

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
	let slider = document.querySelector(".group_visual .detail_swipe");
	let imageTemplate = document.querySelector("#slideItemTemplate").innerHTML;
	let bindTemplate = Handlebars.compile(imageTemplate);
	
	let data = {
			"productDescription":displayInfoSet.displayInfo.productDescription,
			"productImages":displayInfoSet.productImages.slice(0,2)
	};
	
	slider.innerHTML =  bindTemplate(data);
}

// 슬라이더 동작
function runSlider() {
	let sliderBox = document.querySelector("#sliderWrap");
	let slider = document.querySelector(".group_visual .detail_swipe");
	let images = document.querySelectorAll(".group_visual .visual_img .item");
	let totalCount = images.length;
	let sliderWidth = 414;
	let sliderIndex = 0;

	let paginition_current = document
			.querySelector(".figure_pagination #current");
	let paginition_total = document
			.querySelector(".figure_pagination .off #total");

	let nextBtn = document.querySelector("div.nxt");
	let prevBtn = document.querySelector("div.prev");

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
		if (sliderIndex == -1) {
			sliderIndex = totalCount - 1;
		} else if (sliderIndex === totalCount) {
			sliderIndex = 0;
		}
		
		slider.style.transform = "translate(-" + (sliderWidth * sliderIndex) + "px,0)";
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

//이벤트정보 업데이트
function updateEventInfo(displayInfoSet){
	let discountEvent = document.querySelector(".section_event .event_info_box .event_info .in_dsc");
	let insertHTML = "[네이버예약 특별할인] <br>";
	let temp=[];
	
	displayInfoSet.productPrices.forEach(value=>{
		temp.push( value.priceTypeName+"석 "+value.discountRate+"%");
			});
	
	insertHTML += temp.join(', ') + " 할인";
	discountEvent.innerHTML = insertHTML;
}

//예매자 한줄평 헤더부분 업데이트
function updateReviewHeader(displayInfoSet){
	let averageStar = document.querySelector(".grade_area .graph_mask .graph_value");
	let averageScore = document.querySelector(".section_review_list .short_review_area .grade_area .text_value span"); 
	let totalReviewCount = document.querySelector(".grade_area .join_count em");
	
	averageStar.style.width = 100*displayInfoSet.averageScore.toFixed(1)/5.0+"%"; 
	averageScore.innerHTML = displayInfoSet.averageScore.toFixed(1);
	totalReviewCount.innerHTML = displayInfoSet.comments.length +"건";
}

//예매자 한줄평 업데이트
function updateReview(displayInfoSet){
	let reviewBox = document.querySelector("ul.list_short_review");
	let reviewTemplate = document.querySelector("#reviewTemplate").innerHTML;
	let bindTemplate = Handlebars.compile(reviewTemplate);
	
	let data = {
			displayInfo : displayInfoSet.displayInfo,
			comments :  displayInfoSet.comments.slice(0,3)
	};
	
	Handlebars.registerHelper('filePath', function(item) {
	    return item.commentImages.shift().saveFileName;
	});
	
	Handlebars.registerHelper('countImg', function(item) {
	    return item.commentImages.length;
	});
	
	reviewBox.innerHTML  = bindTemplate(data);
	
}

function updateMoreReviewBtn(displayInfoSet){
	let moreReviewBtn = document.querySelector("a.btn_review_more");
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
	let content = document.querySelector(".detail_area .detail_info .detail_info_group .detail_info_lst .in_dsc");
	let title = document.querySelector(".box_store_info .store_name");
	let newAddr = document.querySelector(".store_addr_wrap .store_addr");
	let oldAddr = document.querySelector(".store_addr_wrap .store_addr .addr_old_detail");
	let storeAddr = document.querySelector("p.store_addr.addr_detail");
	let storeTel= document.querySelector("a.store_tel");
	let map = document.querySelector("img.store_map");
	
	content.innerHTML = displayInfoSet.displayInfo.productContent;
	title.innerHTML = displayInfoSet.displayInfo.categoryName +" - " + displayInfoSet.displayInfo.productDescription
	newAddr.innerHTML = displayInfoSet.displayInfo.placeStreet;
	oldAddr.innerHTML = displayInfoSet.displayInfo.placeLot;
	storeAddr.innerHTML = displayInfoSet.displayInfo.placeName;
	storeTel.innerHTML = displayInfoSet.displayInfo.telephone;
	map.src = "resources/" +  displayInfoSet.displayInfoImage.saveFileName;
}



// 상세정보-오시는길 탭 변경
function changeDetailTab() {
	let infoTab = document.querySelector(".section_info_tab .info_tab_lst");

	infoTab.addEventListener('click', function(evt) {
		if (evt.target.tagName === "SPAN" || evt.target.tagName === "A") {
			changeTab(evt);
			showContent();
		}
	});

	// 카테고리탭변경
	function changeTab(evt) {
		let detailTab = document
				.querySelector(".info_tab_lst ._detail .anchor span");
		let locationTab = document
				.querySelector(".info_tab_lst ._path .anchor span");
		let currentTab = document.querySelector("a.anchor.active span");

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
		let detailContent = document.querySelector("div.detail_area_wrap");
		let locationContent = document.querySelector("div.detail_location");
		let currentTab = document.querySelector("a.anchor.active span").innerHTML;
		
		if(currentTab === "상세정보"){
			detailContent.classList.remove("hide");
			locationContent.classList.add("hide");
		}else if(currentTab === "오시는길"){
			detailContent.classList.add("hide");
			locationContent.classList.remove("hide");
		}
	}
	
}


function pressRsvBtn(){
	location.href = "/reservationweb/reserve?id=" + getParam("id");
}