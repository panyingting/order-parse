
$(".num").css('backgroundPositionY',0);

commmonObj = {};
var countNUm = 0;

$(function () {
    init();
});

function init() {
    var obj = urlParameters();

    if(obj.id === undefined){
        $.MsgBox.Alert("温馨提示", "请重新获取连接");
        return;
    }
    // 请求登录接口
    $.ajax({
        type: "POST",
        url: "/coupon/getCouponById",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {"encodeId": obj.id},
        dataType: "json",
        success: function(data){
            if(!data.success || data.data == null){
                $.MsgBox.Alert("温馨提示", "获取优惠券信息失败");
            } else {
                $("#draw_span").text(data.data.deliveryNum);
                $("#remain_span").text(data.data.initNum - data.data.deliveryNum);
                startTime = new Date(data.data.limitStartTime);
                endTime = new Date(data.data.limitEndTime);
                commmonObj.couponId = data.data.id;
                commmonObj.startTime = startTime;
                commmonObj.endTime = endTime;
                initDate();
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    });
}

function initDate() {
    countDown();
    function addZero(i) {
        return i < 10 ? "0" + i: i + "";
    }
    function countDown() {

        var endTime = commmonObj.endTime;
        var startTime = commmonObj.startTime;
        var nowTime = new Date();
        var leftTime = parseInt((endTime.getTime() - nowTime.getTime()) / 1000);

        var notStart = startTime > nowTime;
        if(notStart){
            leftTime = parseInt((startTime.getTime() - nowTime.getTime()) / 1000);
        }
        var d = parseInt(leftTime / (24*60*60))
        var h = parseInt(leftTime / (60 * 60) % 24);
        var m = parseInt(leftTime / 60 % 60);
        var s = parseInt(leftTime % 60);
        d = addZero(d);
        h = addZero(h);
        m = addZero(m);
        s = addZero(s);
        if(notStart){
            document.querySelector(".count").innerHTML = "活动还有<span>"+ d+"天 "+h+"时"+m+"分"+s+"秒 </span>开始";
            setTimeout(countDown, 1000);
        }else {
            countNUm ++;
            document.querySelector(".count").innerHTML = "活动剩余<span>"+ d+"天 "+h+"时"+m+"分"+s+"秒</span>结束";
            if (leftTime <= 0) {
                document.querySelector(".count").innerHTML = "活动已结束";
                return;
            }
            if(countNUm % 10 === 0){
                setTimeout(init, 1000);
            }else {
                setTimeout(countDown, 1000);
            }
        }

    }
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