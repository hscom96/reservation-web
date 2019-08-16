document.addEventListener(
		"DOMContentLoaded",
		function() {
			var topIcon = document.querySelector(".lnk_top");
			var scroll = document.querySelector("html");

			function goTop() {
				if (scroll.scrollTop === 0)
					return;
				scroll.scrollTop -= 35;
				requestAnimationFrame(goTop);
			}

			topIcon.addEventListener("click", function() {
				requestAnimationFrame(goTop);
			})

			var index = 0;
			var width = 414;
			var images = document.querySelector("#images");
			var length = images.childElementCount;

			var moveTo = function() {
				images.style.transform = "translate(-"
					+ (width * index) + "px,0)";
			}

			setInterval(function() {
				index = index + 1;
				index = index % length;
				moveTo();
			}, 2000)

			var categoryList = document
			.querySelector(".event .section_event_tab .event_tab_lst");

			categoryList.addEventListener("click", function(evt) {
				if (evt.target.tagName == "SPAN"
					|| evt.target.tagName == "A") {
					changeCategory(evt);
					deleteItemAll();
					showNewItem();
				}
			})

			function changeCategory(evt) {
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

			function showNewItem() {
				var categoryId = document
				.querySelector(".event .section_event_tab .event_tab_lst>.item .anchor.active").parentNode
				.getAttribute("data-category");
				var itemBox = document
				.querySelectorAll(".event .wrap_event_box .lst_event_box");
				var itemCount = document.querySelector("#itemCount");
				var httpRequest = new XMLHttpRequest();
				var itemTemplate = document.querySelector("#itemList").innerHTML;
				var insertItem;
				var insertItemList;
				var html;

				httpRequest.onreadystatechange = function() {
					if (httpRequest.readyState == XMLHttpRequest.DONE
							&& httpRequest.status == 200) {

						insertItemList = JSON
						.parse(httpRequest.responseText);
						itemCount.innerHTML = insertItemList.totalCount
						+ "개";

						for ( var key in insertItemList.items) {
							insertItem = itemTemplate
								.replace("${id}", insertItemList.items[key].productId)
								.replace(/\$\{description\}/g, insertItemList.items[key].productDescription)
								.replace("${placeName}",insertItemList.items[key].placeName)
								.replace("${content}",insertItemList.items[key].productContent)
								.replace("${imgUrl}",insertItemList.items[key].productImgUrl);
							
							if (key % 2 == 0) {
								itemBox[0].innerHTML += insertItem;
							} else {
								itemBox[1].innerHTML = insertItem;
							}
						}
					}
				};

				httpRequest.open("GET",
						"http://localhost:8020/reservationWeb/api/products?categoryId="
						+ categoryId + "&start=0", true);
				httpRequest.send();
			}
		})