package com.talkon.talkon.controllers.user;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.services.user.member.mentee.MenteeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenteeController extends AbstractController<MenteeService> {

    public MenteeController(MenteeService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/mentee", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody MenteeCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH + "/mentee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/mentee/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<MenteeDto>> get(@PathVariable String id) {
        return new ResponseEntity<>(new DataDto<>(service.get(id)), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/mentee/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<MenteeDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(service.getAll(new GenericCriteria())), HttpStatus.OK);
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPER_ADMIN', 'MANAGER')")
    @RequestMapping(value = PATH + "/mentee/update", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody MenteeUpdateDto updateDto) {
        service.update(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}