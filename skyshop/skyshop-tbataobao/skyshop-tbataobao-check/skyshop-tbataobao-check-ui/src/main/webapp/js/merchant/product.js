$(function(){
	if(!config.initSessionInfo()) {
		return;
	}
	
	var id = window.location.search;
	if(id) {
		id = id.replace('?', '');
	}
	
	initForm();
	initUku(id);
	initUploader();
});

function initForm() {
	$("#form-product").validate({
		ignore: '',
        rules : {
            name : {
                required : true,
                minlength : 4,
            },
            'sysCategory-0': {
            	required : true,
            	min: 1,
            },
            'productCategory-0': {
            	required : true,
            	min: 1,
            },
            price: {
            	required: true,
            	min: 1,
            },
            marketPrice: {
            	required: true,
            	min: 1,
            },
            inventoryCount: {
            	required: true,
            	min: 0,
            },
            'img-0': {
            	required: true,
            },
            introducation: {
            	required: true
            }
        },
        messages : {
            name : {
                required : '<div><span class="red">*</span><span>请输入商品名称</span></div>',
            },
            'sysCategory-0': {
            	required : '<div><span class="red">*</span><span>请选择系统分类</span></div>',
            	min: '<div><span class="red">*</span><span>请选择系统分类</span></div>',
            },
            'productCategory-0': {
            	required : '<div><span class="red">*</span><span>请选择自定义分类</span></div>',
            	min: '<div><span class="red">*</span><span>请选择自定义分类</span></div>',
            },
            price: {
            	required: '<div><span class="red">*</span><span>请输入商品现价</span></div>',
            	min: '<div><span class="red">*</span><span>现价必须大于0</span></div>',
            },
            marketPrice: {
            	required: '<div><span class="red">*</span><span>请输入商品原价</span></div>',
            	min: '<div><span class="red">*</span><span>原价必须大于0</span></div>',
            },
            inventoryCount: {
            	required: '<div><span class="red">*</span><span>请输入商品库存</span></div>',
            	min: '<div><span class="red">*</span><span>库存必须大于0</span></div>',
            },
            'img-0': {
            	required: '<div><span class="red">*</span><span>请上传商品图片</span></div>',
            },
            introducation: {
            	required: '<div><span class="red">*</span><span>请输入商品描述</span></div>',
            }
        },
        onkeyup : false,
        focusCleanup : true,
        success : "valid",
        submitHandler : function(form) {
            save();
        }
    });
}

function save() {
	for (var i = product.sysCategorys.length; i > 0; i--) {
		var c = product.sysCategorys[i - 1];
		if(c.selectedId && c.selectedId != '0') {
			product.sysCategoryId = c.selectedId;
			break;
		}
	}
	for (var i = product.productCategorys.length; i > 0; i--) {
		var c = product.productCategorys[i - 1];
		if(c.selectedId && c.selectedId != '0') {
			product.categoryIdx1 = c.selectedId;
			break;
		}
	}
	
	var intro = product.introducation.replace(/[\r\n\t]/gi, '').replace(/\'/gi, '\"');
	var data = { id: product.id, name: product.name, sysCategoryId: product.sysCategoryId, productCategoryId: product.categoryIdx1, 
				status: product.status.code, price: product.price, marketPrice: product.marketPrice, inventoryCount: product.inventoryCount, 
				img1: product.imgs[0].url, img2: product.imgs[1].url, img3: product.imgs[2].url, img4: product.imgs[3].url, img5: product.imgs[4].url, 
				introducation: intro
			};
	
	var params = $.customParam(data);
	
	put('/merchant/product/', params, function(d){
		showOkMsg('保存成功', function(){
			location.reload();
		});
	});
}

function initUploader() {
	var uploader = WebUploader.create({
		auto: true,
		swf: config.ui_server_url + '/plugins/h-ui/lib/webuploader/0.1.5/Uploader.swf',
	
		// 文件接收服务端。
		server: config.api_server_url + '/merchant/product/imgupload',
	
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#picker',
	
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 只允许选择图片文件。
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	
	uploader.on( 'uploadSuccess', function( file, response ) {
		console.log(file);
		console.log("response=" + response);
		
		if(response.code != config.success_code) {
			uploader.reset();
		} else {
			var path = response.data;
			for (var i = 0; i < product.imgs.length; i++) {
				var img = product.imgs[i];
				if(img.url == '') {
					product.imgs[i].url = config.img_server_url + path;
					config.uku.refresh('product');
					break;
				}
			}
		}
	});
	
	uploader.on( 'uploadError', function(file, reason) {
		console.log(file);
		console.log("reason=" + reason);
		
		uploader.reset();
	});
}

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
	get('/merchant/product/' + product.id, '', function(d){
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
        
        product.sysCategorys = c.sysCateogrys;
        product.productCategorys = c.productCategorys;
        
        product.changeSysCategory = function(e, index) {
        	changeSysCategory(index);
        };
        
        product.changeProductCategory = function(e, index) {
        	changeProductCategory(index);
        };
        
        product.imgs = [];
        product.imgs.push({index: 0, url: c.productImage1 ? (c.productImage1.indexOf('http:') == -1 ? config.img_server_url + c.productImage1 : c.productImage1) : '', showImgMask : function() { return showImgMask(this) } });
    	product.imgs.push({index: 1, url: c.productImage2 ? (c.productImage1.indexOf('http:') == -1 ? config.img_server_url + c.productImage2 : c.productImage2) : '', showImgMask : function() { return showImgMask(this) } });
    	product.imgs.push({index: 2, url: c.productImage3 ? (c.productImage1.indexOf('http:') == -1 ? config.img_server_url + c.productImage3 : c.productImage3) : '', showImgMask : function() { return showImgMask(this) } });
    	product.imgs.push({index: 3, url: c.productImage4 ? (c.productImage1.indexOf('http:') == -1 ? config.img_server_url + c.productImage4 : c.productImage4) : '', showImgMask : function() { return showImgMask(this) } });
    	product.imgs.push({index: 4, url: c.productImage5 ? (c.productImage1.indexOf('http:') == -1 ? config.img_server_url + c.productImage5 : c.productImage5) : '', showImgMask : function() { return showImgMask(this) } });
    	
    	product.deleteImg = function(e, index) {
    		$($('.p-img')[index]).attr('src', '');
    		$($('.p-img-mask')[index]).hide();
    		product.imgs[index].url = '';
    	};
        
        product.showIntroducation = function() {
        	product.introducation = product.introducation.replace(/"\/uploads/gi, '"' + config.img_server_url + '/uploads');
        	$('#introducation').val(product.introducation);
        	editor.html(product.introducation);
        	return product.introducation;
        };
        
        product.showRemarkInfo = function() {
        	showRemarkInfo();
        };
        
        product.cancel = function() {
        	closeLayer();
        };
		
		config.uku.refresh('product');
	});
}

function showImgMask(_this) {
	 if(!_this.url) { 
		 $($('.p-img-mask')[_this.index]).hide();
	 } 
	 return '删除';
}

function changeSysCategory(index) {
	if(index == product.sysCategorys.length - 1) {
		return;
	}
	var parentId = product.sysCategorys[index].selectedId;
	if(!parentId || parentId == 0) {
		for (var i = index + 1; i < product.sysCategorys.length; i ++) {
			product.sysCategorys[i].options = [{id: 0, name: '请选择'}];
			product.sysCategorys[i].selectedId = 0;
		}
		config.uku.refresh('product');
	} else {
		get('/syscategory/childrens/' + parentId, '', function(d) {
			var data = d.data;
			data.unshift({id: 0, name: '请选择'});
			if(index < product.sysCategorys.length - 1) {
				index ++;
				product.sysCategorys[index].options = data;
				product.sysCategorys[index].selectedId = 0;
				config.uku.refresh('product');
				
				changeSysCategory(index);
			}
		}, true);
	}
}

function changeProductCategory(index) {
	if(index == product.productCategorys.length - 1) {
		return;
	}
	
	var parentId = product.productCategorys[index].selectedId;
	if(!parentId || parentId == 0) {
		for (var i = index + 1; i < product.productCategorys.length; i ++) {
			product.productCategorys[i].options = [{id: 0, name: '请选择'}];
			product.productCategorys[i].selectedId = 0;
		}
		config.uku.refresh('product');
	} else {
		get('/productcategory/childrens/' + parentId, '', function(d) {
			var data = d.data;
			data.unshift({id: 0, name: '请选择'});
			if(index < product.productCategorys.length - 1) {
				index ++;
				product.productCategorys[index].options = data;
				product.productCategorys[index].selectedId = 0;
				config.uku.refresh('product');
				
				changeProductCategory(index);
			}
		}, true);
	}
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
		$('.div-checkRemarkInfo').hide();
	}
}



function closeLayer() {
	if(sessionStorage.productLayerIndex) {
		parent.layer.close(sessionStorage.productLayerIndex);
		return;
	}
	parent.layer.closeAll();
}
