package com.talkon.talkon.services.user.member.mentor;

import com.talkon.talkon.config.security.UserSession;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.schedule.ScheduleDto;
import com.talkon.talkon.dtos.schedule.ScheduleTimeDto;
import com.talkon.talkon.dtos.user.member.mentor.*;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.enums.ScheduleStatus;
import com.talkon.talkon.exceptions.MentorIdNotFoundException;
import com.talkon.talkon.exceptions.user.MentorNotFoundException;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.member.mentor.MentorMapper;
import com.talkon.talkon.mappers.user.user.UserMapper;
import com.talkon.talkon.projections.history.HistoryProjection;
import com.talkon.talkon.projections.schedule.ScheduleProjection;
import com.talkon.talkon.repositories.chat.VideoRepository;
import com.talkon.talkon.repositories.schedule.ScheduleRepository;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.member.mentor.MentorValidation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final ScheduleRepository scheduleRepository;

    private final VideoRepository videoRepository;

    private final UserSession userSession;


    public MentorServiceImp(MentorMapper mapper, MentorValidation validator, MentorRepository repository, UserRepository userRepository, UserMapper userMapper, ScheduleRepository scheduleRepository, VideoRepository videoRepository, UserSession userSession) {
        super(mapper, validator, repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.scheduleRepository = scheduleRepository;
        this.videoRepository = videoRepository;
        this.userSession = userSession;
    }

    @Override
    public String create(MentorCreateDto dto) {
        validator.validOnCreate(dto);
        User user = null;
        Optional<User> userOptional = userRepository.findById(dto.getId());
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
        user = mapper.fromCreateDto(dto, user);
        User savedUser = userRepository.save(user);
        Mentor mentee = new Mentor(dto.getExperience());
        mentee.setUser(savedUser);
        repository.save(mentee);
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
            Mentor mentor = null;
            Optional<Mentor> mentorOptional = repository.findByIdAndDeletedFalse(dto.getMentorId());
            if (mentorOptional.isPresent()) {
                mentor = mentorOptional.get();
            } else {
                throw new MentorNotFoundException("Mentor Not Found Exception");
            }
            mentor.setAboutText(dto.getAboutText());
            repository.save(mentor);
        }
    }

    @Override
    public MentorDto get(String id) {
        MentorDto mentee = repository.getMentorById(id);
        if (Objects.isNull(mentee)) {
            throw new MentorNotFoundException("Mentor not found");
        }
        return mentee;
    }

    @Override
    public List<MentorDto> getAll(GenericCriteria criteria) {

        return null;
    }

    public List<MentorDtoForGetAll> getAllForAll(GenericCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getSize());
        return repository.getAll(pageRequest);
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
        User user = userSession.getUser().getUser();
        Optional<Mentor> byUserId = repository.findByUserId(user.getId());
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
            schedule.setCreatedBy(user.getId());
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

    public ResponseEntity<?> seeHistories(String id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<HistoryProjection> historyProjections = videoRepository.seeHistories(id, pageable);
        return ResponseEntity.ok(historyProjections);

    }

}