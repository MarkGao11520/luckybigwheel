/**
 * Created by gaowenfeng on 2017/6/20.
 */
var redList ;
var intList ;
var otherList ;
initData();
function initData(){
    $.ajax({
        type:'get',
        url:'../userController/getUserInfo',
        success:function (result) {
            $('#uname').html(result.nikeName==null?"":result.nikeName);
            $('#name').html(result.name==null?"":result.name);
            $('#phone').html(result.phone==null?"":result.phone);
            $('#redEnvelope').html(result.redEnvelope==null?"":result.redEnvelope);
            $('#integral').html(result.integral==null?"":result.integral);
            redList = result.redEnvelopeRecord==null?new Array():result.redEnvelopeRecord;
            intList = result.integralRecord==null?new Array:result.integralRecord;
            otherList = result.otherRecord==null?new Array:result.otherRecord;
        }
    })
}

function open(temp) {
    var list
    if(temp==1)
        list = redList;
    else if(temp==2)
        list = intList;
    else
        list = otherList;
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

function edit() {
    var phone =  $('#phone').html();
    $('#phone').html('<input type="text" id="phone_i" value="'+phone+'"/>')
    var name = $('#name').html();
    $('#name').html('<input type="text" id="name_i" value="'+name+'"/>')
    $('#edit').html('<a href="javascript:sub()">提交</a>')
}

function sub() {
    $.ajax({
        type:'get',
        url:'../userController/doUpdateUser',
        data:{
            name:$('#name_i').val(),
            phone:$('#phone_i').val()
        },
        success:function (result) {
            if(result.result>0){
                alert("提交成功");
            }else {
                alert("提交失败");
            }
            initData();
        }
    })
    $('#edit').html('<a href="javascript:edit()">编辑</a>')

}