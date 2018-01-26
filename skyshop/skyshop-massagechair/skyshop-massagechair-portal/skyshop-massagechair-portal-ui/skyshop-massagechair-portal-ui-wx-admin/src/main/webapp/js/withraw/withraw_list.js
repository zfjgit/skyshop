$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var recordManage = new WithrawRecordManageInfo();
    uku.registerController("recordManage", recordManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        initStatusPicker();
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        recordManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var statusName = $('#status-picker').val();
    console.log('statusName=' + statusName);
    
    var data = {
        startDate : recordManage.startDate,
        endDate : recordManage.endDate,
        agency : {
            id : config.agency.id
        },
        status : {
            code : recordManage.statusPairs[statusName]
        },
        page : recordManage.page.current + 1,
        pageSize : recordManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/agencywithraw/', params, function(d) {
        recordManage.records = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var record = new WithrawInfo();
            record.id = c.id;
            record.createTime = c.createTime;
            record.money = parseFloat(c.money).toFixed(2);
            record.status = c.status;
            record.bank = c.bank;
            record.account = c.account;
            record.accountName = c.accountName;
            
            record.detailUrl += c.id;
            
            recordManage.records.push(record);
        }
        recordManage.total = parseFloat(d.data.sum).toFixed(2);
        
        recordManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (recordManage.page.hasNextPage) {
            if (recordManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + recordManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('recordManage');
    });
}

function initStatusPicker() {
    var itemNames = [];
    for (var i = 0; i < recordManage.statusItems.length; i++) {
        var s = recordManage.statusItems[i];
        itemNames.push(s.name);
        recordManage.statusPairs[s.name] = s.code;
    }
    $("#status-picker").select({
        title : "选择处理状态",
        items : itemNames,
        input : itemNames[0]
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
        recordManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (recordManage.page.loading) {
            return;
        }
        recordManage.page.loading = true;
        searchList();
    });
}