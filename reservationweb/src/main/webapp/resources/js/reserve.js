document.addEventListener("DOMContentLoaded", function() {
	let pageUpdateUnit = new PageUpdateUnit();
	pageUpdateUnit.getDisplayInfoSet();
	updateBackBtn();
});

function PageUpdateUnit() {
}
PageUpdateUnit.prototype.getDisplayInfoSet = function() {
	let httpRequest = new XMLHttpRequest();
	let displayInfoSet;
	let displayId = getParam("id");

	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE
				&& httpRequest.status == 200) {
			displayInfoSet = JSON.parse(httpRequest.responseText);
			let titleUnit = new TitleUnit();
			let detailInfoUnit = new DetailInfoUnit();
			let ticketSelectUnit = new TicketSelectUnit();
			let agreementUnit = new AgreementUnit();
			let submitUnit = new SubmitUnit();
			
			titleUnit.updateTitle(displayInfoSet);
			detailInfoUnit.updateDetailInfo(displayInfoSet);
			ticketSelectUnit.updateTicketSelectList(displayInfoSet)
			ticketSelectUnit.runTicketSelect();
			agreementUnit.openAgreement();
			agreementUnit.activeRcvBtn();
			submitUnit.submitForm(displayInfoSet);
		}
	};

	httpRequest.open("GET", "/reservationweb/api/products/" + displayId, true);
	httpRequest.send();
}

function TitleUnit() {
}
TitleUnit.prototype.updateTitle = function(displayInfoSet) {
	let productImage = document.querySelector("img.img_thumb");
	productImage.src = "/reservationweb/resources/"
		+ displayInfoSet.productImages.shift().saveFileName;
}

function DetailInfoUnit() {
}
DetailInfoUnit.prototype.updateDetailInfo = function(displayInfoSet) {
	let showTitleTxt = document.querySelector('#showTitle');
	let showTimeTxt = document.querySelector("#showTime");

	showTitleTxt.innerHTML = displayInfoSet.displayInfo.productDescription;
	showTimeTxt.innerHTML = displayInfoSet.displayInfo.openingHours;
}

function TicketSelectUnit() {
}
TicketSelectUnit.prototype = {
		updateTicketSelectList : function(displayInfoSet) {
			let reserveTemplate = document.querySelector("#reserveTemplate").innerHTML;
			let bindTemplate = Handlebars.compile(reserveTemplate);
			let reserveSelectList = document
			.querySelector(".section_booking_ticket .ticket_body");

			Handlebars.registerHelper('changeType', function(type) {
				return changeProductTypeName(type);
			});

			reserveSelectList.innerHTML = bindTemplate(displayInfoSet.productPrices);
		},

		runTicketSelect : function() {
			let ticketBox = document.querySelector('div.ticket_body');
			ticketBox.addEventListener('click', function(evt) {
				this.changeTicketCount(evt);
				this.changeTicketPriceSum(evt);
				this.activeBtn(evt);
				this.changeTicketCountSum();
			}.bind(this))
		},

		changeTicketCount : function(evt) {
			let countBox;
			let currentCount;
			let changeCount;

			if (evt.target.title === "빼기") {
				countBox = evt.target.nextSibling.nextSibling;
				currentCount = countBox.getAttribute('value');
				changeCount = parseInt(currentCount) - 1;

				if (changeCount < 0)
					changeCount = 0;

				countBox.setAttribute('value', changeCount);
			} else if (evt.target.title === "더하기") {
				countBox = evt.target.previousSibling.previousSibling;
				currentCount = countBox.getAttribute('value');
				changeCount = parseInt(currentCount) + 1;

				countBox.setAttribute('value', changeCount);
			}
		},

		changeTicketPriceSum : function(evt) {
			let priceBox = evt.target.parentElement.nextSibling.nextSibling.nextSibling.nextSibling;
			let priceSum = priceBox.childNodes[1];
			let ticketPrice = parseInt(evt.target.closest("div.qty").childNodes[3].childNodes[3].childNodes[1].innerHTML);
			let currentCount;

			if (evt.target.title === "빼기") {
				currentCount = parseInt(evt.target.nextSibling.nextSibling
						.getAttribute('value'));
			} else if (evt.target.title === "더하기") {
				currentCount = parseInt(evt.target.previousSibling.previousSibling
						.getAttribute('value'));
			}

			if (currentCount > 0) {
				priceBox.classList.add('on_color');
			} else {
				priceBox.classList.remove('on_color');
			}

			priceSum.innerHTML = currentCount * ticketPrice;
		},

		changeTicketCountSum : function() {
			let totalCountBox = document.querySelector('span#totalCount');
			let totalCount = this.countTicketCount();
			totalCountBox.innerHTML = totalCount;
		},

		countTicketCount : function() {
			let countBoxList = document.querySelectorAll('.count_control_input');
			let totalCount = 0;
			countBoxList.forEach(function(item) {
				totalCount += parseInt(item.getAttribute('value'));
			})
			return totalCount;
		},

		activeBtn : function(evt) {
			let currentCount;
			let countBox;
			let minBtn = evt.target.parentElement.childNodes[1];
			let plusBtn = evt.target.parentElement.childNodes[3];

			if (evt.target.title === "빼기") {
				countBox = evt.target.nextSibling.nextSibling;
				currentCount = countBox.getAttribute('value');
			} else if (evt.target.title === "더하기") {
				countBox = evt.target.previousSibling.previousSibling;
				currentCount = countBox.getAttribute('value');
			}

			if (currentCount == 0) {
				countBox.classList.add('disabled');
				minBtn.classList.add('disabled');
			} else if (currentCount > 0) {
				countBox.classList.remove('disabled');
				minBtn.classList.remove('disabled');
			}

		}
}

function AgreementUnit() {
}
AgreementUnit.prototype = {
		openAgreement : function() {
			let agreementBox = document
			.querySelector('div.section_booking_agreement');
			agreementBox.addEventListener('click', function(evt) {
				let agreement;
				if (evt.target.classList.contains('btn_agreement')
						|| evt.target.classList.contains('btn_text')
						|| evt.target.classList.contains('fn-down2')) {
					agreement = evt.target.closest('.agreement');
					if (agreement.classList.contains('open')) {
						agreement.classList.remove('open');
						agreement.childNodes[3].childNodes[1].innerHTML = '보기';
					} else {
						agreement.classList.add('open');
						agreement.childNodes[3].childNodes[1].innerHTML = '닫기';
					}
				}
			})
		},

		activeRcvBtn : function() {
			let agreementCheck = document.querySelector('#chk3');
			let reserveBtn = document.querySelector('div.bk_btn_wrap');
			agreementCheck.addEventListener('click', function(evt) {
				if (agreementCheck.checked == true)
					reserveBtn.classList.remove('disable');
				else
					reserveBtn.classList.add('disable');

			})
		}
}

function SubmitUnit() {
}
SubmitUnit.prototype = {
		submitForm : function(displayInfoSet) {
			let submitBtn = document.querySelector('button.bk_btn');
			submitBtn.addEventListener('click', function() {
				let form = document.querySelector('.form_horizontal');
				let validUnit = new ValidUnit();
				let valid = validUnit.loadValidate();
				if (valid) {
					let requestDataUnit = new RequestDataUnit();
					let requestData = requestDataUnit.createRequestData(displayInfoSet);
					let g = requestData;
					// form.submit();
				}
			});
		}
}

function ValidUnit() {
}
ValidUnit.prototype = {
		loadValidate : function() {
			let valid = true;

			let errorItem = this.validItemSelect();
			let errorName = this.validName();
			let errorPhoneNum = this.validPhoneNum();
			let errorEmail = this.validEmail();

			valid = errorItem && errorName && errorPhoneNum && errorEmail;

			if (!valid) {
				alert('잘못 입력하였습니다. 다시한번확인해주세요.');
			}

			return valid;
		},

		validItemSelect : function() {
			var ticketSelectUnit = new TicketSelectUnit();
			var ticketCount = ticketSelectUnit.countTicketCount();

			if (ticketCount <= 0) {
				alert('예매할 티켓을 선택해주세요');
				return false;
			}

			return true;
		},
		validName : function() {
			var nameBox = document.querySelector("input#name");
			var nameValue = nameBox.value;
			var valid = (/^[가-힣]+$/).test(nameValue);

			if (!nameValue) {
				nameBox.placeholder = "예매자를 채워주세요";
				nameBox.classList.add('cautionInput');
				return false;
			} else if (!valid) {
				nameBox.value = "";
				nameBox.placeholder = "예매자 형식이 올바르지 않습니다";
				nameBox.classList.add('cautionInput');
				return false;
			}

			if (nameBox.classList.contains('cautionInput')) {
				nameBox.classList.remove('cautionInput');
				nameBox.placeholder = "예) 홍길동";
			}

			return true;
		},

		validPhoneNum : function() {
			var phoneBox = document.querySelector("input#tel");
			var phoneValue = phoneBox.value;
			var valid = (/^\d{3}-\d{3,4}-\d{4}$/).test(phoneValue);

			if (!phoneValue) {
				phoneBox.placeholder = "연락처를 채워주세요";
				phoneBox.classList.add('cautionInput');
				return false;
			} else if (!valid) {
				phoneBox.value = "";
				phoneBox.placeholder = "연락처 형식이 올바르지 않습니다";
				phoneBox.classList.add('cautionInput');
				return false;
			}

			if (phoneBox.classList.contains('cautionInput')) {
				phoneBox.classList.remove('cautionInput');
				phoneBox.placeholder = "휴대폰 입력 시 예매내역 문자발송";
			}

			return true;
		},

		validEmail : function() {
			var emailBox = document.querySelector("[name='email']");
			var emailValue = emailBox.value;
			var valid = (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue);

			if (!emailValue) {
				emailBox.placeholder = "이메일을 채워주세요";
				emailBox.classList.add('cautionInput');
				return false;
			} else if (!valid) {
				emailBox.value = "";
				emailBox.placeholder = "이메일 형식이 올바르지 않습니다";
				emailBox.classList.add('cautionInput');
				return false;
			}

			if (emailBox.classList.contains('cautionInput')) {
				emailBox.classList.remove('cautionInput');
				emailBox.placeholder = "crong@codesquad.kr";
			}
			return true;
		}
}

function RequestData(displayInfoId, prices, productId, reservationEmail,
		reservationName, reservationTelephone, reservationYearMonthDay) {
	this.displayInfoId = displayInfoId;
	this.prices = prices;
	this.productId = productId;
	this.reservationEmail = reservationEmail;
	this.reservationName = reservationName;
	this.reservationTelephone = reservationTelephone;
	this.reservationYearMonthDay = reservationYearMonthDay;
}

function Price(count, productPriceId, reservationInfoId, reservationInfoPriceId) {
	this.count = count;
	this.productPriceId = productPriceId;
	this.reservationInfoId = reservationInfoId;
	this.reservationInfoPriceId = reservationInfoPriceId;
}

function RequestDataUnit() {
}
RequestDataUnit.prototype = {
		createRequestData : function(displayInfoSet) {
			var prices = this.createPricesData();
			var displayInfoId = displayInfoSet.displayInfo.displayInfoId;
			var productId = displayInfoSet.displayInfo.productId;
			var reservationEmail = document.querySelector('input#email.email').value;
			var reservationName = document.querySelector('input#name.text').value;
			var reservationTelephone = document.querySelector('input#tel.tel').value;
			var reservationYearMonthDay = document
			.querySelector('#reservationDate').innerHTML.trim();

			return new RequestData(displayInfoId, prices, productId,
					reservationEmail, reservationName, reservationTelephone,
					reservationYearMonthDay);
		},

		createPricesData : function() {
			var ticketSelectBox = document.querySelectorAll('.ticket_body .qty');
			var prices = [];

			ticketSelectBox.forEach(function(item) {
				var count = item.childNodes[1].childNodes[3].childNodes[3].value;
				var productPriceId = item.dataset.productpriceid;
				var price = new Price(count, productPriceId);
				prices.push(price);
			});

			return prices;
		}
}

function changeProductTypeName(name) {
	switch (name) {
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
		return name + '석';
	}
}

function updateBackBtn() {
	let backBtn = document.querySelector("a.btn_back");
	backBtn.setAttribute('href', '/reservationweb/detail?id=' + getParam('id'));
};

