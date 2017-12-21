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
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        priceManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        name : priceManage.priceName,
        page : priceManage.page.current + 1,
        pageSize : priceManage.page.pageSize
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
            price.money = parseFloat(c.price).toFixed(2);
            price.agency = c.agency;
            
            price.detailUrl += c.id;
            
            priceManage.prices.push(price);
        }
        priceManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (priceManage.page.hasNextPage) {
            if (priceManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + priceManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('priceManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        priceManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (priceManage.page.loading) {
            return;
        }
        priceManage.page.loading = true;
        searchList();
    });
}