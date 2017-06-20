var url = './pageController/getUserList';
var lists = new Array();
var i = 0 ;
$('#table').bootstrapTable({
	dataType: 'json',
	cache: false,
	striped: true, //是否显示行间隔色
	sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
	url: url,
	toolbar: '#query',
	//				height: $(window).height() - 110,
	//				width: $(window).width(),
	showColumns: true,
	pagination: true,
	//      queryParams : queryParams,
	minimumCountColumns: 2,
	async: false,
	pageNumber: 1, //初始化加载第一页，默认第一页
	pageSize: 7, //每页的记录行数（*）
	pageList: [7, 10, 15, 20], //可供选择的每页的行数（*）
	uniqueId: "id", //每一行的唯一标识，一般为主键列
	//      responseHandler: responseHandler,
	columns: [{
			field: '',
			title: '编号',
			formatter: function(value, row, index) {
				return index + 1;
			}
		},
		{
			field: 'openId',
			title: 'OPENID',
			align: 'center',
			valign: 'middle',
			sortable: true
		}, {
			field: 'nikeName',
			title: '昵称',
			align: 'center',
			valign: 'middle',
			sortable: true
		},{
            field: 'name',
            title: '姓名',
            align: 'center',
            valign: 'middle',
            sortable: true
        },{
            field: 'phone',
            title: '手机号',
            align: 'center',
            valign: 'middle',
            sortable: true
        },{
            field: 'redEnvelope',
            title: '红包',
            align: 'center',
            valign: 'middle',
            sortable: true
        },{
            field: 'integral',
            title: '积分',
            align: 'center',
            valign: 'middle',
            sortable: true
        },{
            field: '',
            title: '操作',
            align: 'center',
            valign: 'middle',
            formatter:function (value, row, index) {
            	var list = row.records;
				lists[i] = list;
				return '<a href="javascript:open('+(i++)+')"><button class="btn btn-warning">中奖纪录</button></a>'
            }
        }
	]
});

function open(j) {
	 var list = lists[j];
    var str=""
    for(var i=0;i<list.length;i++){
        str += '<tr>' +
            '<td>'+list[i].recordCode+'</td>' +
            '<td>'+list[i].prize.prizeName+'</td>' +
            '<td>'+list[i].crateTime+'</td>' +
            '<td>'+(list[i].state==0?'<span color="red">未核销</span>':'已核销')+'</td>' +
            '</tr>'
    }
    $('#tbody').html(str);
    $('#recordList').show();
}

function close() {
    $('#recordList').hide();
}

function doCancelAfterVerification() {
	$.ajax({
		type:'get',
		url:'../pageController/doCancelAfterVerification',
		data:{
			"code":$('#code').val()
		},
		success:function (result) {
			if(result.result==1){
				alert('已核销');
			}else if(result.result==0){
				alert('该号码不存在');
			}else {
				alert('失败');
			}
        }
	})
}



