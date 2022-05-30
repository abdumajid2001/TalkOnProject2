package com.talkon.talkon.services.media;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.talkon.talkon.utils.MediaUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import static com.talkon.talkon.utils.MediaUtils.*;

@Service
public class MediaServiceImp implements MediaService {
    @Override
    public Object upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            String temp = this.uploadFile(file, fileName);
            file.delete();
            return ResponseEntity.ok(temp);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }
    @Override
    public Object delete(String path) {

        try {
            String fileName = this.getFileNameFromPath(path);
            this.deleteFile(fileName);
            return ResponseEntity.ok();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(MediaUtils.FIREBASE_TOKEN_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, "UTF-8"));
    }


    private void deleteFile(String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(FIREBASE_TOKEN_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.delete(blobId);
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private  String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private  String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("?"));
    }

}
