$(function() {
    config.initSessionInfo();
    
    var bank = new BankAccountInfo();
    var search = window.location.search;
    if (!search || search == '?') {
    } else {
        bank.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("bank", bank);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    var data = {
        agency : {
            id : config.agency.id
        }
    };
    var param = $.customParam(data);
    
    getList('/agencybank/', param, function(d) {
        var banks = d.data.datas;
        if (banks && banks.length > 0) {
            bank.id = banks[0].id;
            bank.bank = banks[0].bank;
            bank.account = banks[0].account;
            bank.accountName = banks[0].accountName;
        }
        
        config.uku.refresh("bank");
    });
}

function addListeners() {
    
    $('#btn_ok').on('click', function() {
        if (!bank.bank) {
            showCancelMsg('请输入银行名称');
            return;
        }
        if (!bank.account) {
            showCancelMsg('请输入银行卡号');
            return;
        }
        if (!/^\d{10, 20}$/gi.test(bank.account)) {
            showCancelMsg('请输入正确的银行卡号');
            return;
        }
        if (!bank.accountName) {
            showCancelMsg('请输入银行账户户名');
            return;
        }
        
        bank.agency = {
            id : config.agency.id
        };
        
        var params = $.customParam(bank);
        
        if (bank.id) {
            put('/agencybank/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                    window.location.reload();
                }, function() {
                    window.history.back();
                });
            });
        } else {
            post('/agencybank/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                    window.location.reload();
                }, function() {
                    window.history.back();
                });
            });
        }
    });
}