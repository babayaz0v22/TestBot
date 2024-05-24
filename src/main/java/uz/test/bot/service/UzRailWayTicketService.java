package uz.test.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.test.bot.web.rest. vm.ResponseVM;


@Service
public class UzRailWayTicketService {

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    private Integer currentDay = 0;

    public ResponseVM getTicketsByDate(){
        try {
            String url = "https://eticket.railway.uz/api/v1/trains/availability/space/between/stations";
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
                System.out.println(getDate() + ".04.2024");
                return response.getBody();
        } catch (Exception e){
            log.error("Exception: {}", e.getMessage());
            return null;
        }
    }

    private String getParams() {

        return "{\n" +
            "  \"direction\": [\n" +
            "    {\n" +
            "      \"depDate\": \"01.04.2024\",\n" +
            "      \"fullday\": true,\n" +
            "      \"type\": \"Forward\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stationFrom\": \"2900790\",\n" +
            "  \"stationTo\": \"2900000\",\n" +
            "  \"detailNumPlaces\": 1,\n" +
            "  \"showWithoutPlaces\": 0\n" +
            "}";
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
