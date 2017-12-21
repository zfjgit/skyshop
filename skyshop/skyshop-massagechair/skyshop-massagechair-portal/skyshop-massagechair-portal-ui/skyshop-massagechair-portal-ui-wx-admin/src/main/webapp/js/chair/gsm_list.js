$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var gsmManage = new GSMManageInfo();
    uku.registerController("gsmManage", gsmManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        gsmManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        imei : gsmManage.imei,
        page : gsmManage.page.current + 1,
        pageSize : gsmManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/gsmmodule/', params, function(d) {
        gsmManage.gsms = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var gsm = new GSMModuleInfo();
            gsm.id = c.id;
            gsm.name = c.name;
            gsm.imei = c.imei;
            gsm.module = c.module;
            gsm.status = c.status;
            gsm.simCard = c.simCard;
            gsm.deleteStatus = c.deleteStatus;
            gsm.detailUrl += c.id;
            
            gsmManage.gsms.push(gsm);
        }
        
        gsmManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (gsmManage.page.hasNextPage) {
            if (gsmManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + gsmManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('gsmManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        gsmManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (gsmManage.page.loading) {
            return;
        }
        gsmManage.page.loading = true;
        searchList();
    });
}