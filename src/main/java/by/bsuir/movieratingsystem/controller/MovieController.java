package by.bsuir.movieratingsystem.controller;

import by.bsuir.movieratingsystem.entity.Movie;
import by.bsuir.movieratingsystem.entity.Review;
import by.bsuir.movieratingsystem.entity.User;
import by.bsuir.movieratingsystem.service.MovieService;
import by.bsuir.movieratingsystem.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @SneakyThrows
    @GetMapping
    public String getMoviesPage(Model model) {
        model.addAttribute("movies", movieService.getMoviesList());
        return "movies";
    }

    @SneakyThrows
    @PostMapping("/add")
    public String addMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String imgUrl
    ) {
        if (imgUrl.length() > 255) {
            imgUrl = "";
        }
        movieService.addMovie(title, description, imgUrl);
        return "redirect:/movies";
    }

    @PostMapping("/edit")
    public String editMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String imgUrl,
            @RequestParam Long movieId,
            Model model
    ) {
        movieService.editMovie(title, description, imgUrl, movieId);
        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String getMovie(@PathVariable Long id, Model model, HttpSession session) {
        Movie movie = movieService.getMovie(id);
        model.addAttribute("movie", movie);
        final User user = (User) session.getAttribute("user");
        this.getReviews(id, user.getId(), model);
        return "movieDetails";
    }

    @SneakyThrows
    public void getReviews(Long movieId, Long userId, Model model) {
        List<Review> reviews = reviewService.getReviews(movieId, userId);
        model.addAttribute("reviews", reviews);
    }

    @SneakyThrows
    @PostMapping("/review/add")
    public String addReview(
            @RequestParam Long movieId,
            @RequestParam int rating,
            @RequestParam String text,
            Model model,
            HttpSession session
    ) {
        final User user = (User) session.getAttribute("user");
        reviewService.addReview(movieId, rating, text, user.getId());
        this.getReviews(movieId, user.getId(), model);
        return String.format("redirect:/movies/%d", movieId);
    }

    @SneakyThrows
    @PostMapping("/review/delete")
    public String deleteReview(
            @RequestParam Long movieId,
            @RequestParam Long reviewId,
            Model model,
            HttpSession session
    ) {
        reviewService.deleteReview(reviewId, movieId);
        final User user = (User) session.getAttribute("user");
        this.getReviews(movieId, user.getId(), model);
        return String.format("redirect:/movies/%d", movieId);
    }

}
