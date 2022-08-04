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

    const lane = $('.ui.dropdown').dropdown('get value');
    const champName = $('.ui.search').search('get value');
    fetch('/getstats?lane=' + lane
    + '&tiers=' + tiers
    + '&champname=' + champName)
        .then(res => res.json())
        .then(data => {
            tbodyElem.innerHTML = ``;

            data.forEach(item => {
                const trElem = document.createElement('tr');
                const games = item.games.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                trElem.innerHTML = `
                <td>${item.krnm}</td>
                <td>${games}</td>
                <td>${item.delta}</td>
                `

                trElem.addEventListener('click', () => {
                    makeLink(champName, lane, item.vsennm);
                });
                tbodyElem.append(trElem);
            });
        })
        .catch(err => { console.error(err); });
}

const makeLink = (krnm, lane, vsennm) => {
    fetch('/getennm?name=' + krnm)
        .then(res => res.json())
        .then(data => {
            window.open(`https://lolalytics.com/lol/${vsennm}/vs/${data.result}/build/?lane=${lane}&vslane=${lane}&tier=gold_plus`);
        }).catch(err => { console.error(err); });
}