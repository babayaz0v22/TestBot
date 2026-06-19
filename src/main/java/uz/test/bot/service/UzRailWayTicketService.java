package uz.test.bot.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uz.test.bot.web.rest.vm.RailwayResponse;

@Service
public class UzRailWayTicketService {

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    private Integer currentDay = 0;

    public RailwayResponse getTicketsByDate() {
        try {
            String url = "https://eticket.railway.uz/api/v3/handbook/trains/list";
            String params = getParams();
            HttpHeaders headers = new HttpHeaders();
            String token = "fb19921a-03a1-4d33-a09d-1bb92d9de14d";
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept-Language", "ru");
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            headers.set("X-Xsrf-Token", token);
            headers.set("Cookie", "XSRF-TOKEN=" + token);
            headers.set("Origin", "https://eticket.railway.uz");
            headers.set("Referer", "https://eticket.railway.uz/uz/pages/trains-page");
            // Используйте Map вместо String для параметров

            Map<String, String> forwardDetails = new HashMap<>();
            forwardDetails.put("date", "2026-05-02");
            forwardDetails.put("depStationCode", "2900000");
            forwardDetails.put("arvStationCode", "2900790");

            Map<String, Object> forwardMap = new HashMap<>();
            forwardMap.put("forward", forwardDetails);

            Map<String, Object> rootBody = new HashMap<>();
            rootBody.put("directions", forwardMap);
            HttpEntity<?> entity = new HttpEntity<>(rootBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<RailwayResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {}
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Тело ошибки от сервера: {}", e.getResponseBodyAsString());
            return null;
        }
    }

    private String getParams() {
        return (
            "{\n" +
            "    \"forward\": {\n" +
            "        \"date\"" +
            ": \"2026-04-08\",\n" +
            "        \"depStationCode\": \"2900000\",\n" +
            "        \"arvStationCode\": \"2900790\"\n" +
            "    }\n" +
            "}"
        );
    }

    public int getDate() {
        int nextDate = currentDay;

        currentDay++;
        if (currentDay > 20) {
            currentDay = 0;
        }

        return nextDate;
    }
}
