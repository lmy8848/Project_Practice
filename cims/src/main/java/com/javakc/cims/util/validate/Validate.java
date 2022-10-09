package com.javakc.cims.util.validate;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 16:41
 * @description: [封装常用校验规则]
 */
public class Validate {

    /**
     * 判断对象是否为null
     *
     * @param object 对象
     * @return 判断结果
     * @author zhou
     */
    public static boolean isNotEmpty(Object object) {
        return null != object && !"".equals(object);
    }

    /**
     * 判断对象是否为null
     *
     * @param string 字符串
     * @return 判断结果
     * @author zhou
     */
    public static boolean isNotEmpty(String string) {
        return null != string && !"".equals(string) && string.trim().length() > 0;
    }

    /**
     * 判断数组是否为null
     *
     * @param array 数组
     * @return 判断结果
     * @author zhou
     */
    public static boolean isNotEmpty(String[] array) {
        return null != array && array.length > 0;
    }

    /**
     * 判断Collection集合是否为null
     *
     * @param collection 集合
     * @return 判断结果
     * @author zhou
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断Map集合是否为null
     *
     * @param map 集合
     * @return 判断结果
     * @author zhou
     */
    public static boolean isNotEmpty(Map<String, Object> map) {
        return map != null && map.size() > 0;
    }

    /**
     * 匹配正整数
     *
     * @param string 字符串数字
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isInteger(String string) {
        return match(string, "^[+]?\\d+$");
    }

    /**
     * 匹配正浮点
     *
     * @param string 字符串数字
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isFloat(String string) {
        return match(string, "^[-\\+]?\\d+(\\.\\d+)?$");
    }

    /**
     * 匹配座机
     *
     * @param telPhone 座机号码
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isTelPhone(String telPhone) {
        return match(telPhone, "^(\\d{3,4}-?)?\\d{7,9}$");
    }

    /**
     * 匹配手机号
     *
     * @param phone 手机号码
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isPhone(String phone) {
        if (phone.length() != 11) return false;
        return match(phone, "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
    }

    /**
     * 匹配日期格式
     *
     * @param date yyyy-MM-dd 日期格式
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isDate(String date) {
        String reg = "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        return match(date, reg);
    }

    /**
     * 匹配日期格式
     *
     * @param dateTime yyyy-MM-dd HH:mm:ss 日期格式
     * @return 匹配结果
     * @author zhou
     */
    public static boolean isDateTime(String dateTime) {
        String reg = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))";
        return match(dateTime, reg);
    }

    /**
     * 正则表达式匹配项
     *
     * @param text 匹配内容
     * @param reg  正则表达式
     * @return 匹配结果
     */
    public static boolean match(String text, String reg) {
        if (isNotEmpty(text) && isNotEmpty(reg)) {
            return Pattern.compile(reg).matcher(text).matches();
        }
        return false;
    }

}
