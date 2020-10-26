$(function(){

    $("#addCoupon").click(function () {
        var id = $("input[name='id']").val();
        var initNum = $("input[name='initNum']").val();
        var deliveryNum = $("input[name='deliveryNum']").val();
        var type = $("#coupon_type").find("option:selected").val();
        var desc = $("input[name='desc']").val();
        var isDefault = $("#isDefault").find("option:selected").val();
        var name = $("input[name='name']").val();
        var limitStartTime = $("input[name='limitStartTime']").val();
        var limitEndTime = $("input[name='limitEndTime']").val();
        limitStartTime = convertDateFromString(limitStartTime);
        limitEndTime = convertDateFromString(limitEndTime);

        if(!checkParam(initNum)){
            alert("初始总数量不能为空"); return;
        }
        if(!checkParam(deliveryNum)){
            alert("初始已消费数量不能为空"); return;
        }
        if(!checkParam(type)){
            alert("初始类型不能为空"); return;
        }

        if(!checkParam(isDefault) || isDefault == -1){
            alert("是否为默认优惠券选项不能为空"); return;
        }
        if(!checkParam(desc)){
            alert("配置金额不能为空"); return;
        }
        if(!checkParam(name)){
            alert("优惠券名称不能为空"); return;
        }
        if(!checkParam(limitStartTime) || limitStartTime <=0){
            alert("活动开始时间不能为空"); return;
        }
        if(!checkParam(limitEndTime) || limitEndTime <=0){
            alert("活动结束时间不能为空"); return;
        }

        limitStartTime = limitStartTime.getTime();
        limitEndTime = limitEndTime.valueOf();
        if(!checkParam(limitStartTime) || limitStartTime <=0){
            alert("活动开始时间不能为空2"); return;
        }
        if(!checkParam(limitEndTime) || limitEndTime <=0){
            alert("活动结束时间不能为空2"); return;
        }
        if(id === ""){
            id = 0;
        }
        $.ajax({
            type: "POST",
            url: "/coupon/addCoupon",
            contentType: 'application/x-www-form-urlencoded',
            data: {"id":id,"initNum":initNum, "deliveryNum":deliveryNum, "type":type, "isDefault":isDefault, "desc":desc, "name":name, "limitStartTime":limitStartTime, "limitEndTime":limitEndTime},
            dataType: "json",
            success: function(data){
                if(data.success){
                    alert("优惠券信息添加成功");
                    $(location).attr('href', 'couponList.html');
                }else {
                    alert(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });
    });

    var requestParameters = urlParameters();
    var id = requestParameters["id"];
    if(id != null){
        $.ajax({
            type: "POST",
            url: "/coupon/getCoupon",
            contentType: 'application/x-www-form-urlencoded',
            data: {"id":id},
            dataType: "json",
            success: function(data){
                if(data.success){
                    var entity = data.data;
                    $("input[name='id']").val(entity.id);
                    $("input[name='initNum']").val(entity.initNum);
                    $("input[name='deliveryNum']").val(entity.deliveryNum);
                    $("input[name='desc']").val(entity.desc);
                    $("input[name='name']").val(entity.name);
                    var limitStartTime = new Date(entity.limitStartTime).Format("yyyy-MM-dd HH:mm:ss");
                    var limitEndTime = new Date(entity.limitEndTime).Format("yyyy-MM-dd HH:mm:ss");
                    $("input[name='limitStartTime']").val(limitStartTime);
                    $("input[name='limitEndTime']").val(limitEndTime);
                }else {
                    alert(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });
    }

    
});

function convertDateFromString(dateString) {
    if (dateString) {
        var arr1 = dateString.split(" ");
        var sdate = arr1[0].split('-');
        var date = new Date(sdate[0], sdate[1]-1, sdate[2]);
        return date;
    }
}

function checkParam( param) {
    if(param == null || param.length <= 0){
        return false;
    }
    return true;
}


function urlParameters() {
    //返回当前 URL 的查询部分（问号 ? 之后的部分）。
    var urlParameters = location.search;
    //声明并初始化接收请求参数的对象
    var requestParameters = new Object();
    //如果该求青中有请求的参数，则获取请求的参数，否则打印提示此请求没有请求的参数
    if (urlParameters.indexOf('?') != -1)
    {
        //获取请求参数的字符串
        var parameters = decodeURI(urlParameters.substr(1));
        //将请求的参数以&分割中字符串数组
        var parameterArray = parameters.split('&');
        //循环遍历，将请求的参数封装到请求参数的对象之中
        for (var i = 0; i < parameterArray.length; i++) {
            requestParameters[parameterArray[i].split('=')[0]] = (parameterArray[i].split('=')[1]);
        }
        console.info('theRequest is =====',requestParameters);
    }
    else
    {
        console.info('There is no request parameters');
    }
    return requestParameters;
}