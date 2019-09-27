document.addEventListener(
		"DOMContentLoaded",
		function(){
			var pageLoadUnit = new PageLoadUnit();
			pageLoadUnit.updateRsrvAll();
		}
);


function PageLoadUnit() {}
PageLoadUnit.prototype=
{
		updateRsrvAll : function(){
			var httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {

				if (httpRequest.readyState == XMLHttpRequest.DONE
						&& httpRequest.status == 200) {
					var rsrvInfoSet = JSON.parse(httpRequest.responseText);
					var rsrvEventUnit = new RsrvEventUnit();
					var rsrvIOUnit = new RsrvIOUnit();
					
					rsrvIOUnit.insertRsrvAll(rsrvInfoSet);
					rsrvIOUnit.addIfNoRsrvViewAll();
					rsrvEventUnit.addEvent();
					
				}
			};

			httpRequest.open("GET", "/reservationweb/api/reservations?reservationEmail=" + readCookie('loginCookie'), true);
			httpRequest.send();
		}
}

function RsrvIOUnit(){}
RsrvIOUnit.prototype = {
		insertRsrvAll : function(rsrvInfoSet){
			var rsrvCountUnit = new RsrvCountUnit();
			var rsrvTemplate = document.querySelector("#rsrvItemTemplate").innerHTML;
			var bindTemplate = Handlebars.compile(rsrvTemplate);
			
			for(var rsrvInfo of rsrvInfoSet.reservations){
				if(rsrvInfo.cancelYn === false){
					this.insertConfirmRsrv(rsrvInfo, bindTemplate);
				}else{
					this.insertCancelRsrv(rsrvInfo, bindTemplate);
				}
			}
			rsrvCountUnit.updateCountAll();
		},
		
		insertConfirmRsrv : function(rsrvInfo, bindTemplate){
			var rsrvConfirmBox = document.querySelector("li.card.confirmed");
			var cancelBtn = document.querySelector("#rsrvCancelBtnTemplate").innerHTML;
			rsrvConfirmBox.innerHTML += bindTemplate(rsrvInfo);
			rsrvConfirmBox.lastChild.previousSibling.childNodes[1].childNodes[1].childNodes[3].childNodes[1].innerHTML += cancelBtn;
		},
		
		insertCancelRsrv : function(rsrvInfo, bindTemplate){
			var rsrvCancelBox = document.querySelector("li.card.cancel");
			rsrvCancelBox.innerHTML += bindTemplate(rsrvInfo);
		},
		
		changeConfirmToCancelRsrv : function(rsrvElement){
			var rsrvConfirmBox = document.querySelector("li.card.confirmed");
			var rsrvCancelBox = document.querySelector("li.card.cancel");
			var cancelBtn = rsrvElement.getElementsByTagName("button")[0];
			var rsrvNum;
			rsrvConfirmBox.removeChild(rsrvElement);
			cancelBtn.remove();
			rsrvCancelBox.appendChild(rsrvElement);

			this.delIfNoRsrvView(rsrvCancelBox);
			this.addIfNoRsrvView(rsrvConfirmBox);
		},
		
		addIfNoRsrvView : function(rsrvBox){
			var noRsrvViewTemplate = document.querySelector("#noRsrvViewTemplate").innerHTML;
			var rsrvNum = rsrvBox.querySelectorAll("article.card_item").length;
			if(rsrvNum == 0){
				rsrvBox.innerHTML += noRsrvViewTemplate;
			}
		},
		
		addIfNoRsrvViewAll : function(){
			var rsrvConfirmBox = document.querySelector("li.card.confirmed");
			var rsrvUsedBox = document.querySelector("li.card.used");
			var rsrvCancelBox = document.querySelector("li.card.cancel");
			
			this.addIfNoRsrvView(rsrvConfirmBox);
			this.addIfNoRsrvView(rsrvUsedBox);
			this.addIfNoRsrvView(rsrvCancelBox);
		},
		
		delIfNoRsrvView : function(rsrvBox){
			var rsrvNum = rsrvBox.querySelectorAll("article.card_item").length;
			var noRsrvView = rsrvBox.querySelector("div.err");
			if(rsrvNum > 0 && noRsrvView){
				rsrvBox.removeChild(noRsrvView);
			}
		}
		
}

function RsrvCountUnit(){}
RsrvCountUnit.prototype = {
	updateCountAll : function(){
		this.updateSumCount();
		this.updateConfirmCount();
		this.updateUsedCount();
		this.updateCancelCount();
	},

	updateSumCount : function(){
		var sumBox = document.querySelector("#sumCount");
		var itemCount = document.querySelectorAll("article.card_item").length;
		sumBox.innerHTML = itemCount;
	},
	
	updateConfirmCount : function(){
		var confirmBox = document.querySelector("#confirmCount");
		var itemCount = document.querySelector("li.card.confirmed").childElementCount-1;
		confirmBox.innerHTML = itemCount;
	},
	
	updateUsedCount : function(){
		var usedBox = document.querySelector("#usedCount");
		var itemCount = document.querySelector("li.card.used").childElementCount-1;
		usedBox.innerHTML = itemCount;
	},
	
	updateCancelCount : function(){
		var cancelBox = document.querySelector("#cancelCount");
		var itemCount = document.querySelector("li.card.cancel").childElementCount-1;
		cancelBox.innerHTML = itemCount;
	}
}

function RsrvEventUnit(){}
RsrvEventUnit.prototype = {
		addEvent : function(){
			this.addCancelEvent();
		},
		
		addCancelEvent : function(){
			var confirmBox = document.querySelector("li.card.confirmed");
			confirmBox.addEventListener("click",function(evt){
				if(evt.target.tagName === "BUTTON" || evt.target.parentNode.tagName === 'BUTTON'){
					var httpRequest = new XMLHttpRequest();
					var resrevationItem;
					var reservationId;
				
					if(evt.target.tagName === "BUTTON"){
						resrevationItem = evt.target.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
					}else{
						resrevationItem = evt.target.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;	
					}
					
					reservationId = resrevationItem.dataset.rsrvid;
					
					httpRequest.onreadystatechange = function() {
						if (httpRequest.readyState == XMLHttpRequest.DONE
								&& httpRequest.status == 200) {
							var rsrvCountUnit = new RsrvCountUnit();
							var rsrvIOUnit = new RsrvIOUnit();
							rsrvIOUnit.changeConfirmToCancelRsrv(resrevationItem);
							rsrvCountUnit.updateCountAll();
							}
					};
					httpRequest.open("PUT", "/reservationweb/api/reservations/" + reservationId, true);
					httpRequest.send();
				}
			})
		}
}






