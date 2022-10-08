package uz.test.bot.web.rest.vm;

import java.util.List;

public class PlacesVM {
        public List<CarsVM> cars;

    public List<CarsVM> getCars() {
        return cars;
    }

    public void setCars(List<CarsVM> cars) {
        this.cars = cars;
    }
}
