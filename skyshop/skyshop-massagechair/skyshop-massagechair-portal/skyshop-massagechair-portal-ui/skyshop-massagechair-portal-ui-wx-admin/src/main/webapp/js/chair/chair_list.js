$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var chairManage = new ChairManageInfo();
    uku.registerController("chairManage", chairManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        chairManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        name : chairManage.chairName,
        agency : {
            id : config.agency.id
        },
        page : chairManage.page.current + 1,
        pageSize : chairManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/chair/', params, function(d) {
        chairManage.chairs = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var chair = new MassageChairInfo();
            chair.id = c.id;
            chair.name = c.name;
            chair.status = c.status;
            chair.deleteStatus = c.deleteStatus;
            chair.installationAddress = c.installationAddress;
            
            chair.detailUrl += c.id;
            chairManage.chairs.push(chair);
        }
        
        chairManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (chairManage.page.hasNextPage) {
            if (chairManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + chairManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('chairManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        chairManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    
    $(document.body).infinite().on("infinite", function() {
        if (chairManage.page.loading) {
            return;
        }
        chairManage.page.loading = true;
        searchList();
    });
}