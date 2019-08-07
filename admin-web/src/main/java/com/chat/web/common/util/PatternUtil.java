package com.chat.web.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: java正则验证
 * @Author: franky
 * @CreateDate: 2019-07-01 14:58
 * @Version: 1.0
 */
public class PatternUtil {

    /**
     * 验证邮箱
     *
     * @param mail   需要验证的邮箱
     * @param length 需要验证的长度
     * @return
     */
    public static boolean mailPattern(String mail, int length) {
        if (RStringUtils.isEmpty(mail)) {
            return false;
        }
        String pattern = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(mail);
        if (mail.length() > length || !m.find()) {
            return false;
        }
        return true;
    }

}
