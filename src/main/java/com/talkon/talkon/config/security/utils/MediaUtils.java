package com.talkon.talkon.config.security.utils;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class MediaUtils {
    public final static String DOWNLOAD_URL="https://firebasestorage.googleapis.com/v0/b/talkondemo1.appspot.com/o/%s?alt=media";
    public final static String BUCKET_NAME="talkondemo1.appspot.com";
    public final static String FIREBASE_TOKEN_PATH="src/main/resources/talkondemo1-firebase-adminsdk-krzb5-00092439ca.json";



}
