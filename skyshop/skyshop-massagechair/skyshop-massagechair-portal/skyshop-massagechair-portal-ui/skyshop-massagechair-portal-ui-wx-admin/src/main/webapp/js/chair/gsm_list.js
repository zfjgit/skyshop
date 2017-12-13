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
    uku.init();
});

function searchList() {
    var data = {
        imei : gsmManage.imei
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
        config.uku.refresh('gsmManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}