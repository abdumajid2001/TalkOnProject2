package com.talkon.talkon.services.review;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.review.RatingCreateDto;
import com.talkon.talkon.dtos.review.ReviewCreateDto;
import com.talkon.talkon.dtos.review.ReviewDto;
import com.talkon.talkon.dtos.review.ReviewUpdateDto;
import com.talkon.talkon.projections.review.ReviewProjection;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ReviewService extends GenericCrudService<ReviewDto, ReviewCreateDto, ReviewUpdateDto,String, BaseGenericCriteria> {


    List<ReviewProjection> getAllReview(Integer page, Integer size, String mentorId);

    void countRating(RatingCreateDto dto);

    void editReview(ReviewUpdateDto dto,String reviewId);

    Integer getRatingByMentorId(String mentorId);
}
