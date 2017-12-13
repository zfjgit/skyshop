$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var priceManage = new PriceManageInfo();
    uku.registerController("priceManage", priceManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.init();
});

function searchList() {
    var data = {
        name : priceManage.priceName
    }

    var params = $.customParam(data);
    
    getList('/price/', params, function(d) {
        priceManage.prices = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var price = new PriceInfo();
            price.id = c.id;
            price.name = c.name;
            price.minutes = c.minutes;
            price.money = c.price;
            price.agency = c.agency;
            
            price.detailUrl += c.id;
            
            priceManage.prices.push(price);
        }
        config.uku.refresh('priceManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}