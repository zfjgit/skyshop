$(function() {
    config.initSessionInfo();
    
    var uku = new Ukulele();
    var withraw = new WithrawInfo();
    uku.registerController("withraw", withraw);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    get('/agency/' + config.agency.id, '', function(d) {
        withraw.agency = {
            id : d.data.id,
            name : d.data.name,
            balance : d.data.balance
        };
        
        loadBank();
    });
}

function loadBank() {
    var data = {
        agency : {
            id : config.agency.id
        }
    };
    var param = $.customParam(data);
    
    getList('/agencybank/', param, function(d) {
        var banks = d.data.datas;
        if (banks && banks.length > 0) {
            withraw.bank = banks[0];
        }
        
        config.uku.refresh("withraw");
    });
}

function addListeners() {
    
    $('#btn_ok').on('click', function() {
        if (!withraw.bank || !withraw.bank.bank) {
            showCancelMsg('您需要先填写银行卡信息');
            return;
        }
        if (!withraw.money || withraw.money <= 0) {
            showCancelMsg('请输入正确的金额');
            return;
        }
        if (parseFloat(withraw.money) > withraw.agency.balance) {
            showCancelMsg('提现金额必须小于余额');
            return;
        }
        
        var data = {
            agency : {
                id : withraw.agency.id
            },
            money : withraw.money,
            bank : withraw.bank.bank,
            account : withraw.bank.account,
            accountName : withraw.bank.accountName,
            status : {
                code : 'A'
            }
        };
        var params = $.customParam(data);
        
        post('/agencywithraw/', params, function(d) {
            showConfirmMsg('提现成功，是否留在此页面？', function() {
                window.location.reload();
            }, function() {
                window.history.back();
            });
        });
    });
}