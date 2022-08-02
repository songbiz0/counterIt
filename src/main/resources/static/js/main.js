var content = [];

const getChampList = () => {
    fetch('/getchamplist')
        .then(res => res.json())
        .then(data => {
            data.forEach(champ => {
                content.push({title: champ.krnm});
                console.log(champ);
            });

            $('.ui.search').search({
                source: content
            });
        }).catch(err => { console.error(err); });
}

getChampList();

$('.ui.dropdown').dropdown();
$('.ui.checkbox').checkbox('set checked');

const searchElem = document.querySelector('.search');

searchElem.addEventListener('keypress', e => {
    if(e.key === 'Enter') {
        getResult();
    }
});

const getResult = () => {
    fetch('/getstats?lane=' + $('.ui.dropdown').dropdown('get text')
    + '&silver=' + $('#silver').checkbox('is checked')
    + '&gold_plus=' + $('#gold_plus').checkbox('is checked')
    + '&champname=' + $('.ui.search').search('get value'))
        .then(res => res.json())
        .then(data => { console.log(data); })
        .catch(err => { console.error(err); });
}