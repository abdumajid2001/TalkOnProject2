package com.talkon.talkon.controllers.review;

import com.google.firebase.database.core.Path;
import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.review.RatingCreateDto;
import com.talkon.talkon.dtos.review.ReviewCreateDto;
import com.talkon.talkon.dtos.review.ReviewDto;
import com.talkon.talkon.projections.review.ReviewProjection;
import com.talkon.talkon.services.review.ReviewService;
import com.talkon.talkon.services.review.ReviewServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.UUID;
@RestController
public class RatingAndReviewController extends AbstractController<ReviewService> {
    protected RatingAndReviewController(ReviewService service) {
        super(service);
    }
    @PostMapping(PATH+"/review")
    public HttpEntity<?> saveReview(@RequestBody ReviewCreateDto dto){
        Serializable s = service.create(dto);
        return ResponseEntity.ok(s.toString());
    }

    @GetMapping(PATH+"/review/all/{mentorId}")
    public HttpEntity<?> getAllReviewByMentorId(@PathVariable String mentorId,
                                                @RequestParam (name = "page",defaultValue = "0") Integer page,
                                                @RequestParam (name = "size", defaultValue = "5") Integer size){
        return ResponseEntity.ok(service.getAllReview(page,size,mentorId));
    }
    @PostMapping(PATH+"/rating")
    public HttpEntity<?> countRating(@RequestBody RatingCreateDto dto){
        service.countRating(dto);
        return ResponseEntity.ok("Successfully rated");
    }
    @PostMapping(PATH+"/edit-review/{reviewId}")
    public HttpEntity<?> editReview(@RequestBody ReviewCreateDto dto,@PathVariable String reviewId){
        service.editReview(dto,reviewId);
        return ResponseEntity.ok("Successfully edited");

    }
}
