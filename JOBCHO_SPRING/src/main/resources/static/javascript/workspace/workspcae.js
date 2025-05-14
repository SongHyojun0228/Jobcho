const icon = document.getElementById('plus_icon');
const menuIcon = document.getElementById('menu_icon');
const popup = document.getElementById('popup');
const popup2 = document.getElementById('popup2');
const noticeLink = document.getElementById('notice-link');
const menuLink = document.getElementById('menu_link');;
const overlay = document.getElementById('overlay');
const modal = document.getElementById('modal');
const taskLink = document.getElementById('task-link');
const voteLink = document.getElementById('vote-link');
const bookmarkLink = document.getElementById('bookmark-link');
const wrapper = document.querySelector('.main');
const bookmarkTotalContainer = document.querySelector('.bookmark_total_container');
const taskTotalContainer = document.querySelector('.task_total_container');


taskLink.addEventListener('click', (e) => {
    e.preventDefault();
    wrapper.classList.toggle('show-aside');
    taskTotalContainer.style.display = (taskTotalContainer.style.display === 'block') ? 'none' : 'block';
});

bookmarkLink.addEventListener('click', (e) => {
    e.preventDefault();
    wrapper.classList.toggle('show-aside');
    bookmarkTotalContainer.style.display = (bookmarkTotalContainer.style.display === 'block') ? 'none' : 'block';
});

//아이콘 ... 
icon.addEventListener('click', () => {
    popup.style.display = (popup.style.display === 'block') ? 'none' : 'block';
});
noticeLink.addEventListener('click', (e) => {
    e.preventDefault();
    overlay.style.display = 'block';
    modal.style.display = 'block';
});

//아이콘 메뉴 더보기
menuLink.addEventListener('click', (e) => {
    e.preventDefault();
    popup2.style.display = (popup2.style.display === 'block') ? 'none' : 'block';
});
document.addEventListener('click', (event) => {
    const isClickInsideIcon = icon.contains(event.target);
    const isClickInsidePopup = popup.contains(event.target);
    const isClickInsideMenuIcon = menuIcon.contains(event.target);
    const isClickInsidePopup2 = popup2.contains(event.target);

    if (!isClickInsideIcon && !isClickInsidePopup) {
        popup.style.display = 'none';
    }

    if (!isClickInsideMenuIcon && !isClickInsidePopup2) {
        popup2.style.display = 'none';
    }
});

//공지사항 업로드 스크립트 불러옴
document.addEventListener("DOMContentLoaded", () => {
    const textArea = document.querySelector(".register_form textarea");
    const originText = document.querySelector(".register_form textarea").value;
    const numberOfText = document.querySelector(".numbers_of_text span");
    const editBtn = document.querySelector(".register_btn");

    textArea.addEventListener("input", () => {
        if (textArea.value !== originText && textArea.value.length > 0) {
            editBtn.style.backgroundColor = "rgb(6, 195, 115)";
            editBtn.style.color = "rgb(250, 250, 250)";
        } else {
            editBtn.style.backgroundColor = "rgb(250, 250, 250)";
            editBtn.style.color = "rgb(200, 200, 200)";
        }

        numberOfText.textContent = textArea.value.length;
    });
});

//즐겨찾기 스크립트 불러옴
document.addEventListener("DOMContentLoaded", () => {
    const allFormBtn = document.querySelector(".all_form_btn");
    const fileFormBtn = document.querySelector(".file_form_btn");

    allFormBtn.addEventListener("click", () => {
        fileFormBtn.style.borderColor = "rgb(250, 250, 250)";
        fileFormBtn.style.fontWeight = "400";
        allFormBtn.style.borderColor = "black";
        allFormBtn.style.fontWeight = "700";
    });

    fileFormBtn.addEventListener("click", () => {
        allFormBtn.style.borderColor = "rgb(250, 250, 250)";
        allFormBtn.style.fontWeight = "400";
        fileFormBtn.style.fontWeight = "700";
        fileFormBtn.style.borderColor = "black";
    });
});