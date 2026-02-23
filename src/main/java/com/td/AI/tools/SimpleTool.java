package com.td.AI.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.util.Locale;

public class SimpleTool {

    @Tool
    public String getCurrentDate(){
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
