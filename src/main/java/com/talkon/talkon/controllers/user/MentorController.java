package com.talkon.talkon.controllers.user;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.services.user.member.mentor.MentorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @RequestMapping(value = PATH + "/mentor/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<MentorDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll(new GenericCriteria())), HttpStatus.OK);
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @RequestMapping(value = PATH + "/mentor", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody MentorUpdateDto updateDto) {
        service.update(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
