package uz.test.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.test.bot.service.TelegramMessageService;

@Component
public class LongPollingBot extends TelegramLongPollingBot {

    private final TelegramMessageService telegramMessageService;

    public LongPollingBot(TelegramMessageService telegramMessageService) {
        this.telegramMessageService = telegramMessageService;
    }

    @Value("${telegram.bot.username}")
    private String userName;

    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            telegramMessageService.executeEveryMinute();
            if (update.hasMessage()) {
                String message = update.getMessage().getText();
                switch (message) {
                    case "/start":
                        break;
                    case "/language":
//                        telegramMessageService.sendLanguageMessage(update);
                        break;
                    case "/count":
//                        telegramMessageService.sendCustomersCount(update);
                        break;
                }
            } else if (update.hasCallbackQuery()) {
                telegramMessageService.checkCallbackAnswer(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
