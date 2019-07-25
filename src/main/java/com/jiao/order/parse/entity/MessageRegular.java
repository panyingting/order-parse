package com.jiao.order.parse.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息规则
 */
@Entity
@Table
public class MessageRegular {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int typeId;

    private String typeName;

    private String name;

    private String key;

    private String value;

    private int operation;

    private String key2;

    private String value2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static void main(String[] args) throws IOException {

        TypeReference<List<String>> strListRef = new TypeReference<List<String>>() {};
        TypeReference<List<Integer>> intListRef = new TypeReference<List<Integer>>() {};

        ObjectMapper mapper = new ObjectMapper();

        List<String> list = new ArrayList<String>(){{ add("1"); add("2");}};

        List<Integer> intList;

        System.out.println(mapper.writeValueAsString(list));

        String[] strArr = {"1","3"};

        System.out.println(mapper.writeValueAsString(strArr));

        intList = mapper.readValue("[\"1\",\"3\"]", intListRef);

        System.out.println(list);

        intList = mapper.readValue("[12,232]", intListRef);

        JSONObject json = JSONObject.parseObject("");

        json.get("");

        System.out.println(list);
    }
}
