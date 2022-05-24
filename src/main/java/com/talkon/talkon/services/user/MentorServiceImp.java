package com.talkon.talkon.services.user;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.mentor.MentorDto;
import com.talkon.talkon.dtos.mentor.MentorUpdateDto;
import com.talkon.talkon.mappers.mentor.MentorMapper;
import com.talkon.talkon.repositories.mentor.MentorRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.mentor.MentorValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<MentorDto> getAll(BaseGenericCriteria criteria) {
        return null;
    }
}
