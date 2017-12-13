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
    uku.init();
});

function searchList() {
    var data = {
        fullAddress : addrManage.addrName
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
            
            addr.detailUrl += c.id;
            
            addrManage.addrs.push(addr);
        }
        config.uku.refresh('addrManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}