package com.chat.web.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作公用类
 * 
 * @author qing
 * @version 001
 */
public class RStringUtils {

	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	
	/**
	 * 字符串补零
	 * @param str
	 * @param len
	 * @return
	 */
	public static String leftPadZero(String str, int len){
		str = org.apache.commons.lang.StringUtils.leftPad(str, len, "0");
		return str;
	}
	
	/**
	 * 判断字符串是否包含搜索字符，比如搜索项 为：“中”， 搜索字符为“中国”，返回为true
	 * @param searchValue; 搜索项
	 * @param input；搜索字符
	 * @param caseSensitive;是否区分大小写
	 * @return
	 */
	public static boolean isMatched(String searchValue,String input,boolean caseSensitive){
		if(RStringUtils.isEmpty(input)) return false;
		if(!caseSensitive){
			//不区分的话，全部转为大写
			String searchValue1=searchValue.toUpperCase();
			String input1=input.toUpperCase();
			if(input1.indexOf(searchValue1)!=-1) {
				return true;
			}else{
				return false;
			}
		}else{
			if(input.indexOf(searchValue)!=-1) {
				return true;
			}else{
				return false;
			}
		}
		
	}
	public static boolean isMatched(String searchValue,String input){
		return isMatched( searchValue, input, false);
	}
	/**
	 * 固定长度格式化字符串s，不足处在左边补字符fillChar
	 * @param s
	 * @param length
	 * @param fillChar
	 * @return
	 */
	public static String format(String s, int length, char fillChar) {
		if (s == null) s = "";
		if (s.length() >= length) return s;
		StringBuilder sb = new StringBuilder(length - s.length());
		for (int i = 0; i < length - s.length(); i++) {
			sb.append(fillChar);
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * 判断一个CharSequence对象是否为整数
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isInt("123"), true);
	 *         assertEquals(HJStringUtils.isInt("+123"), true);
	 *         assertEquals(HJStringUtils.isInt("123.0"), false);
	 *         assertEquals(HJStringUtils.isInt("-123"), true);
	 *         assertEquals(HJStringUtils.isInt("abc"), false);
	 *         assertEquals(HJStringUtils.isInt("0"), true);
	 */
	public static boolean isInt(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[-+]?[1-9]\\d*|0$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为正整数；
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isPostiveInt("123"), true);
	 *         assertEquals(HJStringUtils.isPostiveInt("+123"), true);
	 *         assertEquals(HJStringUtils.isPostiveInt("123.0"), false);
	 *         assertEquals(HJStringUtils.isPostiveInt("-123"), false);
	 *         assertEquals(HJStringUtils.isPostiveInt("abc"), false);
	 *         assertEquals(HJStringUtils.isPostiveInt("0"), false);
	 */
	public static boolean isPostiveInt(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[+]?[1-9]\\d*$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为负整数；
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isNegativeInt("-123"), true);
	 *         assertEquals(HJStringUtils.isNegativeInt("-0.123"), false);
	 *         assertEquals(HJStringUtils.isNegativeInt("-123.0"), false);
	 *         assertEquals(HJStringUtils.isNegativeInt("123.123"), false);
	 *         assertEquals(HJStringUtils.isNegativeInt("abc"), false);
	 *         assertEquals(HJStringUtils.isNegativeInt("0"), false);
	 */
	public static boolean isNegativeInt(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^-[1-9]\\d*$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为非负整数（正整数和0）
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isNotNegativeInt("123"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeInt("+123"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeInt("123.0"), false);
	 *         assertEquals(HJStringUtils.isNotNegativeInt("-123"), false);
	 *         assertEquals(HJStringUtils.isNotNegativeInt("abc"), false);
	 *         assertEquals(HJStringUtils.isNotNegativeInt("0"), true);
	 */
	public static boolean isNotNegativeInt(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[+]?[1-9]\\d*|0$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为非正整数（负整数和0）
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isNotPostiveInt("-123"), true);
	 *         assertEquals(HJStringUtils.isNotPostiveInt("-0.123"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveInt("-123.0"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveInt("123.123"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveInt("abc"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveInt("0"), true);
	 */
	public static boolean isNotPostiveInt(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^-[1-9]\\d*$|0")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为浮点数
	 * 
	 * @param cs
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isFloat("-123.456"), true);
	 *         assertEquals(HJStringUtils.isFloat("0.123"), true);
	 *         assertEquals(HJStringUtils.isFloat("+123.456"), true);
	 *         assertEquals(HJStringUtils.isFloat("123"), false);
	 *         assertEquals(HJStringUtils.isFloat("abc"), false);
	 *         assertEquals(HJStringUtils.isFloat("0"), true);
	 *         assertEquals(HJStringUtils.isFloat("0.0"), true);
	 */
	public static boolean isFloat(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches(
				"^[-+]?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)|0(.0+)?$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为正浮点数；
	 * 
	 * @param cs
	 *            ： CharSequence对象；
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isPostiveFloat("123.456"), true);
	 *         assertEquals(HJStringUtils.isPostiveFloat("0.123"), true);
	 *         assertEquals(HJStringUtils.isPostiveFloat("+123.456"), true);
	 *         assertEquals(HJStringUtils.isPostiveFloat("123"), false);
	 *         assertEquals(HJStringUtils.isPostiveFloat("abc"), false);
	 *         assertEquals(HJStringUtils.isPostiveFloat("0"), false);
	 */
	public static boolean isPostiveFloat(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString()
				.matches("^[+]?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为负浮点数；
	 * 
	 * @param cs
	 * @return 如果是，返回true，否则，返回false； test：
	 *         assertEquals(HJStringUtils.isNegativeFloat("-0.123"), true);
	 *         assertEquals(HJStringUtils.isNegativeFloat("-123.123"), true);
	 *         assertEquals(HJStringUtils.isNegativeFloat("-123"), false);
	 *         assertEquals(HJStringUtils.isNegativeFloat("123.456"), false);
	 *         assertEquals(HJStringUtils.isNegativeFloat("0.123"), false);
	 *         assertEquals(HJStringUtils.isNegativeFloat("0"), false);
	 *         assertEquals(HJStringUtils.isNegativeFloat("-abc"), false);
	 */
	public static boolean isNegativeFloat(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为非正浮点数（负浮点数和0）
	 * 
	 * @param cs
	 * @return 如果是，返回true，否则，返回false； test：
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("-0.123"), true);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("-123.123"), true);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("-123"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("123.456"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("0.123"), false);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("0"), true);
	 *         assertEquals(HJStringUtils.isNotPostiveFloat("0.0"), true);
	 */
	public static boolean isNotPostiveFloat(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches(
				"^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)|0(\\.0+)?$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为非负浮点数（正整数和0）
	 * 
	 * @param cs
	 * @return 如果是，返回true，否则，返回false； test：
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("123.456"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("0.123"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("+123.456"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("123"), false);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("abc"), false);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("0"), true);
	 *         assertEquals(HJStringUtils.isNotNegativeFloat("0.0"), true);
	 */
	public static boolean isNotNegativeFloat(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches(
				"^[+]?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)|0(\\.0+)?$")) {
			return false;
		}
		return true;
	}

	public static boolean isNotNegative(CharSequence cs) {
		return isNotNegativeFloat(cs) || isNotNegativeInt(cs);
	}

	/**
	 * 判断一个CharSequence对象是否为英文字符组成（不区分大小写）
	 * 
	 * @param cs
	 *            : CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isLetters("a"), true);
	 *         assertEquals(HJStringUtils.isLetters("abc"), true);
	 *         assertEquals(HJStringUtils.isLetters("c1a"), false);
	 *         assertEquals(HJStringUtils.isLetters("123"), false);
	 *         assertEquals(HJStringUtils.isLetters("a."), false);
	 */
	public static boolean isLetters(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[A-Za-z]+$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为英文字符或数字字符组成（不区分大小写）
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isLettersOrNumbers("a"), true);
	 *         assertEquals(HJStringUtils.isLettersOrNumbers("0a"), true);
	 *         assertEquals(HJStringUtils.isLettersOrNumbers("09"), true);
	 *         assertEquals(HJStringUtils.isLettersOrNumbers("0_a"), false);
	 *         assertEquals(HJStringUtils.isLettersOrNumbers("中文"), false);
	 */
	public static boolean isLettersOrNumbers(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[A-Za-z0-9]+$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为英文字母、数字或下划线组成
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isKeyWords("a"), true);
	 *         assertEquals(HJStringUtils.isKeyWords("0a"), true);
	 *         assertEquals(HJStringUtils.isKeyWords("09"), true);
	 *         assertEquals(HJStringUtils.isKeyWords("0_a"), true);
	 *         assertEquals(HJStringUtils.isKeyWords("中文"), false);
	 */
	public static boolean isKeyWords(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^\\w+$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象是否为中文字符串组成
	 * 
	 * @param cs
	 *            ：CharSequence对象
	 * @return 如果是，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isChineseWords("a"), false);
	 *         assertEquals(HJStringUtils.isChineseWords("0a"), false);
	 *         assertEquals(HJStringUtils.isChineseWords("09"), false);
	 *         assertEquals(HJStringUtils.isChineseWords("0_a"), false);
	 *         assertEquals(HJStringUtils.isChineseWords("中文"), true);
	 */
	public static boolean isChineseWords(CharSequence cs) {
		if (cs == null || cs.length() <= 0) {
			return false;
		}
		if (!cs.toString().matches("^[\u4e00-\u9fa5]+$")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个CharSequence对象长度范围是否合法 min、max分别表示字符串大于等于和小于等于的值
	 * 
	 * @param cs
	 *            : CharSequence对象
	 * @param min
	 *            : 最小值(大于等于0)
	 * @param max
	 *            ：最大值(小于等于0)
	 * @return 如果符合，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isLengthScope("", 0, 0), true);
	 *         assertEquals(HJStringUtils.isLengthScope("123", 1, 3), true);
	 *         assertEquals(HJStringUtils.isLengthScope("0", 1, 1), true);
	 *         assertEquals(HJStringUtils.isLengthScope("0_a", 4, 5), false);
	 *         assertEquals(HJStringUtils.isLengthScope("中文", 1, 2), true);
	 */
	public static boolean isLengthScope(CharSequence cs, int min, int max) {
		if (min < 0 || max < 0)
			return false;
		int length = 0;
		if (cs != null) {
			length = cs.length();
		}
		if (length > max || length < min)
			return false;
		return true;
	}
	
	public static boolean isNotLengthScope(CharSequence cs, int min, int max){
		return !isLengthScope(cs, min, max);
	}

	/**
	 * 判断一个CharSequence对象数组中元素长度范围是否合法 min、max分别表示字符串大于等于和小于等于的值
	 * 
	 * @param strings
	 *            : CharSequence对象
	 * @param min
	 *            : 最小值(大于等于0)
	 * @param max
	 *            ：最大值(小于等于0)
	 * @return 如果符合，返回true，否则，返回false； test:
	 *         assertEquals(HJStringUtils.isLengthScope(0, 0, "","1","23"),
	 *         false); assertEquals(HJStringUtils.isLengthScope(1, 2,
	 *         "","1","23"), false);
	 */
	public static boolean isLengthScope(int min, int max, String... strings) {
		if (min < 0 || max < 0 || min > max)
			return false;
		if (strings == null || strings.length <= 0) {
			if (min == 0)
				return true;
			return false;
		}
		for (String string : strings) {
			int length = 0;
			if (string != null) {
				length = string.length();
			}
			if (length > max || length < min)
				return false;
		}
		return true;
	}
	
	public static boolean isNotLengthScope(int min, int max, String...strings){
		return !isLengthScope(min, max, strings);
	}

	/**
	 * 将Map对象转换为字符串(删除了最后一个后缀)
	 * 
	 * @param map
	 *            ：Map对象
	 * @param prefix
	 *            ：前缀
	 * @param midfix
	 *            ：key值和value值之间的连接字符串
	 * @param suffix
	 *            ：后缀
	 * @return test: Map<String, Integer> normalMap = new HashMap<>();
	 *         normalMap.put("1", 1); normalMap.put("2", 2);
	 *         assertEquals(HJStringUtils.convertMapToString(normalMap, "", "=",
	 *         ","), "2=2,1=1");
	 *         assertEquals(HJStringUtils.convertMapToString(null, "", "=",
	 *         ","), "");
	 */
	public static <K, V> String convertMapToString(Map<K, V> map,
			String prefix, String midfix, String suffix) {
		if (map == null || map.isEmpty())
			return "";
		String pre = (prefix == null ? "" : prefix);
		String mid = (midfix == null ? "" : midfix);
		String suf = (suffix == null ? "" : suffix);
		StringBuffer sb = new StringBuffer("");
		for (K key : map.keySet()) {
			sb.append(pre);
			sb.append(key.toString());
			sb.append(mid);
			sb.append(map.get(key));
			sb.append(suf);
		}
		return removeLast(sb);
	}

	/**
	 * 去掉字符串的最后一个字符
	 * 
	 * @param cs
	 * @return
	 */
	public static String removeLast(CharSequence cs) {
		if (cs == null || cs.length() <= 0)
			return "";
		return cs.subSequence(0, cs.length() - 2).toString();
	}

	/**
	 * 验证是否为手机号码
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isPhoneNo(CharSequence cs) {
		return cs
				.toString()
				.matches(
						"((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
	}
	/**
	 * 验证是否为手机号码
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isMobileNo(CharSequence s) {
		Pattern p=Pattern.compile("^((13[0-9])|(15[0-9]|17[0-9]|18[0-9]))\\d{8}$");
		Matcher m=p.matcher(s);
		return m.matches();
	}
	/**
	 * 验证是否为Email地址
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isEmail(CharSequence cs) {
		return cs.toString().matches(
				"\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}

	/**
	 * 验证是否为（计量或计数等使用的）单位
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isUnit(CharSequence cs) {
		return cs.toString().matches("^[\u4e00-\u9fa5]{1,2}");
	}

	/**
	 * 转化String Object null为empty
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertNullToEmpty(String obj) {
		return obj == null ? "" : obj;
	}

	public static List<String> split(String s, String splitStr) {
		if (StringUtils.isEmpty(s))
			return new ArrayList<String>(0);

		String[] arr = StringUtils.split(s, splitStr);
		return Arrays.asList(arr);
	}

	public static String formatString(String input) {
		if (input == null || "null".equals(input)) {
			return "";
		} else {
			return input.trim();
		}
	}

	/**
	 * 如果字符串为null，则返回value值
	 * 
	 * @author Rockey 2013-10-11 9:54:57
	 * @param s
	 *            要判断的字符串
	 * @param value
	 *            如果判断的字符串是null，则返回该数据
	 * @return
	 */
	public static String nullValue(String s, String value) {
		return s == null ? value : s;
	}
	/**
	 * 如果字符串为空，则返回value值
	 * @param s
	 * @param value
	 * @return
	 */
	public static String emptyValue(String s, String value) {
		return StringUtils.isEmpty(s) ? value : s;
	}

	/**
	 * 如果字符串是否为null和empty，则返回boolean值
	 * 
	 * @author qing
	 * @param s 要判断的字符串
	 *            如果判断的字符串是null 或者empty，则返回该true ,否则为false
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return StringUtils.isEmpty(s);
	}

	/**
	 * 
	 * @param s
	 * @param regex
	 * @return
	 */
	public static String[] matches(String s, String regex) {
		if (StringUtils.isEmpty(s) ||
				StringUtils.isEmpty(regex)) return new String[] { s };
		Matcher matcher = Pattern.compile(regex).matcher(s);
		List<String> lst = new ArrayList<String>();
		while (matcher.find()) {
			String matchStr = matcher.group();
			lst.add(matchStr);
		}
		return lst.toArray(new String[0]);
	}
	/**
	 * 获取匹配的第一项
	 * @param s
	 * @param regex
	 * @return
	 */
	public static String matchFirst(String s, String regex) {
		if (StringUtils.isEmpty(s) ||
				StringUtils.isEmpty(regex)) return s;
		Matcher matcher = Pattern.compile(regex).matcher(s);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equals(String s1, String s2) {
		if (StringUtils.isEmpty(s1)) return StringUtils.isEmpty(s2);
		return s1.equals(s2);
	}
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean notEquals(String s1, String s2) {
		return !equals(s1, s2);
	}
	
	/**
	 * 移除小数点后结尾的0字符
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s){  
		if(s.indexOf(".") > 0){  
			s = s.replaceAll("0+?$", "");//去掉多余的0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }
	
	/**
	 * 比较字符串大小
	 * @param s1
	 * @param s2
	 * @param ignoreCase
	 * 是否忽略大小写
	 * @return
	 */
	public static int compare(String s1, String s2, boolean ignoreCase) {
		if (s1 == null) s1 = "";
		if (s2 == null) s2 = "";
//		if (s1 == null) {
//			return s2 == null ? 0 : -1;
//		}
//		if (s2 == null) return 1;
		if (ignoreCase) return s1.compareToIgnoreCase(s2);
		return s1.compareTo(s2);
	}
	/***
	 * 日期加上前置0；比如1->01
	 */
	public static String addPrefix0ForDate(int i){
		if(i<10) return "0"+i;
		return i+"";
		
	}
	 /**
     * 截取字符串
     *
     * @param str
     * @param start
     * @param end
     * @return String
     */ 
   public static String substring(String str, int start, int end) {  
	   if(str==null || "".equals(str)) return "";
	   int length=str.length();
	   if(end>length) end=length;
       return str.substring(start, end);  
    }

	/**
	 * 用于返回sql from后的字符串 返回会将字符大写
	 * @param mes
	 * @param frag
	 * @return
	 */
	public static String resolveMes(String  mes,String frag){
		mes = mes.toUpperCase();
		String [] meses = mes.split("\r|\n|\t| ");
		int num = 0;
		int findex = 0; //统计Form 出现的次数
		for(String str : meses){
			num += countstr(str,"(")-countstr(str,")");
			int fnum = 0;
			if(str.equals(frag)){
				fnum = 1;
			}
			findex += fnum;
			if(num == 0 && fnum > 0){
				break;
			}
		}
		return subIndexStr(mes,findex,frag);
	}

	/**
	 * 返回从第几个匹配的字符串后的字段
	 * @param source
	 * @param findex
	 * @param frag
	 * @return
	 */
	public static String subIndexStr(String source,int findex,String frag){
		int num = 0;
		int length = frag.length();
		for(int i = 0 ; i < findex ; i ++){
			if(num == 0){
				num = source.indexOf(frag,num);
			}else{
				num = source.indexOf(frag,num + length);
			}
		}
		return source.substring(num);
	}

	/**
	 * 计算匹配字符数 用于小于100字符个数
	 * @param source
	 * @param frag
	 * @return
	 */
	public static int countstr(String source,String frag){
		int slength=frag.length();
		int num = 0;
		int index = 0;
		int sum = 0;
		while(sum < 100){
			index = source.indexOf(frag,index);
			if(index >= 0){
				index = index+slength;
				num++;
			}else{
				break;
			}
		}
		return num;
	}

	/**驼峰转下划线,效率高*/
	public static String humpToLine(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
