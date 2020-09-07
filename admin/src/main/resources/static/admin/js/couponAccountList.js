
$(function(){

    query(1, 2);

});

function query(page, size) {
    $.ajax({
        type: "get",
        url: "/coupon/getAllCouponAccountList",
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        data:{"page":page, "size": size},
        success: function(data){
            if(data.success){
                var pageObj = data.data;
                var dataArr = pageObj.content;
                var tb_body_html = "";
                $.each(dataArr, function(index,element){
                    tb_body_html +=  tr(element);
                });

                $("#data_table tbody").html( tb_body_html);
                $("#page_tr").html( getPageInfo(page, pageObj.totalPages));

                $("#page_tr a").click(function () {
                    var pageStr = $(this).attr("id");
                    query( parseInt(pageStr), size);
                });
            }else {
                alert(data.msg);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    });
}

function getPageInfo(page, pageNum) {
    var prev = page > 1 ? page -1  : 1;
    var next = pageNum > page ? page + 1 : pageNum;
    return "<td colspan=\"8\"><div class=\"pagelist\"> <a href=\"#\" id='"+prev+"'>上一页</a> <span class=\"current\">总页："+pageNum+" /当前页："+page+"</span><a href=\"#\" id='"+next+"'>下一页</a> </div></td>"
}

function tr(element) {
    return "<tr>\n" +
        "          <td><input type=\"checkbox\" name=\"id\" value=\""+element.id+"\" />"+element.id+"</td>\n" +
        "          <td>"+element.userName+"</td>\n" +
        "          <td>"+element.phone+"</td>\n" +
        "          <td>"+element.couponId+"</td>  \n" +
        "           <td>"+element.couponName+"</td>         \n" +
        "           <td>"+element.faceValue+"</td>         \n" +
        "           <td>"+ new Date(element.getTime).Format("yyyy-MM-dd HH:mm")+"</td>         \n" +
        "           <td>"+element.remark+"</td>         \n" +
        "          <td><div class=\"button-group\"> <a class=\"button border-red\" href=\"javascript:void(0)\" onclick=\"return del("+element.id+")\"><span class=\"icon-trash-o\"></span> 删除</a> </div></td>\n" +
        "        </tr>";
}

function del(id){
    if(confirm("您确定要删除吗?")){
        $.ajax({
            type: "get",
            url: "/coupon/deleteAccountCoupon",
            contentType: 'application/x-www-form-urlencoded',
            dataType: "json",
            data:{"id": id},
            success: function(data){
                if(data.success){
                    $(location).attr('href', 'couponAccountList.html');
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