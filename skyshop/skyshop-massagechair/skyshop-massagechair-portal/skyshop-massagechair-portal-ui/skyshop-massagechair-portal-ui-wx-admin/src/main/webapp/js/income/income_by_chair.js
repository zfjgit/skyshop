$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var chairIncomeManage = new ChairIncomeManageInfo();
    uku.registerController("chairIncomeManage", chairIncomeManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        chairIncomeManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        startDate : chairIncomeManage.startDate,
        endDate : chairIncomeManage.endDate,
        agency : {
            id : config.agency.id
        },
        page : chairIncomeManage.page.current + 1,
        pageSize : chairIncomeManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/orderpartition/chairincome', params, function(d) {
        chairIncomeManage.chairIncomes = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var chairIncomeInfo = new ChairIncomeInfo();
            chairIncomeInfo.id = c.id;
            chairIncomeInfo.chair = c.order.chair;
            chairIncomeInfo.money = parseFloat(c.money).toFixed(2);
            chairIncomeInfo.status = c.order.chair.status.name;
            
            chairIncomeInfo.detailUrl += c.order.chair.id;
            
            chairIncomeManage.chairIncomes.push(chairIncomeInfo);
        }
        chairIncomeManage.total = parseFloat(d.data.sum).toFixed(2);
        
        chairIncomeManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (chairIncomeManage.page.hasNextPage) {
            if (chairIncomeManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + chairIncomeManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('chairIncomeManage');
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
        chairIncomeManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (chairIncomeManage.page.loading) {
            return;
        }
        chairIncomeManage.page.loading = true;
        searchList();
    });
}