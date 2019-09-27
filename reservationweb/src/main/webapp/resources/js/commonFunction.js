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

//쿠키읽기
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}


