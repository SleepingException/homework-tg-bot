package ru.StanokBot.Service;

import ru.StanokBot.DTO.Subject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Time;

public class Parser {
    public Parser(){}

    public Subject parse(String text){
        Subject dto = new Subject();
        parseName(text, dto);
        parseInfo(text, dto);
        parseTime(text, dto);
        parseHomework(text, dto);
        return dto;
    }

    public void parseName(String text, Subject dto) {
        Matcher matcher = createRegex("#.*?\\s", text);
        if (matcher != null) {
            dto.setName(text.substring(matcher.start() + 1, matcher.end() - 1));
        }
        else {
            dto.setName(null);
        }
    }
    private void parseInfo(String text, Subject dto) {
        Matcher matcher = createRegex("https:.*zoom.*\\b", text);
        if (matcher != null) {
            dto.setInfo(text.substring(matcher.start(), matcher.end()));
        }
        else {
            dto.setInfo(null);
        }
    }
    private void parseTime(String text, Subject dto){
        Matcher matcher = createRegex("\\d{2}:\\d{2}", text);
        if(matcher != null) {
            dto.setTime(Time.valueOf(text.substring(matcher.start(), matcher.end())+":00"));
        }
        else {
            dto.setTime(null);
        }
    }
    private void parseHomework(String text, Subject dto){
        Matcher matcher = createRegex("дз.*\\b|домашка.*\\b", text);
        if(matcher != null) {
            String str = text.substring(matcher.start(), matcher.end());
            str = str.substring(str.indexOf(':') + 2);
            dto.setHomework(str);
        }
        else
            dto.setHomework(null);
    }

    private Matcher createRegex(String regex, String text){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){return matcher;}
        return null;
    }

}
