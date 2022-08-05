$(document).ready(function () {
        $('.ui.checkbox').checkbox();
    });

const initCheck = () => {
    const trElems = document.querySelectorAll('tr:not(#head_tr)');
    trElems.forEach(item => {
        const champName = item.getAttribute('class');
        const parentCheck = item.querySelector('.parent');
        const childChecks = item.querySelectorAll('.child');
        const ifAllCheck = $(childChecks[0]).checkbox('is checked') &&
            $(childChecks[1]).checkbox('is checked') &&
            $(childChecks[2]).checkbox('is checked') &&
            $(childChecks[3]).checkbox('is checked') &&
            $(childChecks[4]).checkbox('is checked');
        const ifAllNotCheck = $(childChecks[0]).checkbox('is unchecked') &&
            $(childChecks[1]).checkbox('is unchecked') &&
            $(childChecks[2]).checkbox('is unchecked') &&
            $(childChecks[3]).checkbox('is unchecked') &&
            $(childChecks[4]).checkbox('is unchecked');

        if(ifAllCheck) {
            $(parentCheck).checkbox('set checked');
        } else if(ifAllNotCheck) {
            $(parentCheck).checkbox('set unchecked');
        } else {
            $(parentCheck).checkbox('set indeterminate');
        }
    });
}
initCheck();