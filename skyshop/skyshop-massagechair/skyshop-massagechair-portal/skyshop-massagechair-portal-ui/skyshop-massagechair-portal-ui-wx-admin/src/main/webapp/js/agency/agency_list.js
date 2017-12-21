$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var agencyManage = new AgencyManageInfo();
    uku.registerController("agencyManage", agencyManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        agencyManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        parent : {
            id : config.agency.id
        },
        name : agencyManage.agencyName,
        page : agencyManage.page.current + 1,
        pageSize : agencyManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/agency/', params, function(d) {
        agencyManage.agencys = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var agency = new AgencyInfo();
            agency.id = c.id;
            agency.name = c.name;
            agency.level = c.level;
            agency.parent = c.parent;
            agency.balance = c.balance;
            agency.deleteStatus = c.deleteStatus;
            agency.percentage = c.orderIncomePercentage;
            
            agency.detailUrl += c.id;
            
            agencyManage.agencys.push(agency);
        }
        agencyManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (agencyManage.page.hasNextPage) {
            if (agencyManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + agencyManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('agencyManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        agencyManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    $(document.body).infinite().on("infinite", function() {
        if (agencyManage.page.loading) {
            return;
        }
        agencyManage.page.loading = true;
        searchList();
    });
}