$(document).ready(function(){

    initDom();

    // 类型添加
    $("#add_type_btn").click(function(){
        $.ajax({
            type: "POST",
            url: "/order/addType",
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            data: {name:$("#type_name").val()},
            dataType: "json",
            success: function(data){
                alert(data.msg);
                initDom();
            },
             error:function(XMLHttpRequest, textStatus, errorThrown){
                 alert(textStatus+" :"+errorThrown);
             }
        });
    });

    // 类型删除
    $("#del_type_btn").click(function(){

        var idList = [];
        var index = 0;
        $("input[name='td_type_id']").each(function() {
            if ($(this).attr('checked') == true) {
                idList[index++] =  $(this).val();
            }
        });
        $.ajax({
            type: "POST",
            url: "/order/deleteType",
            contentType: 'application/json',
            data: JSON.stringify(idList),
            dataType: "json",
            success: function(data){
                alert(data.msg);
                initDom();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });

    });



    // 规则添加
    $("#add_role_btn").click(function () {
        if($("#type_select option:selected") == null){
            alert("请选择类型！");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/order/addRegular",
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            data: {
                name:$("#rule_name").val(),
                typeId:$("#type_select option:selected").val(),
                operation:$("input[name='operation']:checked").val(),
                key: $("#rule_key").val(),
                value:$("#rule_value").val(),
                key2: $("#rule_key2").val(),
                value2:$("#rule_value2").val()
            },
            dataType: "json",
            success: function(data){
                alert(data.msg);
                initDom();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });
    });

    // 规则删除
    $("#del_rule_btn").click(function(){

        var idList = [];
        var index = 0;
        $("input[name='td_rule_id']").each(function() {
            if ($(this).attr('checked') == true) {
                idList[index++] =  $(this).val();
            }
        });
        $.ajax({
            type: "POST",
            url: "/order/deleteRegular",
            contentType: 'application/json',
            data: JSON.stringify(idList),
            dataType: "json",
            success: function(data){
                alert(data.msg);
                initDom();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });

    });

    // 报文信息提交
    $("#message_submit_btn").click(function () {
        var typeId = $("#type_select_match option:selected").val();
        var message = $("#message").val();
        $.ajax({
            type: "POST",
            url: "/order/findRegularByMessage",
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            data: {message:message, typeId:typeId},
            dataType: "json",
            success: function(data){
                if(data.data != null){
                    alert("匹配成功，您匹配的类型为："+data.data.name);
                }
                else{
                    alert("找不到匹配的规则信息!");
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert(textStatus+" :"+errorThrown);
            }
        });
    });

});

function initDom() {
    $("#type_select").html("");
    $("#type_select_match").html("");
    $("#type_manage").html("");
    $("#rule_manage").html("");

    $("#type_select").append("<option value=0 > 请 选 择 </option>");
    $("#type_select_match").append("<option value=0 > 请 选 择 </option>");
    $.ajax({
        type: "POST",
        url: "/order/getMessageTypeAll",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: "json",
        success: function(data){
            $.each(data.data, function(i, val){
                $("#type_select").append("<option value='"+val.id+"'>"+val.name+"</option>");
                $("#type_select_match").append("<option value='"+val.id+"'>"+val.name+"</option>");

                var html = ("<tr>");
                html += ("<td> <input type='checkbox' name='td_type_id' value='"+val.id+"'/></td>");
                html += ("<td> "+val.name+"</td>");
                html += ("</tr>")
                $("#type_manage").append(html);
            });
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    });


    $.ajax({
        type: "POST",
        url: "/order/getAllRegular",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: "json",
        success: function(data){
            $.each(data.data, function(i, val){
                var operationName = "无";
                if(val.operation ==1){
                    operationName = "或";
                }
                else if(val.operation ==2){
                    operationName = "且";
                }
                var html = ("<tr><td> <input type='checkbox' name='td_rule_id' value='"+val.id+"'/></td>");
                html += ("<td> "+val.name+"</td>");
                html += ("<td> "+val.typeName+"</td>");
                html += ("<td> "+val.key+"</td>");
                html +=("<td> "+val.value+"</td>");
                html +=("<td> "+operationName+"</td>");
                html +=("<td> "+val.key2+"</td>");
                html +=("<td> "+val.value2+"</td>");
                html +=("</tr>")
                $("#rule_manage").append(html)
            });
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus+" :"+errorThrown);
        }
    })
}