package ru.StanokBot.Api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BotService {
    void buildDaysKeyboard(SendMessage msg);
    void buildSubjectsKeyboard(String dayName, SendMessage msg);
    void getSubjectData(int id, SendMessage msg);
}
