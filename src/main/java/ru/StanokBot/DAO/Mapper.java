package ru.StanokBot.DAO;

import ru.StanokBot.DTO.Day;
import ru.StanokBot.DTO.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public Mapper(){}

    public List<Day> mapDays(ResultSet data) throws SQLException {
        List<Day> result = new ArrayList<>();
        while (data.next()){
            Day d = new Day();
            d.setName(data.getString("name"));
            d.setDate(data.getDate("date"));
            result.add(d);
        }
        return result;
    }
    public Subject mapSubject(ResultSet data) throws SQLException {
        data.next();
        Subject s = new Subject();
        s.setId(data.getInt("id"));
        s.setTime(data.getTime("time"));
        s.setInfo(data.getString("info"));
        s.setHomework(data.getString("homework"));
        s.setDay(data.getString("day_name"));
        s.setName(data.getString("name"));
        return s;
    }
    public List<Subject> mapSubjects(ResultSet data) throws SQLException {
        List<Subject> result = new ArrayList<>();
        while (data.next()){
            Subject s = new Subject();
            s.setId(data.getInt("id"));
            s.setTime(data.getTime("time"));
            s.setInfo(data.getString("info"));
            s.setHomework(data.getString("homework"));
            s.setDay(data.getString("day_name"));
            s.setName(data.getString("name"));
            result.add(s);
        }
        return result;
    }

}
