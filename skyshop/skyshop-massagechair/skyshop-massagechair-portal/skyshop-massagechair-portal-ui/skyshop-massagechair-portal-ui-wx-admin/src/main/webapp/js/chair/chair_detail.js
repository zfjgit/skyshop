$(function() {
    config.initSessionInfo();
    
    var chairInfo = new MassageChairInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        $('.h_title').text('新增按摩椅');
        $('#btn_delete').hide();
        $('#btn_fault').hide();
        $('#btn_check').hide();
        $('#btn_qrcode').hide();
        $('.t_deletestatus_field').hide();
    } else {
        $('#btn_autocreate').hide();
        chairInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("chairInfo", chairInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (chairInfo.id && chairInfo.id != 0) {
        get('/chair/' + chairInfo.id, '', function(d) {
            chairInfo.id = d.data.id;
            chairInfo.brand = d.data.brand;
            chairInfo.name = d.data.name;
            chairInfo.status = d.data.status;
            chairInfo.gsm = d.data.gsmModule;
            chairInfo.prices = d.data.prices;
            chairInfo.qrcode = d.data.qrcode;
            chairInfo.deleteStatus = d.data.deleteStatus;
            chairInfo.installationAddress = d.data.installationAddress;
            
            config.uku.refresh('chairInfo');
            
            initPricePicker();
            initStatusPicker();
            initAddressPicker();
            initGSMPicker();
            
            showQRCodeBtns();
        });
    } else {
        initStatusPicker();
        initAddressPicker();
        initGSMPicker();
        initPricePicker();
    }
}

function initStatusPicker() {
    var status = [];
    for ( var k in chairInfo.statusPairs) {
        var ele = chairInfo.statusPairs[k];
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
    if (chairInfo.id != 0 && chairInfo.status) {
        $("#status-picker").val(chairInfo.status.name);
    } else {
        $("#status-picker").val(status[0]);
    }
}

function initPricePicker() {
    getList('/fixedprice/', '', function(d) {
        var prices = d.data.datas;
        var priceItems = [];
        
        var priceNames = '';
        if (chairInfo.id != 0 && chairInfo.prices) {
            for (var i = 0; i < chairInfo.prices.length; i++) {
                var p = chairInfo.prices[i];
                if (!p) {
                    continue;
                }
                if (priceNames != '') {
                    priceNames += ',';
                }
                priceNames += p.name;
                
                chairInfo.pricePairs[p.name] = p.id;
            }
        }
        
        for (var i = 0; i < prices.length; i++) {
            var p = prices[i];
            priceItems.push({
                title : p.name,
                value : p.id
            });
            chairInfo.pricePairs[p.name] = p.id;
        }
        
        $("#price-picker").select({
            title : "请选择价格",
            multi : true,
            items : priceItems
        });
        
        $("#price-picker").val(priceNames);
    });
}

function initAddressPicker() {
    getList('/installaddr/?deleteStatus.code=0&agency.id=' + config.agency.id, '', function(d) {
        var addrs = d.data.datas;
        var names = [ '无' ];
        var vals = [ 0 ];
        for (var i = 0; i < addrs.length; i++) {
            var addr = addrs[i];
            names.push(addr.fullAddress);
            chairInfo.addrPairs[addr.fullAddress] = addr.id;
        }
        $("#addr-picker").picker({
            title : "请选择场所",
            cols : [ {
                textAlign : 'center',
                values : names,
            } ]
        });
        
        if (chairInfo.id != 0 && chairInfo.installationAddress) {
            $("#addr-picker").val(chairInfo.installationAddress.fullAddress);
        } else {
            $("#addr-picker").val('无');
        }
    });
}

function initGSMPicker() {
    getList('/gsmmodule/?deleteStatus.code=0&status.code=A', '', function(d) {
        var gsms = d.data.datas;
        var names = [];
        if (chairInfo.id != 0 && chairInfo.gsm) {
            names.push(chairInfo.gsm.module);
            chairInfo.gsmPairs[chairInfo.gsm.module] = chairInfo.gsm.id;
        }
        
        for (var i = 0; i < gsms.length; i++) {
            var gsm = gsms[i];
            names.push(gsm.module);
            chairInfo.gsmPairs[gsm.module] = gsm.id;
        }
        
        $("#gsm-picker").picker({
            title : "请选择模块",
            cols : [ {
                textAlign : 'center',
                values : names
            } ]
        });
        
        if (chairInfo.id != 0 && chairInfo.gsm) {
            $("#gsm-picker").val(chairInfo.gsm.module);
        } else {
            $("#gsm-picker").val(names[0]);
        }
    });
}

function addListeners() {
    $('#btn_fault').click(function() {
        $.prompt({
            title : '报告故障',
            text : '输入故障信息：',
            input : '',
            empty : false, // 是否允许为空
            onOK : function(input) {
                if (input == '') {
                    showError('请输入故障信息');
                    return;
                }
                var mal = new MalfunctionInfo();
                mal.status.code = 'A';
                mal.type.code = 'C';
                mal.chair = {
                    id : chairInfo.id
                };
                mal.description = input;
                
                var params = $.customParam(mal);
                
                post('/malfunction/', params, function(d) {
                    showOkMsg('报障成功', function() {
                        loadOne();
                    });
                });
            },
            onCancel : function() {
                // 点击取消
            }
        });
    });
    
    $('#btn_qrcode').click(function() {
        $("#popup-qrcode").popup();
    });
    
    $('#btn_createqrcode').click(function() {
        post('/chair/createqrcode/' + chairInfo.id, '', function(d) {
            if (d.data.qrcode) {
                showOkMsg('二维码已生成', function() {
                    window.location.reload();
                });
            }
        });
    });
    
    $('#btn_save').on('click', function() {
        if (chairInfo.name == '') {
            showCancelMsg('请输入编号');
            return;
        }
        
        var gsm = $('#gsm-picker').val();
        if (gsm == '') {
            showCancelMsg('请选择GSM模块');
            return;
        }
        
        chairInfo.gsm['name'] = gsm;
        chairInfo.gsm['id'] = chairInfo.gsmPairs[gsm];
        
        var addr = $("#addr-picker").val();
        if (addr != '' && addr != '无') {
            if (!chairInfo.installationAddress) {
                chairInfo.installationAddress = {};
            }
            chairInfo.installationAddress['fullAddress'] = addr;
            chairInfo.installationAddress['id'] = chairInfo.addrPairs[addr];
        }
        
        var prices = $('#price-picker').val();
        if (prices != '' && prices != '无') {
            var priceNames = prices.split(',');
            for (var i = 0; i < priceNames.length; i++) {
                var priceName = priceNames[i];
                
                if (!chairInfo.prices) {
                    chairInfo.prices = [];
                }
                chairInfo.prices[i] = {
                    name : priceName,
                    id : chairInfo.pricePairs[priceName]
                };
            }
        }
        
        var status = $("#status-picker").val();
        if (status != '' && status != '无') {
            if (!chairInfo.status) {
                chairInfo.status = {};
            }
            chairInfo.status['name'] = status;
            chairInfo.status['code'] = chairInfo.statusPairs[status];
        }
        
        var chair = {
            id : chairInfo.id,
            name : chairInfo.name,
            brand : chairInfo.brand,
            status : chairInfo.status,
            gsmModule : {
                id : chairInfo.gsm.id
            },
            installationAddress : {
                id : chairInfo.installationAddress.id
            },
            prices : chairInfo.prices,
            agency : {
                id : config.agency.id
            }
        }

        var params = $.customParam(chair);
        
        if (chairInfo.id == 0) {
            post('/chair/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else {
            put('/chair/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/chair/' + chairInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
    
    /*
    $('#btn_check').on('click', function() {
        get('/chair/check/' + chairInfo.id, '', function(d) {
            showConfirmMsg('正在检测设备，可能需要几分钟才能确认结果，是否继续等待？', function() {
            }, function() {
            });
        });
    });
    */

    $('#btn_autocreate').on('click', function() {
        window.location.href = 'chair_autocreate.html';
    });
    
    $('#btn_download').on('click', function() {
        window.location.href = chairInfo.qrcode;
    });
}

function showQRCodeBtns() {
    if (!chairInfo.qrcode) {
        $('#btn_download').hide();
        $('#btn_createqrcode').show();
    } else {
        $('#btn_download').show();
        $('#btn_createqrcode').hide();
    }
}