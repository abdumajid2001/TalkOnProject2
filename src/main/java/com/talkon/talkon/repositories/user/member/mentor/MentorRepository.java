package com.talkon.talkon.repositories.user.member.mentor;

import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.repositories.base.AbstractRepository;
import com.talkon.talkon.repositories.base.BaseGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends AbstractRepository<Mentor, String> {

    void deleteByUserId(String id);

    Optional<Mentor> findByIdAndDeletedFalse(String id);

    @Query(value = "select new com.talkon.talkon.dtos.user.member.mentor.MentorDto()")
    MentorDto getMentorById(String id);
}
