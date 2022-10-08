package uz.test.bot.web.rest.vm;

import java.util.List;

public class DirectionVM {
    public String type;
    public List<TrainsVM> trains;
    public Object notAllTrains;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TrainsVM> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainsVM> trains) {
        this.trains = trains;
    }

    public Object getNotAllTrains() {
        return notAllTrains;
    }

    public void setNotAllTrains(Object notAllTrains) {
        this.notAllTrains = notAllTrains;
    }
}
