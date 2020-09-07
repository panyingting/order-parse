$(function(){

    $("#addCoupon").click(function () {
        var initNum = $("input[name='initNum']").val();
        var deliveryNum = $("input[name='deliveryNum']").val();
        var type = $("#coupon_type").find("option:selected").val();
        var desc = $("input[name='desc']").val();
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
        $.ajax({
            type: "POST",
            url: "/coupon/addCoupon",
            contentType: 'application/x-www-form-urlencoded',
            data: {"initNum":initNum, "deliveryNum":deliveryNum, "type":type, "desc":desc, "name":name, "limitStartTime":limitStartTime, "limitEndTime":limitEndTime},
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