package uz.test.bot.service;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.test.bot.web.rest.vm.*;

@Service
public class TelegramMessageService {

    private final TelegramApiService telegramApiService;

    private final UzRailWayTicketService uzRailWayTicketService;

    public TelegramMessageService(TelegramApiService telegramApiService, UzRailWayTicketService uzRailWayTicketService) {
        this.telegramApiService = telegramApiService;
        this.uzRailWayTicketService = uzRailWayTicketService;
    }

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    public void executeEveryMinute() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(drawRunnable, 0, 10, SECONDS);
    }

    public void sendInfo(RailwayResponse.Car carsVM, RailwayResponse.SeatDetail date) {
        if (carsVM.getFreeSeats() != 0) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("372090525");
            sendMessage.setText(
                getFormattedText(
                    "‼️‼️Bilet chiqdi‼️‼️\n" +
                    "\n" +
                    carsVM.getType() +
                    "\nBo'sh joylar: " +
                    carsVM.getFreeSeats() +
                    "\nВерхние: " +
                    date.getUp() +
                    "\nНижние: " +
                    date.getDown()
                )
            );
            SendMessage sendMessage1 = new SendMessage();
            //            sendMessage1.setChatId("352251413");
            //            sendMessage1.setText(getFormattedText("‼️‼️Bilet chiqdi‼️‼️\n" + "\n" + carsVM.getType() + "\nBo'sh joylar: " + carsVM.getFreeSeats() + "\nВерхние: " + date.getUp() + "\nНижние: " + date.getDown()));
            telegramApiService.sendMessage(sendMessage);
            //            telegramApiService.sendMessage(sendMessage1);
        }
    }

    Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            getStart();
        }
    };

    public void getStart() {
        CarsVM carsVMs = new CarsVM();
        RailwayResponse getTicket = uzRailWayTicketService.getTicketsByDate();
        if (getTicket == null) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("‼️‼️Bilet chiqdi‼️‼️\n");
        RailwayResponse.Directions direction = getTicket.getData().getDirections();
        List<RailwayResponse.Train> trains = direction.getForward().getTrains();
        for (RailwayResponse.Train trainsVM : trains) {
            List<RailwayResponse.Car> cars = trainsVM.getCars();
            System.out.println("Size: " + cars.size());
            System.out.println("Train number: " + trainsVM.getNumber());
            if (!cars.isEmpty()) {
                for (RailwayResponse.Car carsVM : cars) {
                    sendInfo(carsVM, carsVM.getSeatDetail());
                }
            }
        }
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

    public void checkCallbackAnswer(Update update) {}
}
