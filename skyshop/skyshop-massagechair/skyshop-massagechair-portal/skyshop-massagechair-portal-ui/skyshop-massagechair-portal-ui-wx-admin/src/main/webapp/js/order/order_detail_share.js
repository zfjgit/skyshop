$(function() {
    config.initSessionInfo();
    
    var orderPartitionManageInfo = new OrderPartitionManageInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        showOperateError();
    } else {
        orderPartitionManageInfo.order = {
            id : search.substring(1)
        };
    }
    
    var uku = new Ukulele();
    uku.registerController("orderPartitionManageInfo", orderPartitionManageInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        
        addListeners();
    });
    uku.init();
});

function searchList() {
    if (orderPartitionManageInfo.order.id && orderPartitionManageInfo.order.id != 0) {
        
        var data = {
            order : {
                id : orderPartitionManageInfo.order.id
            }
        };
        
        var params = $.customParam(data);
        
        getList('/orderpartition/order', params, function(d) {
            orderPartitionManageInfo.orderPartitions = [];
            for (var i = 0; i < d.data.datas.length; i++) {
                var c = d.data.datas[i];
                var orderPartitionInfo = new OrderPartitionInfo();
                orderPartitionInfo.money = parseFloat(c.money).toFixed(2);
                orderPartitionInfo.totalMoney = parseFloat(c.totalMoney).toFixed(2);
                orderPartitionInfo.order = c.order;
                orderPartitionInfo.agency = c.agency;
                if (!orderPartitionInfo.agency) {
                    orderPartitionInfo.agency = {
                        name : '平台'
                    };
                }
                orderPartitionInfo.percentage = c.percentage;
                orderPartitionManageInfo.orderPartitions.push(orderPartitionInfo);
            }
            
            config.uku.refresh('orderPartitionManageInfo');
        });
    }
}

function addListeners() {
    
}