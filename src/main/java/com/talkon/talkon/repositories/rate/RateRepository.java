package com.talkon.talkon.repositories.rate;

import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RateRepository extends JpaRepository<Mentor, String> {
    @Query(nativeQuery = true,value =
    "update users.mentors set rating_count=rating_count+1,rating_value=(rating_value*mentors.rating_count+:ratingValue)/(rating_count+1) where id=:mentorId")
    void countRate(String mentorId, int ratingValue);

    @Query(nativeQuery = true,value = "" +
            "select m.rating_value from users.mentors m where m.id=:mentorId;")
    int getNewMentorRate(String mentorId);
}
