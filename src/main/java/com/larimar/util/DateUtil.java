package com.larimar.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Larimar
 * @time 2019/8/23 周五 10:09
 */
public class DateUtil {
    /**
     * 把当前时间转换为字符串形式的时间
     * @return
     */
    public static String localDateToString(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    };
}
