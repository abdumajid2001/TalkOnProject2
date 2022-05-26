package com.talkon.talkon.controllers.media;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.services.media.MediaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MediaController extends AbstractController<MediaService> {
    protected MediaController(MediaService service) {
        super(service);
    }
    @PostMapping(value = PATH+"/media/upload")
    public Object upload(@RequestBody MultipartFile multipartFile){
        return service.upload(multipartFile);
    }
    @DeleteMapping(value = PATH+"/media/upload/{path}")
    public Object upload(@PathVariable String path){
        return service.delete(path);
    }

}
