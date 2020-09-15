$(function(){



    $.ajax({
        type: "get",
        url: "/coupon/getAllCouponList",
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: function(data){
            if(data.success){
                var dataArr = data.data;
                $.each(dataArr, function(index,element){
                    var startTime = new Date(element.limitStartTime).Format("yyyy-MM-dd HH:mm");
                    var endTime = new Date(element.limitEndTime).Format("yyyy-MM-dd HH:mm");
                   $("#data_table tbody").append(  tr(element.id, element.name, element.initNum, element.deliveryNum, element.desc, element.idEncode, element.type, element.isDefault, startTime, endTime));
                });
            }else {
                alert(data.msg);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    });
});

function tr(id, name, initNum, deliveryNum, desc, idEncode, type, isDefault, startTime, endTime) {
    return "<tr>\n" +
        "          <td><input type=\"checkbox\" name=\"id\" value=\""+id+"\" />"+id+"</td>\n" +
        "          <td>"+name+"</td>\n" +
        "          <td>"+initNum+"</td>\n" +
        "          <td>"+deliveryNum+"</td>  \n" +
        "           <td>"+desc+"</td>         \n" +
        "           <td>"+startTime+"</td>         \n" +
        "           <td>"+endTime+"</td>         \n" +
        "           <td>"+idEncode+"</td>         \n" +
        "          <td>"+type+"</td>\n" +
        "          <td>"+ ((isDefault === 1) ? "是" : "否")+"</td>\n" +
        "          <td><div class=\"button-group\">  <a class=\"button border-main\" href=\"addCoupon.html?id="+id+"\"><span class=\"icon-edit\"></span> 修改</a> " +
        "                       <a class=\"button border-red\" href=\"javascript:void(0)\" onclick=\"return del("+id+")\"><span class=\"icon-trash-o\"></span> 删除</a> </div></td>\n" +
        "        </tr>";
}


function del(id){
    if(confirm("您确定要删除吗?")){
        $.ajax({
            type: "get",
            url: "/coupon/deleteCoupon",
            contentType: 'application/x-www-form-urlencoded',
            dataType: "json",
            data:{"id": id},
            success: function(data){
                if(data.success){
                    $(location).attr('href', 'couponList.html');
                }else {
                    alert(data.msg);
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });
    }
}
