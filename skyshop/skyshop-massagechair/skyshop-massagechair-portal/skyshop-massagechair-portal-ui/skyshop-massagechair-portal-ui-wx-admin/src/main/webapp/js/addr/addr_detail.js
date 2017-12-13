$(function() {
    config.initSessionInfo();
    
    var addrInfo = new InstallAddressInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        $('.h_title').text('新增场所');
        $('#btn_delete').hide();
        $('#btn_income').hide();
        $('.t_deletestatus_field').hide();
    } else {
        addrInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("addrInfo", addrInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (addrInfo.id && addrInfo.id != 0) {
        get('/installaddr/' + addrInfo.id, '', function(d) {
            var c = d.data;
            addrInfo.id = c.id;
            addrInfo.fullAddress = c.fullAddress;
            addrInfo.contact = c.contact;
            addrInfo.contactNumber = c.contactNumber;
            addrInfo.status = c.status;
            addrInfo.agency = c.agency;
            addrInfo.province = c.province;
            addrInfo.city = c.city;
            addrInfo.district = c.district;
            addrInfo.chairs = c.chairs;
            addrInfo.detailAddress = c.detailAddress;
            addrInfo.description = c.description;
            addrInfo.deleteStatus = c.deleteStatus;
            
            config.uku.refresh('addrInfo');
            
            initAddressPicker();
            initChairsPicker();
        });
    } else {
        initAddressPicker();
        initChairsPicker();
    }
}

function initAddressPicker() {
    $("#city-picker").cityPicker({
        title : "选择地址",
        onChange : function(picker, values, displayValues) {
            addrInfo.province = {
                code : values[0]
            };
            addrInfo.city = {
                code : values[1]
            };
            addrInfo.district = {
                code : values[2]
            };
        }
    });
    if (addrInfo.id != 0) {
        $("#city-picker").val(addrInfo.province.name + ' ' + addrInfo.city.name + ' ' + addrInfo.district.name);
    }
}

function initChairsPicker() {
    getList('/chair/?status.code=A&deleteStatus.code=0', '', function(d) {
        var chairs = d.data.datas;
        var chairItems = [];
        
        var chairNames = '';
        if (addrInfo.id != 0 && addrInfo.chairs) {
            for (var i = 0; i < addrInfo.chairs.length; i++) {
                var p = addrInfo.chairs[i];
                if (!p) {
                    continue;
                }
                if (chairNames != '') {
                    chairNames += ',';
                }
                chairNames += p.name;
                
                chairItems.push({
                    title : p.name,
                    value : p.id
                });
                addrInfo.chairPairs[p.name] = p.id;
            }
        }
        for (var i = 0; i < chairs.length; i++) {
            var p = chairs[i];
            chairItems.push({
                title : p.name,
                value : p.id
            });
            addrInfo.chairPairs[p.name] = p.id;
        }
        
        $("#chair-picker").select({
            title : "请选择按摩椅",
            multi : true,
            items : chairItems
        });
        
        $('#chair-picker').val(chairNames);
    });
}

function addListeners() {
    $('#btn_save').on('click', function() {
        if (!addrInfo.province || addrInfo.province.code == 0 || !addrInfo.city || addrInfo.city.code == 0 || !addrInfo.district || addrInfo.district.code == 0) {
            showMsg('请选择地址');
            return;
        }
        
        if (!addrInfo.detailAddress) {
            showMsg('请输入详细地址');
            return;
        }
        if (!addrInfo.contact) {
            showMsg('请输入联系人');
            return;
        }
        if (!addrInfo.contactNumber) {
            showMsg('请输入联系人电话');
            return;
        }
        
        var chairs = $('#chair-picker').val();
        if (chairs != '' && chairs != '无') {
            var chairNames = chairs.split(',');
            for (var i = 0; i < chairNames.length; i++) {
                var chairName = chairNames[i];
                
                if (!addrInfo.chairs) {
                    addrInfo.chairs = [];
                }
                addrInfo.chairs[i] = {
                    name : chairName,
                    id : addrInfo.chairPairs[chairName]
                };
            }
        }
        
        var addr = {
            id : addrInfo.id,
            detailAddress : addrInfo.detailAddress,
            contact : addrInfo.contact,
            contactNumber : addrInfo.contactNumber,
            province : addrInfo.province,
            city : addrInfo.city,
            district : addrInfo.district,
            chairs : addrInfo.chairs,
            agency : {
                id : config.agency.id
            }
        }

        var params = $.customParam(addr);
        
        if (addrInfo.id == 0) {
            post('/installaddr/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else {
            put('/installaddr/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/installaddr/' + addrInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
    
    $('#btn_income').on('click', function() {
        window.location.href = addrInfo.incomeUrl + addrInfo.id;
    });
}