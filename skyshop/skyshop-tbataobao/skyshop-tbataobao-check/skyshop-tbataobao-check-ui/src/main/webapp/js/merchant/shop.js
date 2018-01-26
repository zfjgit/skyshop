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
	initLogoUploader();
	initBannerUploader();
});

function initForm() {
	$("#form-shop").validate({
		ignore: '',
        rules : {
            name : {
                required : true,
                minlength : 4,
            },
            industry: {
            	required : true,
            	min: 1,
            },
            phone: {
            	required : true,
            },
            province: {
            	required: true,
            	min: 1,
            },
            city: {
            	required: true,
            	min: 1,
            },
            detailAddress: {
            	required: true,
            },
            logo: {
            	required: true,
            },
            introducation: {
            	required: true
            }
        },
        messages : {
            name : {
                required : '<div><span class="red">*</span><span>请输入店铺名称</span></div>',
            },
            industry: {
            	required : '<div><span class="red">*</span><span>请选择所属行业</span></div>',
            	min: '<div><span class="red">*</span><span>请选择所属行业</span></div>',
            },
            phone: {
            	required : '<div><span class="red">*</span><span>请输入联系电话</span></div>',
            },
            province: {
            	required: '<div><span class="red">*</span><span>请选择所在省</span></div>',
            	min: '<div><span class="red">*</span><span>请选择所在省</span></div>',
            },
            city: {
            	required: '<div><span class="red">*</span><span>请选择所在城市</span></div>',
            	min: '<div><span class="red">*</span><span>请选择所在城市</span></div>',
            },
            detailAddress: {
            	required: '<div><span class="red">*</span><span>请输入详细地址</span></div>',
            },
            logo: {
            	required: '<div><span class="red">*</span><span>请上传LOGO</span></div>',
            },
            introducation: {
            	required: '<div><span class="red">*</span><span>请输入店铺描述</span></div>',
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
	var data = {id:shop.id, name:shop.name, phone:shop.phone, industry:shop.industry.id, 
			province:shop.province.id, city:shop.city.id, district:shop.district.id, detailAddress:shop.detailAddress,
			status:shop.status.code, logo:shop.logo, introducation:shop.introducation, 
			banner1:shop.banners[0].url, banner2:shop.banners[1].url, banner3:shop.banners[2].url
			};
	var params = $.customParam(data);
	
	put('/merchant/shop/', params, function(d){
		showOkMsg('保存成功', function(){
			location.reload();
		});
	});
}

function initLogoUploader() {
	var logoUploader = WebUploader.create({
		auto: true,
		swf: config.ui_server_url + '/plugins/h-ui/lib/webuploader/0.1.5/Uploader.swf',
	
		// 文件接收服务端。
		server: config.api_server_url + '/merchant/shop/logoupload',
	
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: '#pickerlogo',
	
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 只允许选择图片文件。
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	
	logoUploader.on( 'uploadSuccess', function( file, response ) {
		console.log(file);
		console.log("response=" + response);
		
		if(response.code != config.success_code) {
			logoUploader.reset();
		} else {
			var path = response.data;
			shop.logo = config.img_server_url + path;
			config.uku.refresh('shop');
		}
	});
	
	logoUploader.on( 'uploadError', function(file, reason) {
		console.log(file);
		console.log("reason=" + reason);
		
		logoUploader.reset();
	});
}

function initBannerUploader() {
	var bannerUploader = WebUploader.create({
		auto: true,
		swf: config.ui_server_url + '/plugins/h-ui/lib/webuploader/0.1.5/Uploader.swf',
	
		// 文件接收服务端。
		server: config.api_server_url + '/merchant/shop/bannerupload',
	
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
	
	bannerUploader.on( 'uploadSuccess', function( file, response ) {
		console.log(file);
		console.log("response=" + response);
		
		if(response.code != config.success_code) {
			bannerUploader.reset();
		} else {
			var path = response.data;
			for (var i = 0; i < shop.banners.length; i++) {
				var img = shop.banners[i];
				if(img.url == '') {
					shop.banners[i].url = config.img_server_url + path;
					config.uku.refresh('shop');
					break;
				}
				if(i == shop.banners.length -1) {
					shop.banners[i].url = config.img_server_url + path;
					config.uku.refresh('shop');
					break;
				}
			}
		}
	});
	
	bannerUploader.on( 'uploadError', function(file, reason) {
		console.log(file);
		console.log("reason=" + reason);
		
		bannerUploader.reset();
	});
}

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
	get('/merchant/shop/' + shop.id, '', function(d){
		var c = d.data;
        shop.name = c.name;
        shop.phone = c.phone;
        shop.detailAddress = c.userAddress;
        shop.industry = {id: c.industryId, name: c.industryName};
        shop.createTime = c.createTime;
        shop.introducation = c.remark;
        shop.logo = c.logo ? (c.logo.indexOf('http:') == -1 ? config.img_server_url + c.logo : c.logo) : '';
        
        shop.province = {id: c.provinceid, name: c.province};
        shop.city = {id: c.cityid, name: c.city};
        shop.district = {id: c.districtid, name: c.district};

        shop.industrys = c.industrys;
        
        shop.citys = c.citys;
        shop.provinces = c.provinces;
        shop.districts = c.districts;
        
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
        
        shop.changeProvince = function() {
        	changeProvince();
        };
        
        shop.changeCity = function() {
        	changeCity();
        };
        
        shop.checkRemark = c.checkRemarkInfo;
        
        shop.showLogoMask = function() {
        	if(!shop.logo) { 
        		$('.div-s-logo').find('.p-img-mask').hide();
	       	} else {
	       		$('.div-s-logo').find('.p-img-mask').show();
	       	}
	       	return '删除';
        };
        shop.deleteLogo = function() {
        	$('.s-logo').attr('src', '');
    		$('.div-s-logo').find('.p-img-mask').hide();
    		shop.logo = '';
        };
        
        shop.banners = [];
    	shop.banners.push({ index: 0, url: c.banner1 ? (c.banner1.indexOf('http:') == -1 ? config.img_server_url + c.banner1 : c.banner1) : '', showImgMask : function() { return showImgMask(this) }});
    	shop.banners.push({ index: 1, url: c.banner2 ? (c.banner1.indexOf('http:') == -1 ? config.img_server_url + c.banner2 : c.banner2) : '', showImgMask : function() { return showImgMask(this) }});
    	shop.banners.push({ index: 2, url: c.banner3 ? (c.banner1.indexOf('http:') == -1 ? config.img_server_url + c.banner3 : c.banner3) : '', showImgMask : function() { return showImgMask(this) }});
        
    	shop.deleteImg = function(e, index) {
    		$($('.p-img')[index]).attr('src', '');
    		$($('.div-s-banner')[index]).find('.p-img-mask').hide();
    		shop.banners[index].url = '';
    	};
    	
        shop.showIntroducation = function() {
        	return shop.introducation;
        };
        
        shop.showRemarkInfo = function() {
        	showRemarkInfo();
        };
        
        shop.cancel = function() {
        	closeLayer();
        };
		
		config.uku.refresh('shop');
	});
}

function changeProvince() {
	shop.districts = [{id:0, name:'请选择'}];
	shop.district = {id: 0, name: ''};
	if(!shop.province.id || shop.province.id == '0') {
		shop.citys = [{id:0, name:'请选择'}];
		shop.city = {id: 0, name: ''};
		config.uku.refresh('shop');
	} else {
		get('/shopaddress/childrens/' + shop.province.id, '', function(d){
			shop.citys = d.data;
			shop.city = {id: 0, name: ''};
			config.uku.refresh('shop');
		}, false);
	}
}

function changeCity() {
	if(!shop.city.id || shop.city.id == '0') {
		shop.districts = [{id:0, name:'请选择'}];
		shop.district = {id: 0, name: ''};
		config.uku.refresh('shop');
	} else {
		get('/shopaddress/childrens/' + shop.city.id, '', function(d){
			shop.districts = d.data;
			shop.district = {id: 0, name: ''};
			config.uku.refresh('shop');
		}, false);
	}
}

function showImgMask(_this) {
	 if(!_this.url) { 
		 $($('.div-s-banner')[_this.index]).find('.p-img-mask').hide();
	 } 
	 return '删除';
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
		$('.div-checkRemarkInfo').hide();
	}
}


function closeLayer() {
	if(sessionStorage.shopLayerIndex) {
		parent.layer.close(sessionStorage.shopLayerIndex);
		return;
	}
	parent.layer.closeAll();
}
