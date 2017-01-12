package com.common.utils;

import android.text.TextUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:   String相关工具类
 * @author: Leo
 * @date:   2016/09/26
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("error...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 字符串截取
     *
     * @param input 待截取字符串
     * @param index 截取起始位置 <1为起始位置>
     * @param count 截取位数
     * @return 截取后字符串
     */
    public static String StringCut(String input, int index, int count) {
        if (input.isEmpty()) {
            return "";
        }
        count = (count > input.length() - index + 1) ? input.length() - index + 1 : count;

        return input.substring(index - 1, index + count - 1);
    }

    /**
     * 手机号码部分隐藏
     *
     * @param phone 完整手机号
     * @retrue 局部隐藏后的手机号
     */
    public static String encodePhone(String phone) {
        String result = "";
        if (phone.length() == 11) {
            String before = phone.substring(0, 3);
            String after = phone.substring(7, phone.length());
            result = before + "****" + after;
        }else{
            result = phone;
        }
        return result;
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isNotNull(String string) {
        if (string != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个字符串是否为空
     */
    public static boolean isNotNull(String string, String string2) {
        return isNotNull(string) && isNotNull(string2);
    }

    /**
     * 判断字符串是否为空和是否等于""
     */
    public static boolean isNotNull2(String string) {
        if (string != null && !string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  判断字符串是否为空和是否等于""和"null"
     */
    public static boolean isNotNullAndOther(String string, String string2) {
        if (isNotNull(string, string2) && !string.equals(string2)) {
            //不为空且不与string2相等时返回true
            return true;
        } else {
            return false;
        }
    }

    /**
     *  判断字符串是否为空和是否等于""和"null"
     */
    public static boolean isNotNullAndEqual(String string, String string2) {
        if (isNotNull(string, string2) && string.equals(string2)) {
            //不为空且与string2相等时返回true
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串为数字
     */
    public static boolean isNumber(String number) {
        if (TextUtils.isEmpty(number))
            return false;
        else {
            Pattern p = Pattern.compile("[0-9]*");
            Matcher m = p.matcher(number);
            if (m.matches())
                return true;
            else
                return false;
        }
    }

    /**
     * 带小数的数字
     */
    public static boolean isDecimal(String number) {
        if (TextUtils.isEmpty(number))
            return false;
        else {
            Pattern p = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
            Matcher m = p.matcher(number);
            if (m.matches())
                return true;
            else
                return false;
        }
    }

    /**
     * 字符串为字母
     */
    public static boolean isLetter(String letter) {
        if (TextUtils.isEmpty(letter))
            return false;
        else
            return letter.matches("^[a-zA-Z]*");
    }

    /**
     * 字符串是否含有汉字汉字
     */
    public static boolean hasChinese(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        else {
            String regEx = "[\u4e00-\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if (m.find())
                return true;
            else
                return false;
        }
    }

    /**
     * 判断数字是奇数还是偶数
     */
    public static int isEvenNumbers(String even) {
        if (!TextUtils.isEmpty(even) && isNumber(even)) {
            int i = Integer.parseInt(even);
            if (i % 2 == 0) {
                //偶数
                return 2;
            } else {
                //奇数
                return 1;
            }
        } else {
            //不是奇数也不是偶数
            return 0;
        }
    }

    /**
     * 判断字符串是否字母开头
     */
    public static boolean isLetterBegin(String s) {
        if (TextUtils.isEmpty(s))
            return false;
        else {
            char c = s.charAt(0);
            int i = (int) c;
            if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 判断字符串是否以指定内容开头
     */
    public static boolean startWithMytext(String mytext, String begin) {
        if (TextUtils.isEmpty(mytext) && TextUtils.isEmpty(begin))
            return false;
        else {
            if (mytext.startsWith(begin))
                return true;
            else
                return false;
        }
    }

    /**
     * 判断字符串是否以指定内容结尾
     */
    public static boolean endWithMytext(String mytext, String end) {
        if (TextUtils.isEmpty(mytext) && TextUtils.isEmpty(end))
            return false;
        else {
            if (mytext.endsWith(end))
                return true;
            else
                return false;
        }
    }

    /**
     * 判断字符串中是否含有指定内容
     * @param string  完整字符串
     * @param mytext  关键字
     * @return <true 含有   false 不含>
     */
    public static boolean hasMytext(String string, String mytext) {
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(mytext))
            return false;
        else {
            if (string.contains(mytext))
                return true;
            else
                return false;
        }
    }

    /**
     * 获得前一个字符串
     * @param string
     * @param split 通过":"截取
     * @return
     */
    public static String cutPreString(String string, String split) {
        String pre = "";
        if (!EmptyUtils.isEmpty(string)) {
            pre = string.substring(0, string.indexOf(split));
        }
        return pre;
    }

    /**
     * 获得后一个字符串
     * @param string
     * @param split 通过":"截取
     * @return
     */
    public static String cutNextString(String string, String split) {
        String pre = "";
        if (!EmptyUtils.isEmpty(string)) {
            pre = string.substring(string.indexOf(split) + 1, string.length());
        }
        return pre;
    }

    public static Boolean isHttpPic(String input) {
        if (input != null && !"".equals(input) && input.length() > 3) {
            String content = StringCut(input, 1, 4);

            if ("http".equals(content))
                return true;
        }
        return false;
    }

    /**
     * 格式化空字符串
     * @param content
     * @return
     */
    public static String nullToStr(String content) {
        if (null == content || "".equals(content))
            return "";
        return content;
    }

    /**
     * 判断对象是否为空
     * @param object Bean或集合
     * @return
     */
    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        if (object instanceof String) {
            return isEmpty((String) object);
        }
        if (object instanceof Collection) {
            return isListEmpty((Collection) object);
        }
        return false;
    }

    public static boolean isListEmpty(Collection list) {
        return list.isEmpty() || list.size() == 0;
    }
}
