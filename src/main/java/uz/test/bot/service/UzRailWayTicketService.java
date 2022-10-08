package uz.test.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.test.bot.web.rest.vm.ResponseVM;

@Service
public class UzRailWayTicketService {

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    public ResponseVM getTicketsByDate(){
        String url = "https://chipta.railway.uz/api/v1/trains/availability/space/between/stations";
        String params = getParams();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Language", "ru");
        headers.set("Accept", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseVM> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<>() {
            }
        );
        return response.getBody();
    }

    private String getParams() {
        return "{\n" +
            "  \"direction\": [\n" +
            "    {\n" +
            "      \"depDate\": \"11.10.2022\",\n" +
            "      \"fullday\": true,\n" +
            "      \"type\": \"Forward\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stationFrom\": \"2900000\",\n" +
            "  \"stationTo\": \"2900790\",\n" +
            "  \"detailNumPlaces\": 1,\n" +
            "  \"showWithoutPlaces\": 0\n" +
            "}";
    }
}
