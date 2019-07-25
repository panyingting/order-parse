package com.jiao.order.parse.controller;

import com.jiao.order.parse.common.WebResult;
import com.jiao.order.parse.entity.MessageType;
import com.jiao.order.parse.entity.MessageRegular;
import com.jiao.order.parse.service.OrderDataRegularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderParseController {

    @Autowired
    private OrderDataRegularService orderDataRegularService;

    @RequestMapping("/addRegular")
    public WebResult addRegular(MessageRegular orderDataRegular){

        if(orderDataRegular.getTypeId() <= 0){
            return WebResult.failResult("报文类型不能为空");
        }
        if(orderDataRegular.getName() == null){
            return WebResult.failResult( "规则名称不能为空");
        }
        if(orderDataRegular.getKey() == null || orderDataRegular.getValue() == null){
            return WebResult.failResult( "规则信息不能为空");
        }

        // 如果这三个字段有一个已填，则其他信息都不允许为空（都得填上）
        if (!StringUtils.isEmpty(orderDataRegular.getKey2()) ||  !StringUtils.isEmpty(orderDataRegular.getValue2()) || orderDataRegular.getOperation() > 0){
            if(StringUtils.isEmpty(orderDataRegular.getKey2()) || StringUtils.isEmpty(orderDataRegular.getValue2()) || orderDataRegular.getOperation() == 0){
                return WebResult.failResult( "规则信息第二个条件不完整！");
            }
        }
        orderDataRegularService.addRegular(orderDataRegular);
        return WebResult.sucessResult();
    }

    @RequestMapping("/deleteRegular")
    public WebResult deleteRegular(@RequestBody List<Integer> idList){
        orderDataRegularService.deleteRegular(idList);
        return WebResult.sucessResult();
    }

    @RequestMapping("/getAllRegular")
    public WebResult getAllRegular(){
        List<MessageRegular> lsit = orderDataRegularService.getAllRegular();
        return WebResult.sucessResult(lsit);
    }




    /************/



    @RequestMapping("/addType")
    public WebResult addMessageType(MessageType messageType){
        if (StringUtils.isEmpty(messageType.getName()) ){
            return WebResult.failResult( "报文类型名称不能为空");
        }
        orderDataRegularService.addType(messageType);

        return WebResult.sucessResult();
    }

    @RequestMapping("/deleteType")
    public WebResult deleteType( @RequestBody List<Integer> idList){
        orderDataRegularService.deleteType(idList);
        return WebResult.sucessResult();
    }

    @RequestMapping("/getMessageTypeAll")
    public WebResult getMessageType(){
        List<MessageType> list = orderDataRegularService.getAllType();
        return WebResult.sucessResult(list);
    }


    /*******************/


    @RequestMapping("/findRegularByMessage")
    public WebResult findRegularByMessage(Integer typeId, String message){

        try{

            MessageRegular messageRegular = orderDataRegularService.findRegularByMessage(message, typeId);
            return WebResult.sucessResult(messageRegular);
        }catch (IllegalArgumentException e){
            return WebResult.failResult(e.getMessage());
        }

    }




}
