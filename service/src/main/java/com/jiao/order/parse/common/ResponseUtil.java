package com.jiao.order.parse.common;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class.getName());
//    public static final String REP_KEY_SUCCESS = "success";
//    public static final String REP_KEY_CODE = "code";
//    public static final String REP_KEY_MSG = "msg";
//    public static final String REP_KEY_DATA = "data";
//    public static final String X_CACHE_CONTROL = "X-Cache-Control";

    public ResponseUtil() {
    }

    public static boolean writeJSONResult(HttpServletResponse httpResponse, WebResult WebResult, List<SerializeFilter> filters) {
        return writeJSONResult(httpResponse, WebResult, (SerializeConfig)null, filters);
    }

    public static boolean writeJSONResult(HttpServletResponse httpResponse, WebResult WebResult) {
        return writeJSONResult(httpResponse, WebResult, (SerializeConfig)null, (List)null);
    }

    public static boolean writeJSONResult(HttpServletResponse httpResponse, WebResult WebResult, SerializeConfig config, List<SerializeFilter> filters) {
        Preconditions.checkNotNull(httpResponse);
        String json = result2Json(WebResult, config, filters);
        return writeJsonResult(httpResponse, json);
    }

    public static String result2Json(WebResult responseResult, SerializeConfig config, List<SerializeFilter> filters) {
        Map<String, Object> map = createResponseMap(responseResult);
        return JSONUtil.toJSONString(map, config, filters, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
    }

    public static Map<String, Object> createResponseMap(WebResult WebResult) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", WebResult.isSuccess());
        map.put("code", WebResult.getCode());
        map.put("msg", WebResult.getMsg());
        map.put("data", WebResult.getData());
        return map;
    }

    public static Map<String, Object> createResponseMap(boolean success, int code, Object data, String msg) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", success);
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

    public static boolean writeJsonResult(HttpServletResponse response, String json) {
        try {
            String xCacheControl = response.getHeader("X-Cache-Control");
            if (xCacheControl == null) {
                response.setHeader("Cache-Control", "no-cache");
            } else if (!xCacheControl.isEmpty()) {
                response.setHeader("Cache-Control", xCacheControl);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.flush();
            return true;
        } catch (Exception var4) {
            LOGGER.error("writer to response exception.", var4);
            return false;
        }
    }

    public static boolean writeByteResult(HttpServletResponse response, byte[] data) {
        try {
            String xCacheControl = response.getHeader("X-Cache-Control");
            if (xCacheControl == null) {
                response.setHeader("Cache-Control", "no-cache");
            } else if (!xCacheControl.isEmpty()) {
                response.setHeader("Cache-Control", xCacheControl);
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            ServletOutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            return true;
        } catch (Exception var4) {
            LOGGER.error("writer to response exception.", var4);
            return false;
        }
    }
}
