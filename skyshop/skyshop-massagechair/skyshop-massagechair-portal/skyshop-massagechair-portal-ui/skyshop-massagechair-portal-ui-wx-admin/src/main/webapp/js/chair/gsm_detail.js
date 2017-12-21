$(function() {
    config.initSessionInfo();
    
    var gsmInfo = new GSMModuleInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        $('.h_title').text('新增GSM模块');
        $('#btn_delete').hide();
        $('.t_deletestatus_field').hide();
    } else {
        gsmInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("gsmInfo", gsmInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (gsmInfo.id && gsmInfo.id != 0) {
        get('/gsmmodule/' + gsmInfo.id, '', function(d) {
            gsmInfo.id = d.data.id;
            gsmInfo.simCard = d.data.simCard;
            gsmInfo.imei = d.data.imei;
            gsmInfo.name = d.data.name;
            gsmInfo.status = d.data.status;
            gsmInfo.module = d.data.module;
            gsmInfo.deleteStatus = d.data.deleteStatus;
            
            config.uku.refresh('gsmInfo');
            
            initStatusPicker();
            initSIMPicker();
        });
    } else {
        initStatusPicker();
        initSIMPicker();
    }
}

function initStatusPicker() {
    var status = [];
    for ( var k in gsmInfo.statusPairs) {
        var ele = gsmInfo.statusPairs[k];
        if (k && (typeof k) == 'string' && ele && (typeof ele) == 'string') {
            status.push(k);
        }
    }
    
    $("#status-picker").picker({
        title : "请选择状态",
        cols : [ {
            textAlign : 'center',
            values : status
        } ]
    });
    
    if (gsmInfo.id != 0 && gsmInfo.status) {
        $("#status-picker").val(gsmInfo.status.name);
    } else {
        $("#status-picker").val(status[0]);
    }
}
function initSIMPicker() {
    getList('/simcard/?deleteStatus.code=0&status.code=A', '', function(d) {
        var sims = d.data.datas;
        var names = [];
        if (gsmInfo.id != 0 && gsmInfo.simCard) {
            names.push(gsmInfo.simCard.name);
            gsmInfo.simPairs[gsmInfo.simCard.name] = gsmInfo.simCard.id;
        }
        
        for (var i = 0; i < sims.length; i++) {
            var sim = sims[i];
            names.push(sim.name);
            gsmInfo.simPairs[sim.name] = sim.id;
        }
        
        $("#sim-picker").picker({
            title : "请选择模块",
            cols : [ {
                textAlign : 'center',
                values : names
            } ]
        });
        
        if (gsmInfo.id != 0 && gsmInfo.simCard) {
            $("#sim-picker").val(gsmInfo.simCard.name);
        } else {
            $("#sim-picker").val(names[0]);
        }
    });
}

function addListeners() {
    $('#btn_save').on('click', function() {
        if (gsmInfo.imei == '') {
            showMsg('请输入IMEI号');
            return;
        }
        
        var sim = $('#sim-picker').val();
        if (sim == '') {
            showMsg('请选择SIM卡');
            return;
        }
        gsmInfo.simCard['name'] = sim;
        gsmInfo.simCard['id'] = gsmInfo.simPairs[sim];
        
        var status = $("#status-picker").val();
        if (status != '' && status != '无') {
            if (!gsmInfo.status) {
                gsmInfo.status = {};
            }
            gsmInfo.status['name'] = status;
            gsmInfo.status['code'] = gsmInfo.statusPairs[status];
        }
        
        var gsm = {
            id : gsmInfo.id,
            name : gsmInfo.imei,
            imei : gsmInfo.imei,
            module : gsmInfo.module,
            status : gsmInfo.status,
            simCard : {
                id : gsmInfo.simCard.id
            }
        }

        var params = $.customParam(gsm);
        
        if (gsmInfo.id == 0) {
            post('/gsmmodule/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else {
            put('/gsmmodule/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/gsmmodule/' + gsmInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
}