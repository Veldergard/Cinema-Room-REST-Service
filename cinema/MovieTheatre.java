package cinema;

import java.util.ArrayList;
import java.util.List;

public class MovieTheatre {
    private final int total_rows = 9;
    private final int total_columns = 9;
    private List<Seat> available_seats;

    public MovieTheatre() {
        this.available_seats = new ArrayList<>();
        for (int row = 1; row <= total_rows; row++) {
            for (int col = 1; col <= total_columns; col++) {
                available_seats.add(new Seat(row, col));
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


}
