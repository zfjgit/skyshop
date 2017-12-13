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
    uku.init();
});

function searchList() {
    var data = {
        startDate : orderManage.startDate,
        endDate : orderManage.endDate,
        agency : config.agency
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
            order.money = c.money;
            order.agency = c.agency;
            order.chair = c.chair;
            order.payStatus = c.payStatus;
            order.payType = c.payType;
            order.deleteStatus = c.deleteStatus;
            order.createTime = c.createTime;
            
            orderManage.total += c.money;
            orderManage.total = parseFloat(orderManage.total).toFixed(2);
            
            order.detailUrl += c.id;
            
            orderManage.orders.push(order);
        }
        config.uku.refresh('orderManage');
    });
}

function addListeners() {
    $("#startDate").calendar({
        'dateFormat' : 'yyyy-mm-dd'
    });
    
    $("#endDate").calendar({
        'dateFormat' : 'yyyy-mm-dd'
    });
    
    $('#btn_search').on('click', function() {
        searchList();
    });
}