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
    uku.init();
});

function searchList() {
    var data = {
        sim : simManage.sim
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
        config.uku.refresh('simManage');
    });
}

function addListeners() {
    $('#btn_search').on('click', function() {
        searchList();
    });
}