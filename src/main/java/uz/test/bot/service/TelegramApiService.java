package uz.test.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramApiService {

    private final Logger log = LoggerFactory.getLogger(TelegramApiService.class);

    private final String BASE_URL = "https://api.telegram.org/bot";

    @Value("${telegram.bot.token}")
    private String token;

    public ApiResponse sendMessage(SendMessage sendMessage) {
        Map<String, Object> params = new HashMap<>();
        params.put("chat_id", sendMessage.getChatId());
        params.put("text", sendMessage.getText());
        params.put("parse_mode", "MarkdownV2");
        if (sendMessage.getReplyMarkup() != null) {
            params.put("reply_markup", sendMessage.getReplyMarkup());
        }
        return new RestTemplate().postForObject(BASE_URL + token + "/sendMessage", params, ApiResponse.class);
    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        Map<String, Object> params = new HashMap<>();
        params.put("chat_id", deleteMessage.getChatId());
        params.put("message_id", deleteMessage.getMessageId());
        try {
            new RestTemplate().postForObject("https://api.telegram.org/bot" + token + "/deleteMessage", params, ApiResponse.class);
        } catch (Exception e) {
            log.error("Error in deleteMessage ex : {}", e.getMessage());
        }
    }

    public ApiResponse sendPhoto(SendPhoto sendPhoto) {
        Map<String, Object> params = new HashMap<>();
        params.put("chat_id", sendPhoto.getChatId());
        params.put("photo", sendPhoto.getPhoto());
        params.put("caption", sendPhoto.getCaption());
        params.put("parse_mode", "MarkdownV2");
        if (sendPhoto.getReplyMarkup() != null) {
            params.put("reply_markup", sendPhoto.getReplyMarkup());
        }
        return new RestTemplate().postForObject(BASE_URL + token + "/sendPhoto", params, ApiResponse.class);
    }

    public void sendVideo(SendVideo sendVideo) {
        Map<String, Object> params = new HashMap<>();
        params.put("chat_id", sendVideo.getChatId());
        params.put("video", sendVideo.getVideo());
        params.put("caption", sendVideo.getCaption());
        params.put("parse_mode", "MarkdownV2");
        if (sendVideo.getReplyMarkup() != null) {
            params.put("reply_markup", sendVideo.getReplyMarkup());
        }
        new RestTemplate().postForObject(BASE_URL + token + "/sendVideo", params, ApiResponse.class);
    }
}
