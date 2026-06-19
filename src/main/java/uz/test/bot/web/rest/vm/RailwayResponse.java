package uz.test.bot.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RailwayResponse {

    private DataContainer data;
    private Object error;

    // Getter va Setterlar
    public DataContainer getData() {
        return data;
    }

    public void setData(DataContainer data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataContainer {

        private Directions directions;

        public Directions getDirections() {
            return directions;
        }

        public void setDirections(Directions directions) {
            this.directions = directions;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Directions {

        private ForwardDirection forward;

        public ForwardDirection getForward() {
            return forward;
        }

        public void setForward(ForwardDirection forward) {
            this.forward = forward;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForwardDirection {

        private List<Train> trains;

        public List<Train> getTrains() {
            return trains;
        }

        public void setTrains(List<Train> trains) {
            this.trains = trains;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Train {

        private String number;
        private String departureDate;
        private String arrivalDate;
        private String timeOnWay;
        private String brand;
        private RouteInfo originRoute;
        private SubRoute subRoute;
        private List<Car> cars;

        // Getter va Setterlar
        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public String getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        public String getTimeOnWay() {
            return timeOnWay;
        }

        public void setTimeOnWay(String timeOnWay) {
            this.timeOnWay = timeOnWay;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public RouteInfo getOriginRoute() {
            return originRoute;
        }

        public void setOriginRoute(RouteInfo originRoute) {
            this.originRoute = originRoute;
        }

        public SubRoute getSubRoute() {
            return subRoute;
        }

        public void setSubRoute(SubRoute subRoute) {
            this.subRoute = subRoute;
        }

        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RouteInfo {

        private String depStationName;
        private String arvStationName;

        public String getDepStationName() {
            return depStationName;
        }

        public void setDepStationName(String depStationName) {
            this.depStationName = depStationName;
        }

        public String getArvStationName() {
            return arvStationName;
        }

        public void setArvStationName(String arvStationName) {
            this.arvStationName = arvStationName;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubRoute {

        private String depStationName;
        private String depStationCode;
        private String arvStationName;
        private String arvStationCode;

        public String getDepStationName() {
            return depStationName;
        }

        public void setDepStationName(String depStationName) {
            this.depStationName = depStationName;
        }

        public String getDepStationCode() {
            return depStationCode;
        }

        public void setDepStationCode(String depStationCode) {
            this.depStationCode = depStationCode;
        }

        public String getArvStationName() {
            return arvStationName;
        }

        public void setArvStationName(String arvStationName) {
            this.arvStationName = arvStationName;
        }

        public String getArvStationCode() {
            return arvStationCode;
        }

        public void setArvStationCode(String arvStationCode) {
            this.arvStationCode = arvStationCode;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Car {

        private String type;
        private int freeSeats;
        private List<Tariff> tariffs;
        private SeatDetail seatDetail;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getFreeSeats() {
            return freeSeats;
        }

        public void setFreeSeats(int freeSeats) {
            this.freeSeats = freeSeats;
        }

        public List<Tariff> getTariffs() {
            return tariffs;
        }

        public void setTariffs(List<Tariff> tariffs) {
            this.tariffs = tariffs;
        }

        public SeatDetail getSeatDetail() {
            return seatDetail;
        }

        public void setSeatDetail(SeatDetail seatDetail) {
            this.seatDetail = seatDetail;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tariff {

        private String classServiceType;
        private long tariff;

        public String getClassServiceType() {
            return classServiceType;
        }

        public void setClassServiceType(String classServiceType) {
            this.classServiceType = classServiceType;
        }

        public long getTariff() {
            return tariff;
        }

        public void setTariff(long tariff) {
            this.tariff = tariff;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SeatDetail {

        private int down;
        private int up;
        private int lateralDn;
        private int lateralUp;

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }

        public int getLateralDn() {
            return lateralDn;
        }

        public void setLateralDn(int lateralDn) {
            this.lateralDn = lateralDn;
        }

        public int getLateralUp() {
            return lateralUp;
        }

        public void setLateralUp(int lateralUp) {
            this.lateralUp = lateralUp;
        }
    }
}
