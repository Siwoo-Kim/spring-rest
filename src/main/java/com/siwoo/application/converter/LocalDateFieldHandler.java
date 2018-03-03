package com.siwoo.application.converter;

import lombok.extern.java.Log;
import org.apache.http.ParseException;
import org.apache.http.util.Asserts;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Log
public class LocalDateFieldHandler extends GeneralizedFieldHandler{
    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatPattern);
    }

    @Override
    public Object convertUponGet(Object value) {
        LocalDate localDate = (LocalDate) value;
        return format(localDate);
    }


    @Override
    public Object convertUponSet(Object str) {
        String dateTimeString = (String) str;
        return parse(dateTimeString);
    }


    @Override
    public Class getFieldType() {
        return LocalDate.class;
    }

    private static DateTimeFormatter dateTimeFormatter;

    protected Object parse(String str) {
        if(StringUtils.hasText(str)) {
            return LocalDate.parse(str, dateTimeFormatter);
        }
        log.warning("Not a valida date" + str);
        return null;
    }

    protected Object format(LocalDate localDate) {
        String dateTimeString = "";
        if(!ObjectUtils.isEmpty(localDate)){
            dateTimeString = localDate.format(dateTimeFormatter);
        }
        return dateTimeString;
    }
}
