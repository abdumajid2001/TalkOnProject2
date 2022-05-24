package com.talkon.talkon.repositories.user.member.mentor;

import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.repositories.base.AbstractRepository;
import com.talkon.talkon.repositories.base.BaseGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends AbstractRepository<Mentor, String> {

}
