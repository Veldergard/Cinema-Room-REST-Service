package cinema;

public class Stats {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public Stats(int number_of_available_seats) {
        this.current_income = 0;
        this.number_of_available_seats = number_of_available_seats;
        this.number_of_purchased_tickets = 0;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void addCurrent_income(int income) {
        current_income += income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void incNumber_of_available_seats() {
        this.number_of_available_seats++;
    }

    public void decNumber_of_available_seats() {
        this.number_of_available_seats--;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void incNumber_of_purchased_tickets() {
        this.number_of_purchased_tickets++;
    }

    public void decNumber_of_purchased_tickets() {
        this.number_of_purchased_tickets--;
    }
}
