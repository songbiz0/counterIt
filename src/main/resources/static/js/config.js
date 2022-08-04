$('.ui.checkbox').checkbox();
const dataElem = document.querySelector('#data');
const dataTop = dataElem.dataset.top;
const dataJungle = dataElem.dataset.jungle;
const dataMiddle = dataElem.dataset.middle;
const dataBottom = dataElem.dataset.bottom;
const dataSupport = dataElem.dataset.support;

const initCheck = () => {
    Array.from(dataTop).forEach(item => {
        alert(item.userEntity.uid + ' ' + item.champEntity.krnm);
    });
}
initCheck();