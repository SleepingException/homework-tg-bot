package ru.StanokBot.Service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.StanokBot.Api.BotService;
import ru.StanokBot.Api.DBService;
import ru.StanokBot.DAO.PostgreDbService;
import ru.StanokBot.DTO.Day;
import ru.StanokBot.DTO.Subject;

import java.util.ArrayList;
import java.util.List;

public class BotServiceImpl implements BotService {

    private DBService dbService;
    private Parser parser;
    public BotServiceImpl(){
        dbService = new PostgreDbService();
        parser = new Parser();
    }

    @Override
    public void buildDaysKeyboard(SendMessage msg) {
        msg.setText("Дни недели");
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<Day> days = dbService.getDays();
        for (int i = 0; i < days.size(); i++) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(days.get(i).getName());
            btn.setCallbackData(days.get(i).getName());
            row.add(btn);
        }
        buttons.add(row);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        msg.setReplyMarkup(markupKeyboard);
    }
    @Override
    public void buildSubjectsKeyboard(String dayName, SendMessage msg) {
        msg.setText("Пары");
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<Subject> subjects = dbService.getSubjects(dayName);
        for (int i = 0; i < subjects.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(subjects.get(i).getName());
            btn.setCallbackData(String.valueOf(subjects.get(i).getId()));
            row.add(btn);
            buttons.add(row);
        }
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        msg.setReplyMarkup(markupKeyboard);
    }
    @Override
    public void getSubjectData(int id, SendMessage msg) {
        msg.setText(dbService.getSubjectData(id));
    }

    @Override
    public void updateSubject(String text) {
        Subject subject = parser.parse(text);
        if (subject.getName() != null) {
            dbService.updateSubject(subject);
        }
        else return;
    }


}
