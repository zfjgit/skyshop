function LoginUserInfo() {
	this.code = '';
	this.password = '';
	
	this.setCode = function(code) {
		this.code = code;
	}
	this.setPassword = function(pwd) {
		this.password = pwd;
	}
}

function UserOverviewInfo() {
	this.todayIncome = parseFloat(0).toFixed(2);
	this.deviceCount = 0;
	this.addressCount = 0;
	this.orderCount = 0;
	this.priceCount = 0;
	this.agencyCount = 0;
	this.partitionCount = 0;
}

function UserInfo() {
	this.id = 0;
	this.code = '';
	this.name = '';
	this.type = new EnumInfo();
	this.status = new EnumInfo();
	this.agency = new AgencyInfo();
}

function AgencyInfo() {
	this.id = 0;
	this.name = '';
	this.level = new EnumInfo();
	this.parent = {
		id : 0, 
		name : '',
		level : new EnumInfo()
	};
	this.parentPercentage = 0;
	this.percentage = 0;
	this.balance = parseFloat(0).toFixed(2);
}

function EnumInfo() {
	this.code = '';
	this.name = '';
}