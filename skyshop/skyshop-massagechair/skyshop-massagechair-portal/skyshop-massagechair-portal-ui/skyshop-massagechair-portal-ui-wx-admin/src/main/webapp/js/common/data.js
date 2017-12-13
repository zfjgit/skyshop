function ChairManageInfo() {
    this.chairs = [ new MassageChairInfo() ];
    this.chairName = '';
}

function GSMManageInfo() {
    this.gsms = [ new GSMModuleInfo() ];
    this.imei = '';
}

function SIMManageInfo() {
    this.sims = [ new SIMCardInfo() ];
    this.sim = '';
}

function AddrManageInfo() {
    this.addrs = [ new InstallAddressInfo() ];
    this.addrName = '';
}

function PriceManageInfo() {
    this.prices = [ new PriceInfo() ];
    this.priceName = '';
}

function AgencyManageInfo() {
    this.agencys = [ new AgencyInfo() ];
    this.agencyName = '';
}

function OrderManageInfo() {
    this.orders = [];
    this.total = 0;
    
    this.startDate = today();
    this.endDate = tomorrow();
}

function MassageChairInfo() {
    this.id = 0;
    this.name = '';
    this.brand = '';
    this.gsm = new GSMModuleInfo();
    this.status = new EnumInfo();
    this.installationAddress = new InstallAddressInfo();
    this.deleteStatus = new EnumInfo();
    this.agency = new AgencyInfo();
    this.description = '';
    this.prices = [ new PriceInfo() ];
    this.qrcode = '';
    
    this.gsmPairs = {};
    this.addrPairs = {};
    this.pricePairs = {};
    this.statusPairs = {
        '未投放' : 'A',
        '正常未使用' : 'B',
        '正常使用中' : 'C',
        '故障' : 'D'
    };
    
    this.detailUrl = 'chair_detail.html?';
}
function GSMModuleInfo() {
    this.id = 0;
    this.imei = '';
    this.name = '';
    this.module = '';
    this.simCard = new SIMCardInfo();
    this.status = new EnumInfo();
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.simPairs = {};
    this.statusPairs = {
        '未使用' : 'A',
        '故障' : 'B',
        '使用中' : 'C'
    };
    
    this.detailUrl = 'gsm_detail.html?';
}
function SIMCardInfo() {
    this.id = 0;
    this.sim = '';
    this.name = '';
    this.status = new EnumInfo();
    this.dataFlow = 100;
    this.dueDate = '';
    this.operator = new EnumInfo();
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.operatorPairs = {
        '电信' : 'A',
        '移动' : 'B',
        '联通' : 'C'
    };
    
    this.simPairs = {};
    this.statusPairs = {
        '未使用' : 'A',
        '故障' : 'B',
        '使用中' : 'C'
    };
    
    this.detailUrl = 'sim_detail.html?';
}

function InstallAddressInfo() {
    this.id = 0;
    this.fullAddress = '';
    this.contact = '';
    this.contactNumber = '';
    this.agency = new AgencyInfo();
    this.province = new AddressInfo();
    this.city = new AddressInfo();
    this.district = new AddressInfo();
    this.detailAddress = '';
    this.deleteStatus = new EnumInfo();
    this.description = '';
    
    this.chairs = [];
    this.chairPairs = {};
    
    this.detailUrl = 'addr_detail.html?';
}

function AddressInfo() {
    this.id = 0;
    this.name = '';
    this.level = 1;
    this.code = 0;
    this.parent = {
        code : 0,
        name : ''
    };
}

function OrderInfo() {
    this.createTime = '';
    this.minutes = 0;
    this.money = 0;
    this.agency = {
        name : ''
    };
    this.payType = {
        name : ''
    };
    this.chair = {
        name : ''
    };
    this.payStatus = {
        name : ''
    };
    this.installationAddress = {
        name : ''
    };
    this.deleteStatus = {
        name : ''
    };
    
    this.detailUrl = 'order_detail.html?';
}

function MalfunctionInfo() {
    this.id = 0;
    this.chair = new MassageChairInfo();
    this.status = new EnumInfo();
    this.type = new EnumInfo();
    this.description = '';
}

function PriceInfo() {
    this.id = 0;
    this.name = '';
    this.money = 0;
    this.minutes = 0;
    this.img = '';
    this.agency = new AgencyInfo();
    
    this.detailUrl = 'price_detail.html?';
}

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
    this.deleteStatus = new EnumInfo();
    this.balance = parseFloat(0).toFixed(2);
    
    this.root = {
        id : 0,
        name : '平台/公司'
    };
    
    this.levelPairs = {
        "平台/公司" : 'A',
        "区域总代" : 'C',
        "代理商-E" : 'E',
        "代理商-M" : 'M'
    };
    
    this.parentPairs = {};
    
    this.detailUrl = 'agents_detail.html?';
}

function EnumInfo() {
    this.code = '';
    this.name = '';
}