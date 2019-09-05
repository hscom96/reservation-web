document.addEventListener(
		"DOMContentLoaded",
		function(){
			goTop();
		}
);

function goTop() {
	var topIcon = document.querySelector(".lnk_top");

	topIcon.addEventListener("click", function() {
		window.scrollTo({ top: 0, behavior: 'smooth' })
	});
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

