package com.talkon.talkon.repositories.review;

import com.talkon.talkon.entities.user.members.review.Review;
import com.talkon.talkon.projections.review.ReviewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(nativeQuery = true, value =
            "select u.photo_path as photoPath,\n" +
                    "       m.id as menteeId,\n" +
                    "       u.username            as username,\n" +
                    "       m.created_at          as createdDate,\n" +
                    "        r.body as body\n" +
                    "from users.users u\n" +
                    "         join users.mentors on u.id = mentors.user_id\n" +
                    "         join users.reviews  r on mentors.id = r.mentor_id\n" +
                    "         join users.mentees m on r.mentee_id = m.id\n" +
                    "where mentor_id = :mentorId\n")
    List<ReviewProjection> getAllReviewByMentorId(Pageable pageable, String mentorId);


    @Query(nativeQuery = true, value =
            "update users.reviews set body=:bodyText where reviews.id=:reviewId")
    void updateReview(String reviewId, String bodyText);
}
