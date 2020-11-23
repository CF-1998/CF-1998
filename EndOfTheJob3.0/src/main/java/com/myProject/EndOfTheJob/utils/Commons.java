package com.myProject.EndOfTheJob.utils;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 页面数据展示封装类
 */
 @Component
public class Commons {
    /**
     * 网站链接
     *
     * @return
     */
    public static String site_url() {
        return site_url("/page/1");
    }
    /**
     * 返回网站链接下的全址
     *
     * @param sub 后面追加的地址
     * @return
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站配置项
     *
     * @param key
     * @return
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param key
     * @param defalutValue 默认值
     * @return
     */
    public static String site_option(String key, String defalutValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return defalutValue;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 返回日期
     *
     * @return
     */
     public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
     }

    /**
     * 返回文章链接地址
     *
     * @param aid
     * @return
     */
    public static String permalink(Integer id) {
        return site_url("/todetails/" + id.toString());
    }

    /**
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }

}
