package ru.StanokBot.DTO;

import java.sql.Date;
public class Day {

    private String name;
    private Date date;

    public Day(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
