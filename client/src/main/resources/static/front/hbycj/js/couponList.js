

var couponArr = [];



$(function () {
    // 请求登录接口
    $.ajax({
        type: "POST",
        url: "/coupon/getMyCoupons",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        dataType: "json",
        success: function(data){
            if(!data.success || data.data == null){
                $.MsgBox.Alert("温馨提示", "获取优惠券信息失败");
            } else {
                var nowDate = new Date();
                for(var i=0; i< data.data.length; i++){
                    var ele = data.data[i];
                    var effectTime = new Date(ele.effectTime);
                    var expirationTime = new Date(ele.expirationTime);

                    var effectTimeStr = effectTime.Format("yyyy-MM-dd");
                    var expirationTimeStr = expirationTime.Format("yyyy-MM-dd");
                    var className = "stamp0" + (i%4+1);
                    var statClass = nowDate.getTime() < effectTime.getTime() ? "not_begin" : nowDate.getTime() > expirationTime.getTime() ? "expire" : "activity";
                    var div = divGet(ele.couponName, ele.faceValue, "红包", effectTimeStr, expirationTimeStr, className, statClass, ele.id);
                    $("#myCouponList").append(div);

                    var myCouponListObj = {};
                    myCouponListObj.effectTime = effectTime;
                    myCouponListObj.expirationTime = expirationTime;
                    couponArr[i] = myCouponListObj;
                }
                updateTime();
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    });

});

function divGet(name, money, type, startTime, endTime, className, statClass, couponAccId) {
    var btnTxt;
    var inActivity = false;
    switch (statClass){
        case "not_begin":
            btnTxt = "尚未开始";
            break;
        case "expire":
            btnTxt = "活动已结束";
            break;
        default :
            btnTxt = "点击去使用";
            inActivity = true;
            break;

    }
    return "<div class='stamp "+className+"'> <div class='par'><p>"+name+"</p><sub class='sign'>￥</sub><span>"+money+"</span><br/><sub>红包优惠</sub></div>\n" +
    "    <div class='copy'><p class='times-p'>"+startTime+"<br>"+endTime+"</p><a href='#' onclick=' return gotoUseCoupon("+couponAccId+", "+inActivity+")' class="+statClass+">"+btnTxt+"</a></div><i></i></div>";
}
function gotoUseCoupon(couponAccountId, inActivity) {

    if(inActivity){
        $.MsgBox.Confirm("温馨提示", "您的中奖信息已记录到系统中，请直接联系客服人员进行优惠购买",function () {
            // 请求登录接口
            $.ajax({
                type: "POST",
                url: "/coupon/useCoupon",
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: "json",
                data: {"couponAccountId": couponAccountId},
                success: function(data){
                    if(!data.success ){
                        $.MsgBox.Alert("温馨提示", data.msg);
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert(textStatus+" :"+errorThrown);
                }
            });
        });
    }
}

function updateTime() {

    $("#myCouponList .stamp").each(function ( idx, ele) {
        countDown(couponArr[idx].effectTime, couponArr[idx].expirationTime, $(ele).find(".times-p")[0]);
    });
}

function countDown(startTime, endTime, ele) {

    var nowTime = new Date();
    var leftTime = parseInt((endTime.getTime() - nowTime.getTime()) / 1000);

    var notStart = startTime > nowTime;
    if(notStart){
        leftTime = parseInt((startTime.getTime() - nowTime.getTime()) / 1000);
    }
    if (!notStart && leftTime <= 0) {
        $(ele).html("敬请期待下次活动 <br/><br/>");
        return;
    }
    var d = parseInt(leftTime / (24*60*60))
    var h = parseInt(leftTime / (60 * 60) % 24);
    var m = parseInt(leftTime / 60 % 60);
    var s = parseInt(leftTime % 60);
    h = addZero(h);
    m = addZero(m);
    s = addZero(s);

    var dayTxt = d > 0 ? d+"天<br/>" : "<br/>";
    if(notStart){
        $(ele).html("活动即将开始 <br/>"+ dayTxt + h+":"+m+":"+s);
        setTimeout(updateTime, 1000);
    }else {
        $(ele).html("活动剩余时间 <br/>"+ dayTxt + h+":"+m+":"+s);
        setTimeout(updateTime, 1000);
    }

}

function addZero(i) {
    return i < 10 ? "0" + i: i + "";
}