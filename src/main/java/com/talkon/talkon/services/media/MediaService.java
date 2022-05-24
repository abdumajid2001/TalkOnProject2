package com.talkon.talkon.services.media;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.talkon.talkon.config.security.utils.MediaUtils;
import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import static com.talkon.talkon.config.security.utils.MediaUtils.*;

public interface MediaService extends BaseGenericService {
    Object upload(MultipartFile multipartFile);
    Object delete(String path);

}
