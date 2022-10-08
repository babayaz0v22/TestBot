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
        scheduledExecutorService.scheduleAtFixedRate(drawRunnable, 0, 1, MINUTES);
    }

    public void sendInfo(CarsVM carsVM){
        if(carsVM.freeSeats != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("372090525");
            sendMessage.setText(getFormattedText("‼️‼️Bilet chiqdi‼️‼️\n" + carsVM.type + "\nBo'sh joylar: " + carsVM.freeSeats));
            telegramApiService.sendMessage(sendMessage);
        }
    }

    Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            getStart();
            System.out.println("Beep");
        }
    };
        public void getStart() {
            CarsVM carsVMs = new CarsVM();
            ResponseVM getTicket = uzRailWayTicketService.getTicketsByDate();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("‼️‼️Bilet chiqdi‼️‼️\n");
            List<DirectionVM> direction = getTicket.express.direction;
            for (DirectionVM dir : direction) {
                List<TrainsVM> trains = dir.trains;
                for (TrainsVM trainsVM : trains) {
                    List<TrainVM> trainVM = trainsVM.train;
                    for (TrainVM train : trainVM) {
                        List<CarsVM> cars = train.places.cars;
                        if (!cars.isEmpty()) {
                            for (CarsVM carsVM : cars) {
                                carsVMs = carsVM;
                            }
                        }
                    }
                }
            }
            sendInfo(carsVMs);
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
