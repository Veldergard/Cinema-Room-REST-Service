package cinema;

import java.util.ArrayList;
import java.util.List;

public class MovieTheatre {
    private final int total_rows = 9;
    private final int total_columns = 9;
    private List<Seat> available_seats;

    public Seat findSeat(int row, int col) {
        for (int i = 0; i < available_seats.size(); i++) {
            if (available_seats.get(i).getRow() == row &&
                    available_seats.get(i).getColumn() == col) {
                return available_seats.get(i);
            }
        }
        return null;
    }

    public MovieTheatre() {
        int price;
        this.available_seats = new ArrayList<>();
        for (int row = 1; row <= total_rows; row++) {
            if (row <= 4) {
                price = 10;
            } else {
                price = 8;
            }
            for (int col = 1; col <= total_columns; col++) {
                this.available_seats.add(new Seat(row, col));
                this.available_seats.get(available_seats.size() - 1).setPrice(price);
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public void addAvailable_seats(Seat seat) {
        available_seats.add(seat);
    }
}
