import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.StanokBot.Bot;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi tgApi = new TelegramBotsApi(DefaultBotSession.class);
        tgApi.registerBot(new Bot("BuldaebBot", "1140935874:AAFnJTkVlxB1hMDbbkwoRM3NAVjkh0clSno"));
    }
}
