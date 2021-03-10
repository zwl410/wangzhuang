package com.zhang.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XaUtil {

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "" || pObj == "null") {
            return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        }
        if (pObj == "") {
            return false;
        }
        if ("null".equalsIgnoreCase(pObj + ""))
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化Float 保留小数
     *
     * @param obj
     * @param scale 要保留小数的位数
     * @return
     */
    public static Float formatFloat(Object obj, int scale) {
        if (XaUtil.isNotEmpty(obj)) {
            BigDecimal money = new BigDecimal(String.valueOf(obj));
            return money.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
        } else {
            return 0F;
        }
    }

    public static int getNutNullInt(Object object, int defaultValue) {
        if (isEmpty(object)) {
            return defaultValue;
        }
        return Integer.parseInt(object.toString());
    }

    public static String getNutNullStr(Object o, String defaultValue) {
        if (isEmpty(o)) {
            return defaultValue;
        }
        return o.toString();
    }

    public static Long getNutNullLong(Object o, Long defaultValue) {
        if (isEmpty(o)) {
            return defaultValue;
        }
        return Long.parseLong(o.toString());
    }

    public static double getNutNullDouble(Object o, double defaultValue) {
        if (isEmpty(o)) {
            return defaultValue;
        }
        return Double.parseDouble(o.toString());
    }

    public static String getNutNullStr(Object o) {
        return getNutNullStr(o, "");
    }

    /**
     * 格式化金额.
     *
     * @param value the value
     * @return the string
     */
    public static String formatCurrency2String(Long value) {
        if (value == null || "0".equals(String.valueOf(value))) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value / 100.00);
    }

    /**
     * 判断字符串是不是数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是不是数字 (包含小数 负数)
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[\\+\\-]?[\\d]+(\\.[\\d]+)?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 格式化金额.
     *
     * @param priceFormat the price format
     * @return the long
     */
    public static Long formatCurrency2Long(String priceFormat) {
        BigDecimal bg = new BigDecimal(priceFormat);
        Long price = bg.multiply(new BigDecimal(100)).longValue();
        return price;
    }

    /**
     * 去除html标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        return htmlStr.trim(); //返回文本字符串 
    }


    /**
     * 将xml字符串转化为实体对象
     *
     * @param <T>
     * @param xml
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2bean(String xml, Class<T> clazz) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return t;
    }


    /**
     * 生成一定规则的token，32位随机字符串
     * 规则1：生成的字符串中字符位置遇3的倍数时
     *
     * @return
     */
    public static String generateToken() {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        //对生成的随机字符串做处理符合一定的规则

        return "";
    }

    /**
     * 防止sql注入
     * System.out.print("防SQL注入:"+StringEscapeUtils.escapeSql("1' or '1'='1"));;
     *
     * @param str
     * @return
     */
    public static String Sql(String str) {
        return " '" + StringUtils.replace(str, "'", "''") + "' ";
    }


    /**
     * 形如：/work/android-project/hangzhuProject/OA/OA-project/safefy/target/safety/
     */
    public static String getWebRootPath(HttpServletRequest request) {
        String root = request.getSession(true).getServletContext().getRealPath("/");
        return root;
    }

    /**
     * /work/android-project/hangzhuProject/OA/OA-project/safetyUpload
     * 获取tomcat根目录下面的存放upload文件的地址;
     * 避免
     * <p>
     * docBase可以是相对目录，相对目录是相对appBase
     * <Context path="/safety/upload/" docBase="../portalUploadRoot/upload"></Context>
     * <Context path="/safety/ueditor/" docBase="../portalUploadRoot/ueditor"></Context>
     * <p>
     * tomcat/upload
     */
    public static String getWebUploadRootPath(HttpServletRequest request) {
        //return getWebRootPath(request);
        String root = getWebRootPath(request);
        String parentRoot = new File(root).getParentFile().getParent();
        String uploadRoot = parentRoot + "/ruiqinUploadRoot/";
        try {
            new File(uploadRoot).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("======创建upload目录失败!!=====");
        }
        return uploadRoot;
    }

    /**
     * 合并重复的map 键
     *
     * @param list
     * @return
     */
    public static Map mapCombine(List<Map> list) {
        Map<Object, List> map = new HashMap<>();
        for (Map m : list) {
            Iterator<Object> it = m.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (!map.containsKey(key)) {
                    List newList = new ArrayList<>();
                    newList.add(m.get(key));
                    map.put(key, newList);
                } else {
                    map.get(key).add(m.get(key));
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        //System.out.println(Sql("1' or '1'='1"));
//		String ss = "{\"status\":1,\"result\":\"修改资源成功\"}";
//		System.out.println(StringEscapeUtils.unescapeJavaScript(ss));
//        System.out.println(toUppercase("123123.12"));

    }
}