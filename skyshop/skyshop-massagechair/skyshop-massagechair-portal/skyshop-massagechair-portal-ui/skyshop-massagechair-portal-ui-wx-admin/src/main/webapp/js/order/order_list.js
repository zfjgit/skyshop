$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var orderManage = new OrderManageInfo();
    uku.registerController("orderManage", orderManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        orderManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        startDate : orderManage.startDate,
        endDate : orderManage.endDate,
        agency : {
            id : config.agency.id
        },
        page : orderManage.page.current + 1,
        pageSize : orderManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/order/', params, function(d) {
        orderManage.orders = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var order = new OrderInfo();
            order.id = c.id;
            order.code = c.code;
            order.minutes = c.minutes;
            order.money = parseFloat(c.money).toFixed(2);
            order.agency = c.agency;
            order.chair = c.chair;
            order.payStatus = c.payStatus;
            order.payType = c.payType;
            order.deleteStatus = c.deleteStatus;
            order.createTime = c.createTime;
            
            order.detailUrl += c.id;
            
            orderManage.orders.push(order);
        }
        orderManage.total = parseFloat(d.data.sum).toFixed(2);
        
        orderManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (orderManage.page.hasNextPage) {
            if (orderManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + orderManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('orderManage');
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
        orderManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (orderManage.page.loading) {
            return;
        }
        orderManage.page.loading = true;
        searchList();
    });
}