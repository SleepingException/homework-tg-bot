package ru.StanokBot.DTO;

import java.sql.Time;

public class Subject {

    private int id;
    private String name;
    private Time time;
    private String info;
    private String homework;
    private String day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return name + '\n' +
                "Время:" + time + '\n' +
                "Домашка: " + homework + '\n' +
                "Прочая инфа: " + info;
    }
}
