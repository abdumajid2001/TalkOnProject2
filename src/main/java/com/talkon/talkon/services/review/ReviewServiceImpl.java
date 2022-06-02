package com.talkon.talkon.services.review;

import com.talkon.talkon.config.security.UserSession;
import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.review.RatingCreateDto;
import com.talkon.talkon.dtos.review.ReviewCreateDto;
import com.talkon.talkon.dtos.review.ReviewDto;
import com.talkon.talkon.dtos.review.ReviewUpdateDto;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.entities.user.members.review.Review;
import com.talkon.talkon.projections.review.ReviewProjection;
import com.talkon.talkon.repositories.rate.RateRepository;
import com.talkon.talkon.repositories.review.ReviewRepository;
import com.talkon.talkon.repositories.user.member.mentee.MenteeRepository;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    RateRepository rateRepository;
    ReviewRepository reviewRepository;
    MenteeRepository menteeRepository;
    MentorRepository mentorRepository;
    UserSession userSession;

    @Override
    public String create(ReviewCreateDto dto) {
        Review review = new Review();
        review.setBody(dto.getBody());
        Optional<Mentor> byId = mentorRepository.findById(dto.getMentorId());
        if (byId.isPresent()) {
            Mentor mentor = byId.get();
            mentor.setId(dto.getMentorId());
            review.setMentor(mentor);
        }
        String mentorId = "3bee766e-8351-4ac5-b9f7-1de3218eb9cb";
        Optional<Mentee> findMenteeById = menteeRepository.findById(mentorId);
        if (findMenteeById.isPresent()) {
            Mentee mentee = findMenteeById.get();
            mentee.setId(mentorId);
            review.setMentee(mentee);
        }
        reviewRepository.save(review);
        return "Successfully review created";
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(ReviewUpdateDto dto) {

    }

    @Override
    public ReviewDto get(String id) {

        return null;
    }

    @Override
    public List<ReviewDto> getAll(BaseGenericCriteria criteria) {
        return null;
    }


    @Override
    public List<ReviewProjection> getAllReview(Integer page, Integer size, String mentorId) {
        PageRequest of = PageRequest.of(page, size);
        if (mentorId != null) {
            List<ReviewProjection> allReviewByMentorId = reviewRepository.getAllReviewByMentorId(of, mentorId);
            return allReviewByMentorId;
        }
        return null;
    }

    @Override
    public void countRating(RatingCreateDto dto) {
        Optional<Mentor> byId = mentorRepository.findById(dto.getMentorId());
        if (byId.isPresent()) {
            rateRepository.countRate(dto.getMentorId(), dto.getRatingValue());
        }
    }

    @Override
    public void editReview(ReviewCreateDto dto, String reviewId) {
        Optional<Review> byId = reviewRepository.findById(UUID.fromString(reviewId));
        Review review = new Review();
        if (byId.isPresent()) {
            review.setBody(dto.getBody());
            reviewRepository.save(review);
        }
    }
}
