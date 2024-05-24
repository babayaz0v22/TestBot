package uz.test.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.test.bot.web.rest.vm.*;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class TelegramMessageService {

    private final TelegramApiService telegramApiService;

    private final PinflService pinflService;

    public TelegramMessageService(TelegramApiService telegramApiService, PinflService pinflService) {
        this.telegramApiService = telegramApiService;
        this.pinflService = pinflService;
    }

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    public void getStart(Update update){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(getFormattedText("Пасспорт серия ва рақамингизни кўрсатилган форматда киритинг: АА0000000"));
        telegramApiService.sendMessage(sendMessage);
    }

    public void getDateOfBirth(Update update){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(getFormattedText("Туғилган санангизни кўрсатилган форматда киритинг: 01.01.1900"));
        telegramApiService.sendMessage(sendMessage);
    }

    public void getCaptchaImage(Update update) {
        File captchaImage = pinflService.getCaptchaImage();
        InputFile photo = new InputFile(captchaImage);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        sendPhoto.setPhoto(photo);
        sendPhoto.setCaption("Расмдаги натижани киритинг");
        telegramApiService.sendPhoto(sendPhoto);
    }

    public void sendPinflResult(Update update){

    }

    private String getFormattedText(String text) {
        return text
            .replace("[", "\\[")
            .replace("]", "\\]")
            .replace("(", "\\(")
            .replace(")", "\\)")
            .replace("~", "\\~")
            .replace("`", "\\`")
            .replace(">", "\\>")
            .replace("#", "\\#")
            .replace("+", "\\+")
            .replace("-", "\\-")
            .replace("=", "\\=")
            .replace("|", "\\|")
            .replace("{", "\\{")
            .replace("}", "\\}")
            .replace(".", "\\.")
            .replace("!", "\\!");
    }
    public void checkCallbackAnswer(Update update) {
    }
}
