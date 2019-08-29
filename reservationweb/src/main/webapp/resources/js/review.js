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
			updateReviewHeader(displayInfoSet);
			updateReviewList(displayInfoSet);
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
}

function updateBackBtn(displayInfoSet){
	var backBtn = document.querySelector("a.btn_back");
	backBtn.setAttribute('href', '/reservationweb/detail?id='+displayInfoSet.displayInfo.displayInfoId);
};

function updateReviewHeader(displayInfoSet){
	var title = document.querySelector("a.title");
	var averageStar = document.querySelector(".grade_area .graph_mask .graph_value");
	var averageScore = document.querySelector(".section_review_list .short_review_area .grade_area .text_value span"); 
	var totalReviewCount = document.querySelector(".grade_area .join_count em");
	
	title.innerHTML = displayInfoSet.displayInfo.productDescription;
	averageStar.style.width = 100*displayInfoSet.averageScore.toFixed(1)/5.0+"%"; 
	averageScore.innerHTML = displayInfoSet.averageScore.toFixed(1);
	totalReviewCount.innerHTML = displayInfoSet.comments.length +"건";
}

function updateReviewList(displayInfoSet){
	var reviewTemplate = document.querySelector("#reviewTemplate").innerHTML;
	var bindTemplate = Handlebars.compile(reviewTemplate);
	var reviewList = document.querySelector(".section_review_list .short_review_area .list_short_review");
	
	Handlebars.registerHelper('filePath', function(item) {
	    return item.commentImages.slice(0,1).shift().saveFileName;
	});
	
	reviewList.innerHTML  = bindTemplate(displayInfoSet);
}