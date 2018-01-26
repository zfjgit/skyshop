$(function() {
    config.initSessionInfo();
    
    var orderInfo = new OrderInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        showOperateError();
    } else {
        orderInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("orderInfo", orderInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (orderInfo.id && orderInfo.id != 0) {
        get('/order/' + orderInfo.id, '', function(d) {
            var c = d.data;
            orderInfo.id = c.id;
            orderInfo.code = c.code;
            orderInfo.money = c.money;
            orderInfo.chair = c.chair;
            orderInfo.agency = c.agency;
            orderInfo.minutes = c.minutes;
            orderInfo.payType = c.payType;
            orderInfo.payStatus = c.payStatus;
            orderInfo.createTime = c.createTime;
            orderInfo.deleteStatus = c.deleteStatus;
            orderInfo.installationAddress = c.installationAddress;
            
            config.uku.refresh('orderInfo');
        });
    }
}

function addListeners() {
    $('#btn_partition').on('click', function() {
        showConfirmMsg('订单重新分成会删除之前的分成数据，生成新的分成数据，确定要继续吗？', function() {
            post('/order/partition/' + orderInfo.id, '', function() {
                showOkMsg('订单分成成功', function() {
                    
                });
            });
        }, function() {
        });
    });
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/order/' + orderInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
    
}