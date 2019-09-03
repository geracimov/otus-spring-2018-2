package ru.geracimov.otus.spring.hw22springsecurityacl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.BookService;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.ReviewService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @GetMapping("/review")
    public String showReviewList(@NotNull Model model) {
        Iterable<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("allReviews", reviews);
        return "review-list";
    }

    @GetMapping("/review/add")
    public String showReviewAddPage(Review review,
                                    Model model) {
        model.addAttribute("allBooks", bookService.getAllBooks());
        return "review-add";
    }

    @PostMapping("/review/add")
    public String reviewSave(@Valid Review review,
                             @NotNull BindingResult result) {
        if (result.hasErrors()) {
            return "review-add";
        }
        review.setDateTime(LocalDateTime.now());
        reviewService.save(review);
        return "redirect:/review";
    }

    @PostMapping("/review/{id}/delete")
    public String deleteUser(@PathVariable("id") UUID id) {
        Review review = reviewService.getReviewById(id);
        reviewService.delete(review);
        return "redirect:/review";
    }

}