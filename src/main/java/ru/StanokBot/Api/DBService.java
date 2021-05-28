package ru.StanokBot.Api;

import ru.StanokBot.DTO.Day;
import ru.StanokBot.DTO.Subject;

import java.util.List;

public interface DBService {
    List<Day> getDays();
    List<Subject> getSubjects(String dayName);
    String getSubjectData(int id);
    void updateSubject(Subject dto);
}
