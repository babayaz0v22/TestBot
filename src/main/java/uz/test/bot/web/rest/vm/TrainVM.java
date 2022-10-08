package uz.test.bot.web.rest.vm;

public class TrainVM {

    public String length;
    public String type;
    public String number;
    public String brand;
    public String timeInWay;
    public String number2;
    public String elRegPossible;
    public Object firmName;
    public Object parom;
    public Object bus;
    public Object comments;
    public PlacesVM places;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(String timeInWay) {
        this.timeInWay = timeInWay;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getElRegPossible() {
        return elRegPossible;
    }

    public void setElRegPossible(String elRegPossible) {
        this.elRegPossible = elRegPossible;
    }

    public Object getFirmName() {
        return firmName;
    }

    public void setFirmName(Object firmName) {
        this.firmName = firmName;
    }

    public Object getParom() {
        return parom;
    }

    public void setParom(Object parom) {
        this.parom = parom;
    }

    public Object getBus() {
        return bus;
    }

    public void setBus(Object bus) {
        this.bus = bus;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public PlacesVM getPlaces() {
        return places;
    }

    public void setPlaces(PlacesVM places) {
        this.places = places;
    }
}
