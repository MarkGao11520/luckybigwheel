var url = './qrController/getQrList';
var lists = new Array();
var i = 0;
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
    pageSize: 10, //每页的记录行数（*）
    pageList: [10, 15, 20, 30], //可供选择的每页的行数（*）
    uniqueId: "id", //每一行的唯一标识，一般为主键列
    //      responseHandler: responseHandler,
    columns: [{
        field: '',
        title: '编号',
        formatter: function (value, row, index) {
            return index + 1;
        }
    },
        {
            field: 'url',
            title: '链接',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: '',
            title: '是否失效',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                // i++;
                // $('#start').attr('max',i);
                // $('#end').attr('max',i);
                return (row.isFailure == 0 ? '未失效' : '已失效');
            }
        }
    ]
});

$.ajax({
    type:'get',
    url:'./qrController/getSize',
    success:function (result) {
         $('#start').attr('max',result);
         $('#end').attr('max',result);
    }
})

function isF(temp) {
    if(temp==0){
        url = './qrController/getQrList'
    }else if(temp==1){
        url = './qrController/getQrList?isF=0'
    }else if(temp==2){
        url = './qrController/getQrList?isF=1'
    }
    $.ajax({
        type:'get',
        url:url,
        success:function (result) {
            $('#table').bootstrapTable('load',result);    //刷新表格
        }
    })

}


function doCreate() {
    $.ajax({
        type: 'get',
        url: './qrController/doCreateQrBatch',
        data: {
            "num": $('#num').val()
        },
        success: function (result) {
            if (result.result == 1) {
                alert('生成成功');
            } else {
                alert('生成失败');
            }
            $('#table').bootstrapTable('refresh');    //刷新表格
        }
    })
}

function doDownLoad() {
    window.location.href = "./qrController/downLoadZip?start=" + $('#start').val() + "&end=" + $('#end').val();
}



