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