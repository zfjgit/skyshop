$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var addrIncomeManage = new AddrIncomeManageInfo();
    uku.registerController("addrIncomeManage", addrIncomeManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        addrIncomeManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        startDate : addrIncomeManage.startDate,
        endDate : addrIncomeManage.endDate,
        agency : {
            id : config.agency.id
        },
        page : addrIncomeManage.page.current + 1,
        pageSize : addrIncomeManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/report/addrincomes', params, function(d) {
        addrIncomeManage.addrIncomes = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var addrIncomeInfo = new AddrIncomeInfo();
            addrIncomeInfo.addr = {
                id : c.addrId,
                name : c.addrName
            };
            addrIncomeInfo.chairCount = c.chairCount;
            
            addrIncomeInfo.money = c.money;
            addrIncomeInfo.chairAvgMoney = parseFloat(c.chairAvgMoney).toFixed(2);
            addrIncomeInfo.orderCount = c.orderCount;
            
            addrIncomeInfo.detailUrl += c.addrId;
            
            addrIncomeManage.addrIncomes.push(addrIncomeInfo);
        }
        addrIncomeManage.total = parseFloat(d.data.sum).toFixed(2);
        
        addrIncomeManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (addrIncomeManage.page.hasNextPage) {
            if (addrIncomeManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + addrIncomeManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('addrIncomeManage');
    });
}

function addListeners() {
    $("#startDate").calendar({
        'dateFormat' : 'yyyy-mm-dd',
        'value' : [ today(-30) ]
    });
    $("#endDate").calendar({
        'dateFormat' : 'yyyy-mm-dd',
    });
    
    $('#btn_search').on('click', function() {
        addrIncomeManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (addrIncomeManage.page.loading) {
            return;
        }
        addrIncomeManage.page.loading = true;
        searchList();
    });
}