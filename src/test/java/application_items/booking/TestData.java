package application_items.booking;

public class TestData {

    private String destination ;
    private int arrivalInXDays;
    private int durationOfStay;
    private int adults;
    private int children;
    private int rooms;

    public TestData() {
    }

    public TestData(String destination, int arrivalInXDays, int durationOfStay, int adults, int children, int rooms) {
        this.destination = destination;
        this.arrivalInXDays = arrivalInXDays;
        this.durationOfStay = durationOfStay;
        this.adults = adults;
        this.children = children;
        this.rooms = rooms;
    }

    public String getDestination() {
        return destination;
    }

    public int getArrivalInXDays() {
        return arrivalInXDays;
    }

    public int getDurationOfStay() {
        return durationOfStay;
    }

    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public int getRooms() {
        return rooms;
    }
}
