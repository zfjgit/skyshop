$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var simManage = new SIMManageInfo();
    uku.registerController("simManage", simManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        simManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        sim : simManage.sim,
        needRecharge : simManage.needRecharge,
        page : simManage.page.current + 1,
        pageSize : simManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/simcard/', params, function(d) {
        simManage.sims = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var sim = new SIMCardInfo();
            sim.id = c.id;
            sim.name = c.name;
            sim.sim = c.sim;
            sim.dataFlow = c.dataFlow;
            sim.dueDate = c.dueDate;
            sim.status = c.status;
            sim.operator = c.operator;
            sim.deleteStatus = c.deleteStatus;
            sim.detailUrl += c.id;
            
            simManage.sims.push(sim);
        }
        simManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (simManage.page.hasNextPage) {
            if (simManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + simManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('simManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        simManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (simManage.page.loading) {
            return;
        }
        simManage.page.loading = true;
        searchList();
    });
}