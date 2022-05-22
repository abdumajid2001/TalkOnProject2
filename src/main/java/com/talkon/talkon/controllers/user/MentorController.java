package com.talkon.talkon.controllers.user;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.services.user.MentorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentorController extends AbstractController<MentorService> {
    protected MentorController(MentorService service) {
        super(service);
    }

}
