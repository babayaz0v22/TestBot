package uz.test.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.test.bot.web.rest.vm.*;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class TelegramMessageService {

    private final TelegramApiService telegramApiService;

    private final UzRailWayTicketService uzRailWayTicketService;

    public TelegramMessageService(TelegramApiService telegramApiService, UzRailWayTicketService uzRailWayTicketService) {
        this.telegramApiService = telegramApiService;
        this.uzRailWayTicketService = uzRailWayTicketService;
    }

    private final Logger log = LoggerFactory.getLogger(TelegramMessageService.class);

    public void executeEveryMinute(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(drawRunnable, 0, 30, SECONDS);
    }

    public void sendInfo(CarsVM carsVM, Object date){
        if(carsVM.freeSeats != null && carsVM.type.equals("Плацкартный")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("372090525");
            sendMessage.setText(getFormattedText("‼️‼️Bilet chiqdi‼️‼️\n" + date + "\n" + carsVM.type + "\nBo'sh joylar: " + carsVM.freeSeats));
            telegramApiService.sendMessage(sendMessage);
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
        ResponseVM getTicket = uzRailWayTicketService.getTicketsByDate();
        if(getTicket == null){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("‼️‼️Bilet chiqdi‼️‼️\n");
        List<DirectionVM> direction = getTicket.express.direction;
        for (DirectionVM dir : direction) {
            List<TrainsVM> trains = dir.trains;
            for (TrainsVM trainsVM : trains) {
                List<TrainVM> trainVM = trainsVM.train;
                for (TrainVM train : trainVM) {
                    if (train.number.equals("056Ж") || train.number.equals("058Ь")) {
                        List<CarsVM> cars = train.places.cars;
                        if (!cars.isEmpty()) {
                            for (CarsVM carsVM : cars) {
                                carsVMs = carsVM;
                                sendInfo(carsVMs, train.departureTrain);
                                System.out.println(carsVM.type + ": " + carsVM.freeSeats);
                            }
                        }
                    }
                }
            }
        }
        log.debug("CarsVm: ", carsVMs.freeSeats);
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
