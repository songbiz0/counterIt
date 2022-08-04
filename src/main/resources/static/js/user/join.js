const joinFormElem = document.querySelector('#joinForm');

const uidElem = document.querySelector('#uid');
const uidInput = uidElem.querySelector('input');

const upwElem = document.querySelector('#upw');
const upwInput = upwElem.querySelector('input');

const upwconElem = document.querySelector('#upwcon');
const upwconInput = upwconElem.querySelector('input');

const joinCancelBtnElem = document.querySelector('#joinCancelBtn');

let idOk = false;

const makeErrorBox = (elem, msg) => {
    elem.classList.add('error');
    elem.classList.remove('info');
    elem.querySelector('div').classList.remove('hidden');
    elem.querySelector('div').classList.remove('cinfo');
    elem.querySelector('div').classList.add('cerror');
    elem.querySelector('div').innerText = msg;
}

const makeInfoBox = (elem, msg) => {
    elem.classList.add('info');
    elem.classList.remove('error');
    elem.querySelector('div').classList.remove('hidden');
    elem.querySelector('div').classList.remove('cerror');
    elem.querySelector('div').classList.add('cinfo');
    elem.querySelector('div').innerText = msg;
}

uidInput.addEventListener('keyup', () => {
        fetch(`/user/idChk/${uidInput.value}`)
            .then(res => res.json())
            .then(data => {
                if (data.integer === 0) {
                    makeErrorBox(uidElem, '이미 등록되어 있는 아이디입니다.');
                    idOk = false;
                } else {
                    makeInfoBox(uidElem, '사용 가능한 아이디입니다.');
                    idOk = true;
                }
            });
});

joinFormElem.addEventListener('submit', e => {
    if(!idOk) {
        alert('중복되지 않은 아이디를 입력해주세요.');
        e.preventDefault();
        return;
    }

    if(!upwInput.value.length > 0) {
        makeErrorBox(upwElem, '비밀번호를 입력해주세요.');
        e.preventDefault();
    }
    if(upwInput.value.length > 20) {
        makeErrorBox(upwElem, '비밀번호를 20자 이내로 입력해주세요.');
        e.preventDefault();
    }
    if(upwInput.value !== upwconInput.value) {
        makeErrorBox(upwconElem, '비밀번호가 일치하지 않습니다.');
        e.preventDefault();
    }
});

joinCancelBtnElem.addEventListener('click', e => {
    e.preventDefault();
    window.history.back();
});