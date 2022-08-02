const tbodyElem = document.querySelector('tbody');
const searchElem = document.querySelector('.search');

var content = [];

const getChampList = () => {
    fetch('/getchamplist')
        .then(res => res.json())
        .then(data => {
            data.forEach(champ => {
                content.push({title: champ.krnm});
            });

            $('.ui.search').search({
                source: content
            });
        }).catch(err => { console.error(err); });
}

getChampList();

$('.ui.dropdown').dropdown();
$('.ui.checkbox').checkbox('set checked');

searchElem.addEventListener('keypress', e => {
    if(e.key === 'Enter') {
        getResult();
    }
});

const getResult = () => {
    const tiers = [];
    if($('#silver').checkbox('is checked')) {
        tiers.push('silver');
    }
    if($('#gold_plus').checkbox('is checked')) {
        tiers.push('gold_plus');
    }

    fetch('/getstats?lane=' + $('.ui.dropdown').dropdown('get value')
    + '&tiers=' + tiers
    + '&champname=' + $('.ui.search').search('get value'))
        .then(res => res.json())
        .then(data => {
            tbodyElem.innerHTML = ``;

            data.forEach(item => {
                const trElem = document.createElement('tr');
                const games = item.games.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                trElem.innerHTML = `
                <td>${item.vschampname}</td>
                <td>${games}</td>
                <td>${item.delta}</td>
                `
                tbodyElem.append(trElem);
            });
        })
        .catch(err => { console.error(err); });
}