document.addEventListener("DOMContentLoaded", function() {
	updateImageSlider();
	runImageSlider();
	addNewItem();
	changeCategory();
	moreItem();
});

function updateImageSlider() {
	var httpRequest = new XMLHttpRequest();
	var promotionTemplate = document.querySelector('#promotionItem').innerHTML;
	var insertItem = "";
	var promotionBox = document.querySelector('#slider');
	
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {

			insertItemList = JSON.parse(httpRequest.responseText);

			for ( var key in insertItemList) {
				insertItem = promotionTemplate.replace("${productImageUrl}",insertItemList[key].productImageUrl)
				.replace("${description}",insertItemList[key].description)
				.replace("${placeName}",insertItemList[key].placeName);
				promotionBox.innerHTML += insertItem;
			}

		}
	};

	httpRequest.open("GET", "/reservationweb/api/promotions", true);
	httpRequest.send();
}

function runImageSlider() {
	var index = 0;
	var width = 414;
	var slider = document.querySelector("#slider");
	var length = document.querySelectorAll(".item").length;

	setInterval(function() {
		index = index + 1;
		index = index % length;
		slider.style.transform = "translate(-" + (width * index) + "px,0)";
	}, 2000)
}

function addNewItem() {
	var categoryId = document
			.querySelector(".event .section_event_tab .event_tab_lst>.item .anchor.active").parentNode
			.getAttribute("data-category");
	var itemBox = document
			.querySelectorAll(".event .wrap_event_box .lst_event_box");
	var currentItemCount = document
			.querySelectorAll('.event .wrap_event_box .lst_event_box .item').length;
	var httpRequest = new XMLHttpRequest();
	var itemTemplate = document.querySelector("#itemList").innerHTML;
	var insertItem;
	var insertItemList;

	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {

			insertItemList = JSON.parse(httpRequest.responseText);

			updateCategoryItemSum(insertItemList.totalCount);

			for ( var key in insertItemList.items) {
				insertItem = itemTemplate
					.replace("${id}",insertItemList.items[key].displayId)
					.replace(/\$\{description\}/g, insertItemList.items[key].productDescription)
					.replace("${placeName}", insertItemList.items[key].placeName)
					.replace("${content}",insertItemList.items[key].productContent)
					.replace("${imgUrl}", insertItemList.items[key].productImgUrl);

				if (key % 2 == 0) {
					itemBox[0].innerHTML += insertItem;
				} else {
					itemBox[1].innerHTML += insertItem;
				}
			}

			deleteMoreButton(currentItemCount, insertItemList.totalCount);
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products?categoryId="
			+ categoryId + "&start=" + currentItemCount, true);
	httpRequest.send();
}

function updateCategoryItemSum(totalCount) {
	var categoryItemSum = document.querySelector("#categoryItemSum");
	categoryItemSum.innerHTML = totalCount + "개";
}

function deleteMoreButton(currentItemCount, totalCount) {
	var moreButton = document.querySelector(".more .btn");
	var currentItemCount = document
			.querySelectorAll('.event .wrap_event_box .lst_event_box .item').length;
	if (currentItemCount >= totalCount) {
		moreButton.style.display = 'none';
	}
}

function moreItem() {
	var moreButton = document.querySelector(".more .btn");
	moreButton.addEventListener("click", function() {
		addNewItem();
	})
}

function changeCategory() {
	var categoryList = document
			.querySelector(".event .section_event_tab .event_tab_lst");
	var moreButton = document.querySelector(".more .btn");

	categoryList.addEventListener("click", function(evt) {
		if (evt.target.tagName === "SPAN" || evt.target.tagName == "A") {
			moveCategory(evt);
			checkMoreButton();
			deleteItemAll();
			addNewItem();
		}
	})

	function moveCategory(evt) {
		var selectedCategory = document
				.querySelector(".event .section_event_tab .event_tab_lst>.item .anchor.active");
		if (evt.target.tagName == "SPAN") {
			selectedCategory.classList.remove("active");
			evt.target.parentNode.classList.add("active");
		} else if (evt.target.tagName === "A") {
			selectedCategory.classList.remove("active");
			evt.target.classList.add("active");
		}
	}

	function checkMoreButton() {
		if (moreButton.style.display == 'none')
			moreButton.style.display = 'block';
	}

	function deleteItemAll() {
		var itemBox = document
				.querySelectorAll(".event .wrap_event_box .lst_event_box");
		while (itemBox[0].hasChildNodes()) {
			itemBox[0].removeChild(itemBox[0].firstChild);
		}
		while (itemBox[1].hasChildNodes()) {
			itemBox[1].removeChild(itemBox[1].firstChild);
		}
	}
}