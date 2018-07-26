package cn.luoxx.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <br>创建日期：2017年2月9日
 * <br><b>Copyright 2017 luoxiaoxiao All Rights Reserved</b>
 *
 * @author luoxx
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ParamTypeMappingConfig {

    @Bean
    public Converter<String, Date> convertDate() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = (Date) sdf.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, Timestamp> convertTimestamp() {
        return new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String source) {
                return Timestamp.valueOf(source);
            }
        };
    }
}
