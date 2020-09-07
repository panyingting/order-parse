package com.jiao.order.parse.service;

import com.alibaba.fastjson.JSONObject;
import com.jiao.order.parse.entity.MessageType;
import com.jiao.order.parse.entity.MessageRegular;
import com.jiao.order.parse.repository.MessageTypeRepository;
import com.jiao.order.parse.repository.MessageRegularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDataRegularService {

    @Autowired
    private MessageRegularRepository messageRegularRepository;

    @Autowired
    private MessageTypeRepository messageTypeRepository;

    public MessageRegular addRegular(MessageRegular messageRegular){
        if(messageRegular.getTypeId() > 0){
            MessageType messageType = messageTypeRepository.getOne(messageRegular.getTypeId());
            messageRegular.setTypeName(messageType.getName());
        }
        return messageRegularRepository.save(messageRegular);
    }


    public List<MessageRegular> getRegularByType(int typeId){
        return messageRegularRepository.findByTypeId(typeId);
    }

    public void deleteRegular(List<Integer> idList){
        if(idList != null){
            idList.forEach(id->messageRegularRepository.deleteById(id));
        }
    }

    public List<MessageRegular> getAllRegular(){
        return messageRegularRepository.findAll();
    }




    public MessageType addType(MessageType messageType){
        return messageTypeRepository.save(messageType);
    }

    public MessageType getType(int id){
        if(id < 1){
            throw new IllegalArgumentException("id 不合法");
        }
        return messageTypeRepository.getOne(id);
    }

    public void deleteType(List<Integer> idList){
        if(idList != null){
            idList.forEach(id->messageTypeRepository.deleteById(id));
        }
    }

    public List<MessageType> getAllType(){
        return messageTypeRepository.findAll();
    }


    public MessageRegular findRegularByMessage(String message, int typeId){

        JSONObject jsonObject;
        try{
            jsonObject = JSONObject.parseObject(message);

        }catch (Exception ex){
            throw new IllegalArgumentException("非法报文信息，请输入正确的Json字符串, 可以到https://www.json.cn/ 验证格式");
        }

        List<MessageRegular> regularList = messageRegularRepository.findByTypeId(typeId);
        
        for(MessageRegular regular: regularList){
            String key = regular.getKey();
            String value = regular.getValue();
            int operation = regular.getOperation();
            Object jsonValue = jsonObject.get(key);

            if(equal(value, jsonValue)){
                if(operation > 0){
                    String key2 = regular.getKey2();
                    String value2 = regular.getValue2();
                    Object jsonValue2 = jsonObject.get(key2);

                    if(!equal(value2, jsonValue2)){
                        continue;
                    }
                }
                return regular;
            }
        }
        return null;
    }

    private boolean equal(String value, Object jsonValue){
        if(value.startsWith("\"") && value.endsWith("\"")){
            value = value.substring(1, value.length() - 1);
        }
        return jsonValue != null && value.equals(jsonValue.toString());
    }

}
