package com.talkon.talkon.services.user.member.mentor;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.mappers.user.member.mentor.MentorMapper;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.member.mentor.MentorValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImp extends AbstractService<MentorRepository, MentorMapper,MentorValidation> implements MentorService {
    protected MentorServiceImp( MentorMapper mapper, MentorValidation validator, MentorRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(MentorCreateDto dto) {

        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(MentorUpdateDto dto) {

    }

    @Override
    public MentorDto get(String id) {
        return null;
    }

    @Override
    public List<MentorDto> getAll(GenericCriteria criteria) {
        return null;
    }
}
