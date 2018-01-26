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
	var product = new ProductInfo();
	product.id = id;
	
	var uku = new Ukulele();
    uku.registerController("product", product);
    
    uku.addListener(Ukulele.INITIALIZED, function(e) {
        config.uku = uku;
        getOne();
    });
    
    uku.init();
}

function getOne() {
	get('/product/' + product.id, '', function(d){
		var c = d.data;
        product.name = c.productName;
        product.catagory = { id: c.categoryIdx1, name: c.categoryName };
        product.shop = {id: c.shopIdx, name: c.shopName};
        product.createTime = c.makeTime;
        
        product.status = ProductStatus.onsale;
        if(c.productStatus == ProductStatus.OFFSALE_CODE) {
        	product.status = ProductStatus.offsale;
        }
        product.checkStatus = CheckStatus.unchecked;
        if(c.ischecked == CheckStatus.SUCCESSED_CODE) {
        	product.checkStatus = CheckStatus.successed;
        } else if(c.ischecked == CheckStatus.FAILED_CODE) {
        	product.checkStatus = CheckStatus.failed;
        }
        product.sysCatagory = {id: c.sysCatagoryId, name : c.sysCatagoryName};
        product.introducation = c.introducation;
        
        product.price = parseFloat(c.productPrice).toFixed(2);
        product.marketPrice = parseFloat(c.marketPrice).toFixed(2);
        
        product.checkRemark = c.checkRemarkInfo;
        
        product.imgs = [];
        if(c.productImage1) {
        	product.imgs.push(config.img_server_url + c.productImage1);
        }
        if(c.productImage2) {
        	product.imgs.push(config.img_server_url + c.productImage2);
        }
        if(c.productImage3) {
        	product.imgs.push(config.img_server_url + c.productImage3);
        }
        if(c.productImage4) {
        	product.imgs.push(config.img_server_url + c.productImage4);
        }
        if(c.productImage5) {
        	product.imgs.push(config.img_server_url + c.productImage5);
        }
        
        product.showIntroducation = function() {
        	product.introducation = product.introducation.replace(/"\/uploads/gi, '"' + config.img_server_url + '/uploads');
        	$('#introducation').html(product.introducation);
        };
        
        product.showRemarkInfo = function() {
        	showRemarkInfo();
        };
        
        product.checkSuccess = function() {
        	checkSuccess(product.id);
        };
        
        product.checkFail = function() {
        	checkFail(product.id);
        };
        
        product.checkCancel = function() {
        	closeLayer();
        };
		
		config.uku.refresh('product');
	});
}

function showRemarkInfo() {
	if(product.checkStatus.code == CheckStatus.SUCCESSED_CODE) {
		$('#div-checkAlert').addClass('Huialert-success');
		$('.checkStatus-text').text(product.checkStatus.name);
		$('.div-checkRemarkInfo').hide();
	} else if(product.checkStatus.code == CheckStatus.FAILED_CODE) {
		$('#div-checkAlert').addClass('Huialert-danger');
		$('.checkStatus-text').text(product.checkStatus.name);
		if(product.checkRemark) {
			$('.checkRemarkInfo-text').text(product.checkRemark.description);
			$('.checkRemarkTime-text').text(product.checkRemark.createTime);
		} else {
			$('.div-checkRemarkInfo').hide();
		}
	} else {
		$('#div-checkAlert').addClass('Huialert-warning');
		$('.checkStatus-text').text(product.checkStatus.name);
		if(product.checkRemark) {
			$('.checkRemarkInfo-text').text(product.checkRemark.description);
			$('.checkRemarkTime-text').text(product.checkRemark.createTime);
		} else {
			$('.div-checkRemarkInfo').hide();
		}
	}
}

function checkSuccess(id) {
	var productInfo = {id: product.id, checkStatus: {code: CheckStatus.SUCCESSED_CODE, name: ''}};
	var params = $.customParam(productInfo);
	put('/product/', params, function(d){
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
		var productInfo = {id: id, checkStatus: {code: CheckStatus.FAILED_CODE, name: ''}, remarkInfo: {description: value}};
		var params = $.customParam(productInfo);
		put('/product/', params, function(d){
			layer.close(idx);
			showOkMsg('操作成功', function(){
				parent.location.reload();
			});
		});
	});
}

function closeLayer() {
	if(sessionStorage.productLayerIndex) {
		parent.layer.close(sessionStorage.productLayerIndex);
		return;
	}
	parent.layer.closeAll();
}
