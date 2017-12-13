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
    uku.init();
});

function searchList() {
    var data = {
        parent : {
            id : config.agency.id
        },
        name : agencyManage.agencyName
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
        config.uku.refresh('agencyManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}