package cc.codehub.newkit.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Response extends LinkedHashMap<String, Object> {

    public Response() {
    }

    /**
     * 构造一个Response。
     *
     *
     * @param attributeName，属性名
     * @param attributeValue，属性值
     */
    public Response(String attributeName, Object attributeValue) {
        this.addAttribute(attributeName, attributeValue);
    }

    /**
     * 增加一个属性。
     * 存在则替换。
     *
     * @param attributeName，属性名
     * @param attributeValue，属性值
     * @return 用于增加属性的对象
     */
    public Response addAttribute(String attributeName, Object attributeValue) {
        Assert.notNull(attributeName, "Model attribute name must not be null");
        this.put(attributeName, attributeValue);
        return this;
    }

    /**
     * 获取一个属性值。
     *
     *
     * @param attributeName，属性名
     * @return 获取到的属性值，不存在则返回空
     */
    public Object getAttribute(String attributeName) {
        return this.get(attributeName);
    }

    /**
     * 增加一个属性集合。
     * 存在则替换。
     *
     * @param attributes，属性集合
     *
     * @return 用于增加属性的对象
     */
    public Response addAllAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            this.putAll(attributes);
        }

        return this;
    }

    /**
     * 合并一个属性集合。
     * 存在不替换。
     *
     * @param attributes，属性集合
     *
     * @return 用于增加属性的对象
     */
    public Response mergeAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            Iterator var2 = attributes.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry entry = (Map.Entry) var2.next();
                String key = (String) entry.getKey();
                if (!this.containsKey(key)) {
                    this.put(key, entry.getValue());
                }
            }
        }

        return this;
    }

    /**
     * 是否存在指定的属性名。
     *
     *
     * @param attributeName，属性名
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean containsAttribute(String attributeName) {
        return this.containsKey(attributeName);
    }

    /**
     * 删除一个属性。
     *
     *
     * @param attributeName，属性名
     *
     * @return 存在返回被删除掉的属性值，不存在返回空
     */
    public Object removeAttribute(String attributeName) {
        return this.remove(attributeName);
    }

    /**
     * 转换为Json字符串。
     *
     *
     * @return Json字符串，如果转换出错则返回空
     */
    public String toJsonString() {
        String json = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
    
    /**
     * Json字符串转Map
     * @param jsonStr Json字符串
     * @return Map
     * @throws IOException
     */
    public static Map<String, Object> parseMap(String jsonStr) throws IOException { 
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(jsonStr, Map.class);  
        return map;
    }  


    /**
     * 从一个对象转换。
     * 默认忽略空的属性值。
     *
     * @param obj，要转换的对象
     *
     * @return 转换后的Response对象
     */
    public static Response fromObject(Object obj) {
        return fromObject(obj, true);
    }

    /**
     * 从一个对象转换。
     *
     * @param obj，要转换的对象
     * @param ignoreNullValue, 是否忽略空的属性值
     *
     * @return 转换后的Response对象
     */
    public static Response fromObject(Object obj, boolean ignoreNullValue) {
        return fromObject(obj, ignoreNullValue, null);
    }

    /**
     * 从一个对象转换。
     *
     * @param obj，要转换的对象
     * @param ignoreNullValue, 是否忽略空的属性值
     * @param ignoreFields，要忽略的属性名集合
     *
     * @return 转换后的Response对象
     */
    public static Response fromObject(Object obj, boolean ignoreNullValue, List<String> ignoreFields) {
        final List<Field> allFields = FieldUtils.getAllFieldsList(obj.getClass());

        Response response = new Response();

        for (Field field : allFields) {
            String fieldName = field.getName();
            try {
                Method method = obj.getClass().getMethod(
                        "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                Object resultObj = method.invoke(obj);

                if ((!ignoreNullValue || resultObj != null) && (ignoreFields == null || ignoreFields.contains(fieldName))) {
                    response.addAttribute(fieldName, resultObj);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

}

