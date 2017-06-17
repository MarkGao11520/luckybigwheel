var url = './pageController/getUserList';

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
			field: 'prize',
			title: '奖品',
			align: 'center',
			valign: 'middle',
			sortable: true
		}
	]
});



