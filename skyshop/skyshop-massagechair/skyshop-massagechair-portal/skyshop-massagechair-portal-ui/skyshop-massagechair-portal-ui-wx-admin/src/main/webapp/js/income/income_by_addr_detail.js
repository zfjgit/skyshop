$(function() {
    config.initSessionInfo();
    
    var addrOrderManage = new AddrOrderManageInfo();
    
    var search = window.location.search;
    if (search && search != '?') {
        addrOrderManage.addr = {
            id : search.substring(1)
        };
    }
    
    var uku = new Ukulele();
    uku.registerController("addrOrderManage", addrOrderManage);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        addrOrderManage.page.loading = false;
    });
    uku.init();
});

function loadOne() {
    if (addrOrderManage.addr.id && addrOrderManage.addr.id != 0) {
        get('/installaddr/' + addrOrderManage.addr.id, '', function(d) {
            var c = d.data;
            addrOrderManage.addr = {
                id : c.id,
                name : c.fullAddress
            };
            
            addrOrderManage.orderPartitions = [];
            
            config.uku.refresh('addrOrderManage');
            
            searchList();
        });
    }
}

function searchList() {
    
    var data = {
        startDate : addrOrderManage.startDate,
        endDate : addrOrderManage.endDate,
        agency : {
            id : config.agency.id
        },
        order : {
            installationAddress : addrOrderManage.addr
        },
        page : addrOrderManage.page.current + 1,
        pageSize : addrOrderManage.page.pageSize
    };
    
    var params = $.customParam(data);
    
    getList('/report/addrincomedetails', params, function(d) {
        addrOrderManage.orderPartitions = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var orderPartitionInfo = new OrderPartitionInfo();
            orderPartitionInfo.id = c.id;
            orderPartitionInfo.order = c.order;
            orderPartitionInfo.order.money = parseFloat(c.order.money).toFixed(2);
            orderPartitionInfo.money = parseFloat(c.money).toFixed(2);
            orderPartitionInfo.percentage = c.percentage;
            
            orderPartitionInfo.detailUrl += c.id;
            
            addrOrderManage.orderPartitions.push(orderPartitionInfo);
        }
        addrOrderManage.total = parseFloat(d.data.sum).toFixed(2);
        
        addrOrderManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (addrOrderManage.page.hasNextPage) {
            if (addrOrderManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + addrOrderManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('addrOrderManage');
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
        addrOrderManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (addrOrderManage.page.loading) {
            return;
        }
        addrOrderManage.page.loading = true;
        searchList();
    });
}