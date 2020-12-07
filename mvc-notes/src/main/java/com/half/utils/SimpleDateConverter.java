package com.half.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/7 22:01
 * @Version 1.0
 * @Description
 */
public class SimpleDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
             df = new SimpleDateFormat("yyyy/MM/dd");
            try {
                return df.parse(source);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                throw new RuntimeException("转换失败");
            }

        }
    }
}
