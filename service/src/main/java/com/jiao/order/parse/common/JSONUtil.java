package com.jiao.order.parse.common;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.List;

public final class JSONUtil {
    public static final SerializerFeature[] EMPTY_FEATURES = new SerializerFeature[0];
    public static final SerializeFilter[] EMPTY_SERIALIZE_FILTERS = new SerializeFilter[0];

    private JSONUtil() {
    }

    public static final String toJSONString(Object object, List<SerializeFilter> filters, SerializerFeature... features) {
        return toJSONString(object, (SerializeConfig)null, filters, features);
    }

    public static final String toJSONString(Object object, SerializeConfig config, List<SerializeFilter> filters, SerializerFeature... features) {
        if (config == null) {
            config = SerializeConfig.getGlobalInstance();
        }

        SerializeFilter[] filtersArray = null;
        if (filters == null) {
            filtersArray = EMPTY_SERIALIZE_FILTERS;
        } else {
            filtersArray = new SerializeFilter[filters.size()];
            filtersArray = (SerializeFilter[])filters.toArray(filtersArray);
        }

        if (features == null) {
            features = EMPTY_FEATURES;
        }

        return JSON.toJSONString(object, config, filtersArray, features);
    }
}

