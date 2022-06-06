package com.talkon.talkon.repositories.user.member.mentor;

import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDtoForGetAll;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentorRepository extends AbstractRepository<Mentor, String> {

    @Modifying
    @Query(value = "update mentors um set um.deleted = true where um.user.id = ?1")
    void deleteByUserId(String id);

    Optional<Mentor> findByIdAndDeletedFalse(String id);

    @Query(value = "select new com.talkon.talkon.dtos.user.member.mentor.MentorDto(u.id," +
            "m.experience," +
            "u.firstName," +
            "u.lastName," +
            "u.email," +
            "u.username," +
            "u.dataOfBirth," +
            "u.timeZone," +
            "m.aboutMediaLink," +
            "m.aboutText," +
            "m.ratingValue," +
            "u.photoPath) from users u inner join mentors m on u.id = m.user.id where u.deleted = false and u.id = ?1")
    MentorDto getMentorById(String id);

    @Modifying
    @Query("update users  u set u.status = 221 where u.id = ?1")
    void block(String id);

    @Modifying
    @Query("update users  u set u.status = 0 where u.id = ?1")
    void unBlock(String id);

    Optional<Mentor> findByUserId(String user_id);



    @Query("select new com.talkon.talkon.dtos.user.member.mentee.MenteeDtoForGetAll(" +
            "u.id" +
            ",u.username" +
            ",u.photoPath" +
            ") from users u inner join mentors m on u.id=m.user.id where u.deleted = false "
    )
    List<MentorDtoForGetAll> getAll(PageRequest pageRequest);
}
