$(function() {
    config.initSessionInfo();
    
    var chairOrderPartitionManage = new ChairOrderPartitionManageInfo();
    
    var search = window.location.search;
    if (search && search != '?') {
        chairOrderPartitionManage.chair = {
            id : search.substring(1)
        };
    }
    
    var uku = new Ukulele();
    uku.registerController("chairOrderPartitionManage", chairOrderPartitionManage);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        chairOrderPartitionManage.page.loading = false;
    });
    uku.init();
});

function loadOne() {
    if (chairOrderPartitionManage.chair.id && chairOrderPartitionManage.chair.id != 0) {
        get('/chair/' + chairOrderPartitionManage.chair.id, '', function(d) {
            var c = d.data;
            chairOrderPartitionManage.chair.name = c.name;
            
            chairOrderPartitionManage.orderPartitions = [];
            
            config.uku.refresh('chairOrderPartitionManage');
            
            searchList();
        });
    }
}

function searchList() {
    
    var data = {
        startDate : chairOrderPartitionManage.startDate,
        endDate : chairOrderPartitionManage.endDate,
        agency : {
            id : config.agency.id
        },
        order : {
            chair : chairOrderPartitionManage.chair
        },
        page : chairOrderPartitionManage.page.current + 1,
        pageSize : chairOrderPartitionManage.page.pageSize
    };
    
    var params = $.customParam(data);
    
    getList('/orderpartition/chairincomedetail', params, function(d) {
        chairOrderPartitionManage.orderPartitions = [];
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
            
            chairOrderPartitionManage.orderPartitions.push(orderPartitionInfo);
        }
        chairOrderPartitionManage.total = parseFloat(d.data.sum).toFixed(2);
        
        chairOrderPartitionManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (chairOrderPartitionManage.page.hasNextPage) {
            if (chairOrderPartitionManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + chairOrderPartitionManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('chairOrderPartitionManage');
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
        chairOrderPartitionManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (chairOrderPartitionManage.page.loading) {
            return;
        }
        chairOrderPartitionManage.page.loading = true;
        searchList();
    });
}