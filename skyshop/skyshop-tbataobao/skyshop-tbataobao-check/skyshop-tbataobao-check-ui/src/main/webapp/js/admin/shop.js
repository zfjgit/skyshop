$(function(){
	if(!config.initSessionInfo()) {
		return;
	}
	
	var id = window.location.search;
	if(id) {
		id = id.replace('?', '');
	}
	
	initUku(id);
});

function initUku(id) {
	var shop = new ShopInfo();
	shop.id = id;
	
	var uku = new Ukulele();
    uku.registerController("shop", shop);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        getOne();
    });
    
    uku.init();
}

function getOne() {
	get('/shop/' + shop.id, '', function(d){
		var c = d.data;
        shop.name = c.name;
        shop.phone = c.phone;
        shop.address = (c.province ? c.province : '') + (c.city ? c.city : '') + (c.district ? c.district : '') + (c.userAddress ? c.userAddress : '');
        shop.industry = c.industry;
        shop.createTime = c.createTime;
        shop.intro = c.remark;
        shop.logo = c.logo;
        
        shop.status = ShopStatus.on;
        if(c.status == ShopStatus.OFF_CODE) {
        	shop.status = ShopStatus.off;
        }
        shop.checkStatus = CheckStatus.unchecked;
        if(c.ischecked == CheckStatus.SUCCESSED_CODE) {
        	shop.checkStatus = CheckStatus.successed;
        } else if(c.ischecked == CheckStatus.FAILED_CODE) {
        	shop.checkStatus = CheckStatus.failed;
        }
        
        shop.checkRemark = c.checkRemarkInfo;
        
        shop.banners = [];
        if(c.banner1) {
        	shop.banners.push(config.img_server_url + c.banner1);
        }
        if(c.banner2) {
        	shop.banners.push(config.img_server_url + c.banner2);
        }
        if(c.banner3) {
        	shop.banners.push(config.img_server_url + c.banner3);
        }
        
        shop.showIntroducation = function() {
        	shop.intro = shop.intro.replace(/"\/uploads/gi, '"' + config.img_server_url + '/uploads');
        	$('#introducation').html(shop.intro);
        };
        
        shop.showRemarkInfo = function() {
        	showRemarkInfo();
        };
        
        shop.checkSuccess = function() {
        	checkSuccess(shop.id);
        };
        
        shop.checkFail = function() {
        	checkFail(shop.id);
        };
        
        shop.checkCancel = function() {
        	closeLayer();
        };
		
		config.uku.refresh('shop');
	});
}

function showRemarkInfo() {
	if(shop.checkStatus.code == CheckStatus.SUCCESSED_CODE) {
		$('#div-checkAlert').addClass('Huialert-success');
		$('.checkStatus-text').text(shop.checkStatus.name);
		$('.div-checkRemarkInfo').hide();
	} else if(shop.checkStatus.code == CheckStatus.FAILED_CODE) {
		$('#div-checkAlert').addClass('Huialert-danger');
		$('.checkStatus-text').text(shop.checkStatus.name);
		if(shop.checkRemark) {
			$('.checkRemarkInfo-text').text(shop.checkRemark.description);
			$('.checkRemarkTime-text').text(shop.checkRemark.createTime);
		} else {
			$('.div-checkRemarkInfo').hide();
		}
	} else {
		$('#div-checkAlert').addClass('Huialert-warning');
		$('.checkStatus-text').text(shop.checkStatus.name);
		if(shop.checkRemark) {
			$('.checkRemarkInfo-text').text(shop.checkRemark.description);
			$('.checkRemarkTime-text').text(shop.checkRemark.createTime);
		} else {
			$('.div-checkRemarkInfo').hide();
		}
	}
}

function checkSuccess(id) {
	var shopInfo = {id: shop.id, checkStatus: {code: CheckStatus.SUCCESSED_CODE, name: ''}};
	var params = $.customParam(shopInfo);
	put('/shop/', params, function(d){
		showOkMsg('操作成功', function(){
			parent.location.reload();
		});
	});
}

function checkFail(id) {
	var idx = layer.prompt({
		formType: 2,
		value: '',
		title: '审核不通过，请输入原因：',
	}, function(value, index, elem){
		var shopInfo = {id: id, checkStatus: {code: CheckStatus.FAILED_CODE, name: ''}, remarkInfo: {description: value}};
		var params = $.customParam(shopInfo);
		put('/shop/', params, function(d){
			layer.close(idx);
			showOkMsg('操作成功', function(){
				parent.location.reload();
			});
		});
	});
}

function closeLayer() {
	if(sessionStorage.shopLayerIndex) {
		parent.layer.close(sessionStorage.shopLayerIndex);
		return;
	}
	parent.layer.closeAll();
}
