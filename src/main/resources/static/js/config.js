const parentCheckReset = champName => {
    const parentCheck = document.querySelector(`.${champName}.parent`);
    const childChecks = document.querySelectorAll(`.${champName}.child`);
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
}

const initCheck = () => {
    const trElems = document.querySelectorAll('tr:not(#head_tr)');
    trElems.forEach(item => {
        const champName = item.getAttribute('class');
        parentCheckReset(champName);

        const parentCheck = item.querySelector('.parent');
        const childChecks = item.querySelectorAll('.child');

        $(parentCheck).checkbox({
            onChecked: () => {
                $(childChecks).checkbox('set checked');
            },
            onUnchecked: () => {
                $(childChecks).checkbox('set unchecked');
            }
        });

        $(childChecks).checkbox({
            onChange: () => {
                parentCheckReset(champName);
            }
        })
    });
}
initCheck();

const getCheckedList = () => {
    const childChecks = document.querySelectorAll('.ui.checkbox.child');
    const arrayObject = {
        top: [],
        jungle: [],
        middle: [],
        bottom: [],
        support: []
    }

    childChecks.forEach(item => {
        if($(item).checkbox('is checked')) {
            const idx = item.parentNode.parentNode.dataset.idx;
            const laneName = item.dataset.lane;
            switch(laneName) {
                case 'top':
                    arrayObject.top.push(idx);
                    break;
                case 'jungle':
                    arrayObject.jungle.push(idx);
                    break;
                case 'middle':
                    arrayObject.middle.push(idx);
                    break;
                case 'bottom':
                    arrayObject.bottom.push(idx);
                    break;
                case 'support':
                    arrayObject.support.push(idx);
            }
        }
    });
    return arrayObject;
}

const initObject = getCheckedList();

const cancelBtnElem = document.querySelector('#cancelBtn');
cancelBtnElem.addEventListener('click', () => {
    window.location.reload();
});

const saveBtnElem = document.querySelector('#saveBtn');
saveBtnElem.addEventListener('click', () => {
    const changedObject = getCheckedList();
    if(JSON.stringify(initObject) === JSON.stringify(changedObject)) {
        alert('변경된 내용이 없습니다.');
        return;
    }

    fetch('/updateConfig', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/JSON'
        },
        body: JSON.stringify(changedObject)
    }).then(res => res.json())
        .then(data => {
            if(data.integer === 0) {
                alert('에러가 발생했습니다.');
            } else if(data.integer === 1) {
                alert('수정에 성공했습니다.');
                window.location.reload();
            }
        }).catch(err => { console.error(err); });
});