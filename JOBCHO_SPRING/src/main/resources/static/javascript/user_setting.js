document.addEventListener("DOMContentLoaded", () => {
	const toggleItems = document.querySelectorAll(".personal_info_items");

	toggleItems.forEach((item) => {
		const arrowImg = item.querySelector("img[alt='arrow_down']");
		const userInfo = item.querySelector(".user_info");
		const changeForm = item.querySelector(".change_form");
		const inputContent = item.querySelector(".change_form input");
		const confirmBtn = item.querySelector(".confirm_btn");

		if (!arrowImg || !changeForm || !userInfo) return;

		// 🌿 입력값이 있을 경우 확인버튼 컬러 변경
		// 유효성 검사 필요
		inputContent.addEventListener("input", () => {
			if (confirmBtn.classList.contains("delete_btn")) {
				return;
			}

			if (inputContent.value.length > 0) {
				confirmBtn.style.backgroundColor = "rgb(6, 195, 115)";
				confirmBtn.style.borderColor = "rgb(6, 195, 115)";
			} else {
				confirmBtn.style.backgroundColor = "rgb(169, 169, 169)";
				confirmBtn.style.borderColor = "rgb(169, 169, 169)";
			}
		});


		// 🌿 토글
		item.addEventListener("click", () => {
			if (changeForm.style.display === "block") return;
			const isDown = arrowImg.src.includes("arrow-down.png");

			arrowImg.src = isDown
				? "/images/arrow-up.png"
				: "/images/arrow-down.png";

			// 🌟 '잡초 계정 삭제'는 userInfo 숨기지 말기
			if (!item.classList.contains("profile_delete")) {
				userInfo.style.display = isDown ? "none" : "block";
			}
			changeForm.style.display = isDown ? "block" : "none";
		});


		// 🌿 폼 내에서 클릭 시 버블링 방지
		changeForm.addEventListener("click", (e) => {
			e.stopPropagation();
		});

		// 🌿 취소 버튼 처리
		const cancelBtn = changeForm.querySelector(".cancel_btn");
		cancelBtn.addEventListener("click", (e) => {
			e.stopPropagation();
			arrowImg.src = "/images/arrow-down.png";
			userInfo.style.display = "block";
			changeForm.style.display = "none";
		});
	});
});
