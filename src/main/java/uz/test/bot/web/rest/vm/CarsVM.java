package uz.test.bot.web.rest.vm;

public class CarsVM {
        public String type;
        public SeatsVM seats;
        public String indexType;
        public String freeSeats;
        public String typeShow;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SeatsVM getSeats() {
        return seats;
    }

    public void setSeats(SeatsVM seats) {
        this.seats = seats;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(String freeSeats) {
        this.freeSeats = freeSeats;
    }

    public String getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(String typeShow) {
        this.typeShow = typeShow;
    }
}
