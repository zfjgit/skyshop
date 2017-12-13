$(function() {
    config.initSessionInfo();
    
    var priceInfo = new PriceInfo();
    
    var search = window.location.search;
    if (!search || search == '?') {
        $('.h_title').text('新增价格');
        $('#btn_delete').hide();
    } else {
        priceInfo.id = search.substring(1);
    }
    
    var uku = new Ukulele();
    uku.registerController("priceInfo", priceInfo);
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        loadOne();
        
        addListeners();
    });
    uku.init();
});

function loadOne() {
    if (priceInfo.id && priceInfo.id != 0) {
        get('/price/' + priceInfo.id, '', function(d) {
            var c = d.data;
            priceInfo.id = c.id;
            priceInfo.name = c.name;
            priceInfo.minutes = c.minutes;
            priceInfo.money = c.price;
            priceInfo.img = c.img;
            
            config.uku.refresh('priceInfo');
        });
    }
}

function addListeners() {
    $('#btn_save').on('click', function() {
        if (!priceInfo.name) {
            showMsg('请输入名称');
            return;
        }
        if (!priceInfo.money || priceInfo.money <= 0) {
            showMsg('请输入正确的金额');
            return;
        }
        if (!priceInfo.minutes || priceInfo.minutes <= 0) {
            showMsg('请输入正确的时长');
            return;
        }
        
        var img = '';
        var price = {
            id : priceInfo.id,
            name : priceInfo.name,
            price : priceInfo.money,
            minutes : priceInfo.minutes,
            img : priceInfo.img,
            agency : {
                id : config.agency.id
            }
        }

        var params = $.customParam(price);
        
        if (priceInfo.id == 0) {
            post('/fixedprice/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        } else {
            put('/fixedprice/', params, function(d) {
                showConfirmMsg('保存成功，是否留在此页面？', function() {
                }, function() {
                    window.history.back();
                });
            });
        }
    });
    
    $('#btn_delete').on('click', function() {
        showConfirmMsg('是否确定要删除此信息？', function() {
            del('/price/' + priceInfo.id, '', function(d) {
                showOkMsg('删除成功', function() {
                    window.history.back();
                });
            });
        });
    });
    
}