package com.talkon.talkon.controllers.user;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.schedule.ScheduleDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDtoForGetAll;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.services.user.member.mentor.MentorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MentorController extends AbstractController<MentorService> {
    protected MentorController(MentorService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/mentor", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody MentorCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH + "/mentor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/mentor/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<MentorDto>> get(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @RequestMapping(value = PATH + "/mentor/getAll", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<List<MentorDtoForGetAll>>> getAll(@RequestBody GenericCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(service.getAllForAll(criteria)), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/mentor", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody MentorUpdateDto updateDto) {
        service.update(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @RequestMapping(value = PATH + "/mentor/blocked/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> block(@PathVariable String id) {
        service.block(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @RequestMapping(value = PATH + "/mentor/unblocked/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> unBlock(@PathVariable String id) {
        service.unBlock(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('MENTOR')")
    @PostMapping(PATH+"/schedule")
    public HttpEntity<?> saveSchedule(@RequestBody @Valid ScheduleDto scheduleDto){
        return service.saveSchedule(scheduleDto);
    }

    @RequestMapping(value = PATH + "/mentor/history",method = RequestMethod.GET)
    public ResponseEntity<?> seeHistories(@RequestParam(name = "page") int page,
                                          @RequestParam(name = "size") int size,
                                          @RequestParam(name = "id") String id){
        return service.seeHistories(id,page,size);

    }
    @PreAuthorize(value = "hasAnyRole('MENTOR', 'USER', 'ADMIN', 'SUPER_ADMIN')")
    @GetMapping(PATH+"/schedule/all")
    public HttpEntity<?> getAllScheduleByMentorId(@RequestParam String mentorId){
        return service.getAllSchedule(mentorId);
    }
}
