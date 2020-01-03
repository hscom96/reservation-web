document.addEventListener("DOMContentLoaded", function() {
	let submitUnit = new SubmitUnit();
	submitUnit.submitForm();
});

function ValidUnit() {
}
ValidUnit.prototype = {
		validEmail : function() {
			var emailBox = document.querySelector("input#resrv_id");
			var emailValue = emailBox.value;
			var valid = (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(emailValue);

			if (!emailValue) {
				emailBox.placeholder = "이메일을 채워주세요";
				emailBox.classList.add('cautionInput');
				alert('잘못 입력하였습니다. 다시한번확인해주세요.');
				return false;
			} else if (!valid) {
				emailBox.value = "";
				emailBox.placeholder = "이메일 형식이 올바르지 않습니다";
				emailBox.classList.add('cautionInput');
				alert('잘못 입력하였습니다. 다시한번확인해주세요.');
				return false;
			}

			if (emailBox.classList.contains('cautionInput')) {
				emailBox.classList.remove('cautionInput');
				emailBox.placeholder = "crong@codesquad.kr";
			}
			return true;
		}
}

function SubmitUnit() {
}
SubmitUnit.prototype = {
		submitForm : function(displayInfoSet) {
			let submitBtn = document.querySelector(".login_btn.confirm")
			submitBtn.addEventListener('click', function(evt) {
				evt.preventDefault();
				let validUnit = new ValidUnit();
				let valid = validUnit.validEmail();
				if (valid) {
					document.querySelector("#form1").submit();
				}
			});
		}
}
