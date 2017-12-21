$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var orderPartitionManage = new OrderPartitionManageInfo();
    uku.registerController("orderPartitionManage", orderPartitionManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        orderPartitionManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        agency : {
            id : config.agency.id
        },
        startDate : orderPartitionManage.startDate,
        endDate : orderPartitionManage.endDate,
        page : orderPartitionManage.page.current + 1,
        pageSize : orderPartitionManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/orderpartition/', params, function(d) {
        orderPartitionManage.orderPartitions = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var orderPartition = new OrderPartitionInfo();
            orderPartition.id = c.id;
            orderPartition.money = parseFloat(c.money).toFixed(2);
            orderPartition.order = c.order;
            orderPartition.order.money = parseFloat(c.order.money).toFixed(2);
            orderPartition.agency = c.agency;
            orderPartition.totalMoney = parseFloat(c.totalMoney).toFixed(2);
            orderPartition.percentage = c.percentage;
            
            orderPartitionManage.orderPartitions.push(orderPartition);
        }
        orderPartitionManage.total = parseFloat(d.data.sum).toFixed(2);
        
        orderPartitionManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (orderPartitionManage.page.hasNextPage) {
            if (orderPartitionManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + orderPartitionManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('orderPartitionManage');
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
        orderPartitionManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (orderPartitionManage.page.loading) {
            return;
        }
        orderPartitionManage.page.loading = true;
        searchList();
    });
}