$(function() {
    config.initSessionInfo();
    
    var agencyInfo = new AgencyInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        agencyInfo.id = -1;
        $('.h_title').text('新增下级代理商');
        $('.t_deletestatus_field').hide();
        $('.t_balance_field').hide();
        $('#btn_delete').hide();
    } else {
        agencyInfo.id = search.substring(1);
        if (agencyInfo.id == 0) {
            $('#btn_save').hide();
            $('#btn_delete').hide();
            
            $(':input').prop('readonly', true);
        }
    }
    
    var uku = new Ukulele();
    uku.registerController("agencyInfo", agencyInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (agencyInfo.id == 0) {
        agencyInfo.parent = agencyInfo.root;
        agencyInfo.balance = 0;
        agencyInfo.percentage = 0;
        agencyInfo.parentPercentage = 0;
        agencyInfo.name = '平台/公司';
        agencyInfo.level = {
            code : 'A',
            name : '平台/公司'
        };
        agencyInfo.deleteStatus = {
            code : 0,
            name : '未删除'
        };
        initLevelPicker();
        initParentPicker();
        
        config.uku.refresh('agencyInfo');
    } else if (agencyInfo.id != -1) {
        get('/agency/' + agencyInfo.id, '', function(d) {
            agencyInfo.id = d.data.id;
            agencyInfo.level = d.data.level;
            agencyInfo.name = d.data.name;
            agencyInfo.parent = d.data.parent;
            if (!d.data.parent) {
                agencyInfo.parent = agencyInfo.root;
            }
            agencyInfo.balance = d.data.balance;
            agencyInfo.deleteStatus = d.data.deleteStatus;
            agencyInfo.percentage = d.data.orderIncomePercentage;
            
            initLevelPicker();
            initParentPicker();
            
            config.uku.refresh('agencyInfo');
        });
    } else {
        initLevelPicker();
        initParentPicker();
    }
}

function initParentPicker() {
    var names = [];
    getList('/agency/', '', function(d) {
        var rootName = agencyInfo.root.name;
        names.push(rootName);
        agencyInfo.parentPairs[rootName] = 0;
        for (var i = 0; i < d.data.datas.length; i++) {
            var p = d.data.datas[i];
            var parentName = p['name'];
            names.push(parentName);
            
            agencyInfo.parentPairs[parentName] = p.id;
        }
        $("#parent-picker").picker({
            title : "请选择上级代理商",
            cols : [ {
                textAlign : 'center',
                values : names
            } ]
        });
        if (agencyInfo.id != -1) {
            $("#parent-picker").val(agencyInfo.parent.name);
        }
    });
}

function initLevelPicker() {
    var items = [];
    for ( var k in agencyInfo.levelPairs) {
        if (k && (typeof k == 'string') && agencyInfo.levelPairs[k] && (typeof agencyInfo.levelPairs[k]) == 'string') {
            items.push(k);
        }
    }
    
    $("#lv-picker").picker({
        title : "请选择代理商级别",
        cols : [ {
            textAlign : 'center',
            values : items
        } ]
    });
    
    if (agencyInfo.id != -1) {
        $("#lv-picker").val(agencyInfo.level.name);
    }
}

function addListeners() {
    $('#btn_save').on('click', function() {
        if (agencyInfo.name == '') {
            showCancelMsg('请输入名称');
            return;
        }
        if (agencyInfo.percentage == '') {
            showCancelMsg('请输入分成比例');
            return;
        }
        var lvName = $('#lv-picker').val();
        if (lvName == '') {
            showCancelMsg('请选择代理商级别');
            return;
        }
        var parentName = $('#parent-picker').val();
        if (parentName == '') {
            showCancelMsg('请选择上级代理商');
            return;
        }
        
        var lv = agencyInfo.levelPairs[lvName];
        var parentId = agencyInfo.parentPairs[parentName];
        
        if (!agencyInfo.deleteStatus || agencyInfo.deleteStatus['code'] == '') {
            agencyInfo.deleteStatus = {
                code : 0
            };
        }
        
        var agency = {
            id : agencyInfo.id,
            name : agencyInfo.name,
            balance : 0,
            level : {
                code : lv,
                name : lvName
            },
            parent : {
                id : parentId,
                name : parentName
            },
            deleteStatus : agencyInfo.deleteStatus,
            orderIncomePercentage : agencyInfo.percentage
        };
        
        var params = $.customParam(agency);
        
        if (agencyInfo.id == -1) {
            post('/agency/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else if (agencyInfo.id && agencyInfo.id != 0) {
            put('/agency/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/agency/' + agencyInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
}