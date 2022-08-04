const urlParams = new URLSearchParams(window.location.search);
$(document).ready(() => {
        if (urlParams.has('error')) {
            alert('올바른 아이디와 비밀번호를 입력해주세요.');
        }
    }
);

const loginBtnElem = document.querySelector('#loginBtn');
const loginFormElem = document.querySelector('#loginForm');

loginBtnElem.addEventListener('click', () => {
    loginFormElem.submit();
});