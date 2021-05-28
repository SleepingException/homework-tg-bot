package ru.StanokBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.StanokBot.Api.BotService;
import ru.StanokBot.Service.BotServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    private final BotService botService;
    private String botUsername;
    private String botToken;

    private final List<String> week = new ArrayList<>(Arrays.asList(
            "пн", "вт","ср","чт","пт","сб"
    ));

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    public Bot(String name, String token) {
        super();
        botUsername = name;
        botToken = token;
        botService = new BotServiceImpl();
    }

    public void onUpdateReceived(Update update) {
         if (update.hasCallbackQuery()){
            try {
                SendMessage msg = new SendMessage();
                msg.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
                if (week.contains(update.getCallbackQuery().getData())) {
                    botService.buildSubjectsKeyboard(update.getCallbackQuery().getData(), msg);
                    execute(msg);
                }
                else{
                    botService.getSubjectData(Integer.parseInt(update.getCallbackQuery().getData()), msg);
                    execute(msg);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
         else if (update.getMessage().hasText()) {
            String msgText = update.getMessage().getText();
            if (msgText.equals("/week")) {
                try {
                    SendMessage msg = new SendMessage();
                    msg.setChatId(String.valueOf(update.getMessage().getChatId()));
                    botService.buildDaysKeyboard(msg);
                    execute(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                botService.updateSubject(update.getMessage().getText());
            }
        }
    }
}
