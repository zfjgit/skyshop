$(function() {
    config.initSessionInfo();
    
    var simInfo = new SIMCardInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        $('.h_title').text('新增SIM卡');
        $('#btn_delete').hide();
        $('.t_deletestatus_field').hide();
    } else {
        simInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("simInfo", simInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (simInfo.id && simInfo.id != 0) {
        get('/simcard/' + simInfo.id, '', function(d) {
            simInfo.id = d.data.id;
            simInfo.sim = d.data.sim;
            simInfo.name = d.data.name;
            simInfo.status = d.data.status;
            simInfo.dueDate = d.data.dueDate;
            simInfo.dataFlow = d.data.dataFlow;
            simInfo.operator = d.data.operator;
            simInfo.deleteStatus = d.data.deleteStatus;
            
            config.uku.refresh('simInfo');
            
            initDueDatePicker();
            initStatusPicker();
            initOperatorPicker();
        });
    } else {
        initDueDatePicker();
        initStatusPicker();
        initOperatorPicker();
    }
}

function initDueDatePicker() {
    $("#date-picker").calendar({
        'dateFormat' : 'yyyy-mm-dd'
    });
    
    if (simInfo.id != 0 && simInfo.dueDate) {
        $("#date-picker").val(simInfo.dueDate);
    }
}

function initStatusPicker() {
    var status = [];
    for ( var k in simInfo.statusPairs) {
        var ele = simInfo.statusPairs[k];
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
    
    if (simInfo.id != 0 && simInfo.status) {
        $("#status-picker").val(simInfo.status.name);
    } else {
        $("#status-picker").val(status[0]);
    }
}
function initOperatorPicker() {
    var operators = [];
    for ( var k in simInfo.operatorPairs) {
        var ele = simInfo.operatorPairs[k];
        if (k && (typeof k) == 'string' && ele && (typeof ele) == 'string') {
            operators.push(k);
        }
    }
    
    $("#operator-picker").picker({
        title : "请选择运营商",
        cols : [ {
            textAlign : 'center',
            values : operators
        } ]
    });
    
    if (simInfo.id != 0 && simInfo.operator) {
        $("#operator-picker").val(simInfo.operator.name);
    } else {
        $("#operator-picker").val(operators[0]);
    }
}

function addListeners() {
    $('#btn_save').on('click', function() {
        if (simInfo.sim == '') {
            showMsg('请输入SIM卡号');
            return;
        }
        
        var dueDate = $('#sim-picker').val();
        if (dueDate == '') {
            showMsg('请选择到期日');
            return;
        }
        
        var operator = $('#operator-picker').val();
        
        simInfo.operator['name'] = operator;
        simInfo.operator['code'] = simInfo.operatorPairs[operator];
        
        var status = $("#status-picker").val();
        if (status != '' && status != '无') {
            if (!simInfo.status) {
                simInfo.status = {};
            }
            simInfo.status['name'] = status;
            simInfo.status['code'] = simInfo.statusPairs[status];
        }
        
        var simCard = {
            id : simInfo.id,
            name : simInfo.sim,
            sim : simInfo.sim,
            dataFlow : simInfo.dataFlow,
            dueDate : simInfo.dueDate,
            operator : simInfo.operator,
            status : simInfo.status
        }

        var params = $.customParam(simCard);
        
        if (simInfo.id == 0) {
            post('/simcard/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else {
            put('/simcard/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/simcard/' + simInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
}