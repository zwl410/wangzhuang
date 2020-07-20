package com.zhang.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 序列化 对象转  json 字符串
     */
    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    /**
     * 反序列化  json字符串转对象
     */
    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    public static <T> T parse(Object object, Class<T> tClass) {
        try {
            return mapper.readValue(serialize(object), tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + serialize(object), e);
            return null;
        }
    }

    /**
     * json 转list
     */
    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * json转map
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * json转复杂对象
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错" + json, e);
            return null;
        }
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * 没有条件: Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
     * 一个条件: Map<String, Object> filterParams   = WebUitl.getParametersStartingWith("{'search_EQ_userId':'" + userId + "'}", "search_");
     * 2个以及以上个条件:
     *
     * @<code>Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
     * "{'search_EQ_vSDC_Id':'"+ JConstant.VSDCStates.PASS+"'" +
     * ",'search_NE_status':'"+ XaConstant.Status.delete+"'" +
     * ",'search_EQ_userId':'"+userId+"'}", "search_");</code>
     * <p>
     * WebUitl.getParametersStartingWith("{'search_IN_id':'" + JSON.toJSONString(shopIdSetXr.getObject()) + "'}", "search_");
     * <p>
     * 返回的结果的Parameter名已去除前缀.
     * <p>
     * 说明:用json转换已经可以防止注入'这种特殊字符
     * <p>
     * Map数据：
     * key:EQ_userId
     * value:888
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getParametersStartingWith(String jsonFilter, String prefix) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(jsonFilter) && StringUtils.isNotEmpty(prefix)) {
            if (jsonFilter.endsWith(",")) {
                jsonFilter = jsonFilter.substring(0, jsonFilter.length() - 1);
            }
            jsonFilter = "{" + jsonFilter + "}";
            Map<String, String> map = JsonUtils.parseMap(jsonFilter, String.class, String.class);
            for (String key : map.keySet()) {
                if (key.startsWith(prefix)) {
                    String unprefixed = key.substring(prefix.length());
                    Object value = map.get(key);
                    searchParams.put(unprefixed, value);
                }
            }
        }
        return searchParams;
    }
}