package com.talkon.talkon.repositories.user.member.mentee;

import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MenteeRepository extends AbstractRepository<Mentee, String> {

    @Modifying
    @Query(value = "update mentees  um set um.deleted = true where um.user.id = ?1")
    void deleteByUserId(String id);

    @Query("select new com.talkon.talkon.dtos.user.member.mentee.MenteeDto(" +
            "u.id" +
            ",u.firstName" +
            ",u.lastName" +
            ",u.email" +
            ",u.username" +
            ",u.dataOfBirth" +
            ",u.timeZone" +
            ",m.level" +
            ",m.conversationCount" +
            ") from mentees m inner join users u on m.user.id = u.id where not m.deleted  and m.user.id = ?1"
    )
    MenteeDto getMenteeById(String id);
}
