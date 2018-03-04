package com.siwoo.application.converter;

import lombok.extern.java.Log;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Log
public class LocalDateHandler extends GeneralizedFieldHandler {

    private static String DEFAULT_PATTERN = "yyyy-MM-dd";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        String pattern = config.getProperty("date-parse");
        if(pattern != null || StringUtils.hasText(pattern)){
            try {
                dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            } catch (Exception e) {
                log.warning("Error to parse parse: set default pattern: "+DEFAULT_PATTERN);
                dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
            }
        }

    }

    @Override
    public Object convertUponGet(Object value) {
        return  parse((LocalDate)value);
    }

    private String parse(LocalDate value) {
        return value.format(dateTimeFormatter);
    }

    @Override
    public Object convertUponSet(Object string) {
        String dateString = (String) string;
        return LocalDate.parse(dateString,dateTimeFormatter);
    }

    @Override
    public Class getFieldType() {
        return LocalDate.class;
    }
}
