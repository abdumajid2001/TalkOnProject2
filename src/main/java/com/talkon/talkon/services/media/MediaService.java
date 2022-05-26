package com.talkon.talkon.services.media;

import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService extends BaseGenericService {
    Object upload(MultipartFile multipartFile);
    Object delete(String path);

}
