package com.talkon.talkon.services.user.member.mentor;

import com.talkon.talkon.config.security.UserSession;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.schedule.ScheduleDto;
import com.talkon.talkon.dtos.schedule.ScheduleTimeDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.enums.ScheduleStatus;
import com.talkon.talkon.exceptions.MentorIdNotFoundException;
import com.talkon.talkon.exceptions.user.MentorNotFoundException;
import com.talkon.talkon.mappers.user.member.mentor.MentorMapper;
import com.talkon.talkon.mappers.user.user.UserMapper;
import com.talkon.talkon.projections.schedule.ScheduleProjection;
import com.talkon.talkon.repositories.schedule.ScheduleRepository;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.member.mentor.MentorValidation;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class MentorServiceImp extends AbstractService<MentorRepository, MentorMapper, MentorValidation> implements MentorService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSession userSession;
    private final MentorRepository mentorRepository;
    private final ScheduleRepository scheduleRepository;

    public MentorServiceImp(MentorMapper mapper, MentorValidation validator, MentorRepository repository, UserRepository userRepository, UserMapper userMapper, UserSession userSession, MentorRepository mentorRepository, ScheduleRepository scheduleRepository) {
        super(mapper, validator, repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userSession = userSession;
        this.mentorRepository = mentorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public String create(MentorCreateDto dto) {
        validator.validOnCreate(dto);
        validator.validOnCreate(dto);
        User user = mapper.fromCreateDto(dto, userRepository.findById(dto.getId()).get());
        User savedUser = userRepository.save(user);
//
        Mentor mentee = new Mentor(dto.getExperience());
        mentee.setUser(savedUser);
        Mentor savedMentor = repository.save(mentee);
        return dto.getId();
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        userRepository.deleteByIdMy(id);
        repository.deleteByUserId(id);
    }

    @Override
    public void update(MentorUpdateDto dto) {
        validator.validOnUpdate(dto);
        User user = mapper.fromUpdateDto(dto, userRepository.findById(dto.getId()).get());
        userRepository.save(user);

        if (Objects.nonNull(dto.getAboutText())) {
            if (Objects.isNull(dto.getMentorId())) {
                throw new MentorIdNotFoundException("MentorId Not Found Exception");
            }

            Mentor mentor = repository.findByIdAndDeletedFalse(dto.getMentorId()).get();

            mentor.setAboutText(dto.getAboutText());
            repository.save(mentor);
        }
    }

    @Override
    public MentorDto get(String id) {
        if (Objects.nonNull(repository.getMentorById(id))) {
            return repository.getMentorById(id);
        }
        throw new MentorNotFoundException("Mentor not found");
    }

    @Override
    public List<MentorDto> getAll(GenericCriteria criteria) {

        return null;
    }

    @Override
    public void block(String id) {
        repository.block(id);
    }

    @Override
    public void unBlock(String id) {
        repository.unBlock(id);
    }

    @Override
    public HttpEntity<?> saveSchedule(ScheduleDto scheduleDto) {
//        String id = userSession.getUser().getId();
        String id = "f1a897aa-7ce0-4695-a3d6-977eaed981f0";

        Optional<Mentor> byUserId = mentorRepository.findByUserId(id);
        if (!byUserId.isPresent()) {
            throw new MentorNotFoundException("Mentor not found");
        }
        List<Schedule> schedules = new ArrayList<>(scheduleDto.getScheduleTimes().size());
        List<ScheduleTimeDto> scheduleTimeDto = scheduleDto.getScheduleTimes();
        for (ScheduleTimeDto date : scheduleTimeDto) {
            Schedule schedule = new Schedule();
            schedule.setScheduleStatus(ScheduleStatus.NEW);
            schedule.setMentor(byUserId.get());
            schedule.setCreatedAt(LocalDateTime.now());
            schedule.setCreatedBy(id);
            schedule.setStartDateTime(date.getStartTime());
            schedule.setDuration(20);
            schedules.add(schedule);
        }
        scheduleRepository.saveAll(schedules);
        return ResponseEntity.ok("successfully save");
    }

    @Override
    public HttpEntity<?> getAllSchedule(String mentorId) {
        List<ScheduleProjection> allScheduleByMentorId = scheduleRepository.getAllScheduleByMentorId(mentorId);
        return ResponseEntity.ok(allScheduleByMentorId);
    }
}
