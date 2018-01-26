$(function() {
    config.initSessionInfo();
    
    var withraw = new WithrawInfo();
    var search = window.location.search;
    if (!search || search == '?') {
        
    } else {
        withraw.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("withraw", withraw);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (withraw.id) {
        get('/agencywithraw/' + withraw.id, '', function(d) {
            withraw.money = parseFloat(d.data.money).toFixed(2);
            withraw.bank = {
                bank : d.data.bank,
                account : d.data.account,
                accountName : d.data.accountName
            };
            withraw.createTime = d.data.createTime;
            withraw.status = {
                name : d.data.status.name
            };
            withraw.agency = config.agency;
            
            config.uku.refresh("withraw");
        });
    }
}

function addListeners() {
    
}