package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class MovieTheatreController {
    private final MovieTheatre movieTheatre = new MovieTheatre();
    private final List<Ticket> ticketList = new ArrayList<>();
    private final Stats stats = new Stats(movieTheatre.getTotal_columns() * movieTheatre.getTotal_rows());

    public MovieTheatreController() {
    }

    @GetMapping("/seats")
    public MovieTheatre getSeats() {
        return movieTheatre;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody Seat seat) {
        int price;
        if (seat.getRow() < 1 || seat.getRow() > movieTheatre.getTotal_rows() ||
                seat.getColumn() < 1 || seat.getColumn() > movieTheatre.getTotal_columns()) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        Seat purchased = movieTheatre.findSeat(seat.getRow(), seat.getColumn());
        if (purchased == null) {
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        movieTheatre.getAvailable_seats().remove(purchased);
        Ticket ticket = new Ticket(purchased);
        ticketList.add(ticket);
        stats.decNumber_of_available_seats();
        stats.incNumber_of_purchased_tickets();
        stats.addCurrent_income(ticket.getTicket().getPrice());
        return new ResponseEntity(ticket, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity refundTicket(@RequestBody Token token) {
        UUID uuid = UUID.fromString(token.getToken());
        Ticket ticket = null;
        for (Ticket curTicket : ticketList) {
            if (curTicket.getToken().equals(uuid)) {
                ticket = curTicket;
                break;
            }
        }
        if (ticket == null) {
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        ticketList.remove(ticket);
        movieTheatre.addAvailable_seats(ticket.getTicket());
        stats.incNumber_of_available_seats();
        stats.decNumber_of_purchased_tickets();
        stats.addCurrent_income(-ticket.getTicket().getPrice());
        return new ResponseEntity(Map.of("returned_ticket", ticket.getTicket()), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam(value = "password", required = false) String password) {
        if (!"super_secret".equals(password)) {
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(stats, HttpStatus.OK);
    }
}
