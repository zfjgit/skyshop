$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var addrManage = new AddrManageInfo();
    uku.registerController("addrManage", addrManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        addListeners();
    });
    uku.addListener(Ukulele.REFRESH, function(e) {
        fixUkuRepeatComment();
        addrManage.page.loading = false;
    });
    uku.init();
});

function searchList() {
    var data = {
        fullAddress : addrManage.addrName,
        agency : {
            id : config.agency.id
        },
        page : addrManage.page.current + 1,
        pageSize : addrManage.page.pageSize
    }

    var params = $.customParam(data);
    
    getList('/installaddr/', params, function(d) {
        addrManage.addrs = [];
        var datas = d.data.datas;
        for (var i = 0; i < datas.length; i++) {
            var c = datas[i];
            var addr = new InstallAddressInfo();
            addr.id = c.id;
            addr.name = c.name;
            addr.fullAddress = c.fullAddress;
            addr.contact = c.contact;
            addr.contactNumber = c.contactNumber;
            addr.status = c.status;
            addr.agency = c.agency;
            addr.province = c.province;
            addr.city = c.city;
            addr.district = c.district;
            addr.detailAddress = c.detailAddress;
            addr.description = c.description;
            addr.deleteStatus = c.deleteStatus;
            
            addr.detailUrl = '../addr/' + addr.detailUrl + c.id;
            
            addrManage.addrs.push(addr);
        }
        addrManage.page = {
            current : d.data.current,
            pages : d.data.pages,
            total : d.data.total,
            pageSize : d.data.pageSize,
            hasNextPage : d.data.hasNextPage
        };
        if (addrManage.page.hasNextPage) {
            if (addrManage.page.current == 1) {
                $(document.body).infinite();
                $('.weui-loading').show();
                $('.weui-loadmore__tips').text('正在加载');
            }
        } else {
            $(document.body).destroyInfinite();
            $('.weui-loading').hide();
            $('.weui-loadmore__tips').text('共 ' + addrManage.page.total + ' 条，已加载完毕');
        }
        
        config.uku.refresh('addrManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        addrManage.page.current = 0;
        fixUkuRepeatComment(true);
        searchList();
    });
    $(document.body).infinite().on("infinite", function() {
        if (addrManage.page.loading) {
            return;
        }
        addrManage.page.loading = true;
        searchList();
    });
}