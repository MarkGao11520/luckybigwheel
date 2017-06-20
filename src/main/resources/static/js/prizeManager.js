var url = './pageController/getPrizeList';
var prizePic = null;
var control = $('#file');
var useNum = 0;
var rateNum = 0.0;
initFileUpload();
initTable();
initUseNum();

function initTable() {
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
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 7, //每页的记录行数（*）
        pageList: [7, 10, 15, 20], //可供选择的每页的行数（*）
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        exportDataType: 'all',
        //      responseHandler: responseHandler,
        columns: [{
            field: '',
            title: '编号',
            formatter: function(value, row, index) {
                return index + 1;
            }
        },
            {
                field: 'prizeName',
                title: '奖品名称',
                align: 'center',
                valign: 'middle',
                sortable: true
            },{
                field: 'value',
                title: '值',
                align: 'center',
                valign: 'middle',
                sortable: true
            },{
                field: 'type',
                title: '类型',
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter:function(value, row, index) {
                    if(row.type==0){
                        return "其他";
                    }else if(row.type==1){
                        return "红包";
                    }else if(row.type==2){
                        return "积分";
                    }

                    return '<img  onclick="bigImg(this)" src = "' + row.prizePic + '" style= "height:50px;width:50px" >点击查看大图</img>'
                }
            }, {
                field: 'prizeRate',
                title: '中奖率',
                align: 'center',
                valign: 'middle',
                sortable: true
            },{
                field: 'prizePic',
                title: '奖品图片',
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter: function(value, row, index) {
                    return '<img  onclick="bigImg(this)" src = "' + row.prizePic + '" style= "height:50px;width:50px" >点击查看大图</img>'
                }
            }, {
                field: 'isUse',
                title: '是否禁用',
                align: 'center',
                valign: 'middle',
                formatter:function (value, row, index) {
                    if(row.isUse==1){
                        return	'<span style="color: red">是</span>';
                    }else{
                        return	'否';
                    }
                }
            }, {
                field: '',
                title: '操作',
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter: function(value, row, index) {
                    var str = '';
                    str += '<button class="btn btn-warning" onclick="openDesignPanel('+false+','+row.id+',\''+row.prizeName+'\''+',\''+row.prizeRate+'\''+','+(row.isUse==1?false:true)+')">编辑</button>&nbsp;&nbsp;';
                    if(row.isUse==1){
                        str +='<button onclick="updateIsUse('+row.id+','+0+','+row.prizeRate+')" class="btn btn-warning">启用</button>';
                    }else{
                        str +='<button onclick="updateIsUse('+row.id+','+1+','+row.prizeRate+')" class="btn btn-warning">禁用</button>';
                    }
                    return str;
                }
            }
        ]
    });
}


function initFileUpload() {
    control.fileinput({
        language:'zh',  //设置语言
        uploadUrl:'./pageController/uploadPrizeImage',
//                uploadAsync:false,
        dropZoneEnabled:true,
        showCaption:false,   //是否显示标题
        showPreview:true,
        showUpload : false,//是否显示上传按钮
        allowedFileExtensions:['png','jpg'],
        maxFileCount:10,
        enctype:'multipart/form-data',
        browseClass:"btn btn-success"
    })
        .on("fileuploaded",function (event,data,c,d) {
            if((data.response)){
                if(data.response.isSuccess){
                    prizePic = data.response.url
                    // alert("处理成功");
                }else {
                    alert("处理失败");
                }
            }else{
                alert("处理失败");
            }

        })
        .on("filebatchselected",function (event,files) {
             $(this).fileinput("upload");
        })
        .on("fileerror",function (a,b,c) {
            alert("失败");
        });
}

function initUseNum() {
	$.ajax({
		type:'get',
		url:'./pageController/getUseNum',
		success:function (result) {
			useNum = result.useNum;
			rateNum = result.rateNum;
        },
		error:function (error) {
			alert("访问服务器失败")
        }
	})
}


function bigImg(obj) {
	$('.winright img').attr("src", obj.src);
	var windowW = $(window).width();
	var windowH = $(window).height();
	var rheight = (obj.height * 750) / obj.width;
	var w = (windowW - 750) / 2;
	if(rheight > windowH) {
		var h = 10;
	} else {
		var h = (windowH - rheight) / 2 - 30;
	}

	var myAlert = document.getElementById("imgBig");
	myAlert.style.display = "block";
	myAlert.style.position = "fixed";
	myAlert.style.top = h + "px";
	myAlert.style.left = w + "px";
	var bgObj = document.getElementById("bgDiv");
	bgObj.style.display = "block";
	bgObj.style.position = "fixed";
	bgObj.style.top = "0";
	bgObj.style.left = "0";
	bgObj.style.background = "#777";
	bgObj.style.filter = "alpha(opacity:40)";
	bgObj.style.zoom = "1";
	bgObj.style.opacity = "0.6";
	bgObj.style.width = "100%";
	bgObj.style.height = "100%";
}


function removeTr(id) {
	$('#designid_' + id).remove()
}

function closeDesignPanel() {
	$('#desginPanel').hide();
	$('#prizeName').val("");
	$('#prizeRate').val("");
	prizePic = null;
}

function openDesignPanel(isAdd,id,name,rate,isUse) {
	if(isAdd){
        $('#desigenSumbit').attr('onclick','add()');
	}
	else {
		$('#prizeName').val(name);
		$('#prizeRate').val(rate);
        $('#desigenSumbit').attr('onclick','update('+id+','+rate+','+isUse+')');
    }
    $('#desginPanel').show();
}

function check() {
	if($('#prizeName').val()==null||$('#prizeName').val()==""){
		alert("奖品名不能为空");
		return false;
	}else if($('#prizeRate').val()==null||$('#prizeRate').val()==""){
		alert("中奖率不能为空")
		return false;
	}else if(isNaN($('#prizeRate').val())||$('#prizeRate').val()>=1||$('#prizeRate').val()<=0){
		alert("中奖率必须位0-1之间的数字");
		return false;
	}else if($('#value').val()==null||$('#value').val()==""){
        alert("值不能为空")
        return false;
    }else if(isNaN($('#value').val())||$('#value').val()<=0){
        alert("值必须为大于0的数字");
        return false;
    }else {
		return true;
	}
}

function checkRate(oldRate,isUse){
	if(isUse){
        if((rateNum-oldRate+(1*$('#prizeRate').val()))>1){
            alert("启用奖品总中奖率不能超过1");
            return false;
        }else {
            return true;
        }
	}else{
        return true;
    }

}

function add() {
    if(check()){
    	if(checkRate(0,true)){
            $.ajax({
                type:'post',
                url:'./pageController/addPrize',
                data:{
                    type:$('#prizeType').val(),
                    value:$('#value').val(),
                    prizeName:$('#prizeName').val(),
                    prizeRate:$('#prizeRate').val(),
                    prizePic:prizePic
                },
                success:function (result) {
                    if(result.result==1){
                        alert("添加成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        initUseNum();
                        closeDesignPanel();
                    }else if(result.result==-2){
                        alert("该奖品已经存在");
                    }else {
                        alert("添加失败");
                    }
                },
                error:function (error) {
                    alert("访问服务器失败")
                }
            })
		}
    }
}

function update(id,rate,isUse) {
    if(check()){
    	if(checkRate(rate,isUse)){
            $.ajax({
                type:'post',
                url:'./pageController/updatePrzie',
                data:{
                    id:id,
                    type:$('#prizeType').val(),
                    value:$('#value').val(),
                    prizeName:$('#prizeName').val(),
                    prizeRate:$('#prizeRate').val(),
                    prizePic:prizePic
                },
                success:function (result) {
                    if(result.result==1){
                        alert("编辑成功");
                        $('#table').bootstrapTable('refresh');    //刷新表格
                        initUseNum();
                        closeDesignPanel();
                    }else {
                        alert("编辑失败");
                    }
                },
                error:function (error) {
                    alert("访问服务器失败")
                }
            })
		}
    }
}

function updateIsUse(id,isUse,rate) {
	if(isUse==0){
        if((rateNum-0+(1*rate))>1){
            alert("启用奖品总中奖率不能超过1");
            return ;
        }
        if(useNum>=5){
			alert("启用奖品最多为5个");
			return;
        }
	}
        $.ajax({
            type:'post',
            url:'./pageController/updatePrzie',
            data:{
                id:id,
                isUse:isUse
            },
            success:function (result) {
                if(result.result==1){
                    alert("修改成功");
                    initUseNum();
                    $('#table').bootstrapTable('refresh');    //刷新表格
                    closeDesignPanel();
                }else {
                    alert("修改失败");
                }
            },
            error:function (error) {
                alert("访问服务器失败")
            }
        })

}