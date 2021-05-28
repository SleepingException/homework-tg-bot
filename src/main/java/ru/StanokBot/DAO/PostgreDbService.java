package ru.StanokBot.DAO;

import ru.StanokBot.Api.DBService;
import ru.StanokBot.DTO.Day;
import ru.StanokBot.DTO.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgreDbService implements DBService {

    static final String URL = "jdbc:postgresql://127.0.0.1:5432/TgBot";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";
    private Connection connection = null;
    private final Mapper mapper;
    public PostgreDbService(){
        this.mapper = new Mapper();

        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    @Override
    public List<Day> getDays(){
        List<Day> res = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from \"public\".days order by id asc"
            );
            res = mapper.mapDays(statement.executeQuery());
        }
    catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Subject> getSubjects(String dayName){
        List<Subject> res = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from \"public\".subjects where day_name = ?"
            );
            statement.setString(1, dayName);
            res = mapper.mapSubjects(statement.executeQuery());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String getSubjectData(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from \"public\".subjects where id = ?"
            );
            statement.setInt(1, id);
            return mapper.mapSubject(statement.executeQuery()).toString();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "Error =)";
    }

    @Override
    public void updateSubject(Subject dto) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "update \"public\".subjects " +
                            "set info = ? , homework = ?, time = ?"+
                            "where name = ?"
            );
            statement.setString(1, dto.getInfo());
            statement.setString(2, dto.getHomework());
            statement.setTime(3, dto.getTime());
            statement.setString(4, dto.getName());
            statement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
