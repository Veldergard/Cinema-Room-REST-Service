package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieTheatreController {
    private MovieTheatre movieTheatre;

    public MovieTheatreController() {
        this.movieTheatre = new MovieTheatre();
    }

    @GetMapping("/seats")
    public MovieTheatre getSeats() {
        return movieTheatre;
    }
}
