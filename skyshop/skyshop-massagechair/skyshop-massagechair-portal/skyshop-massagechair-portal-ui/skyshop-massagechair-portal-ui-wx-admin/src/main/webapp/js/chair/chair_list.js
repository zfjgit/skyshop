$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var chairManage = new ChairManageInfo();
    uku.registerController("chairManage", chairManage);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        searchList();
        
        addListeners();
    })
    uku.init();
});

function searchList() {
    var data = {
        name : chairManage.chairName,
        agency : {
            id : config.agency.id
        }
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
        config.uku.refresh('chairManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}