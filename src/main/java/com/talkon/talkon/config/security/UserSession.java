package com.talkon.talkon.config.security;


import com.talkon.talkon.dtos.user.user.UserDetails;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.enums.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserSession {

    private final HttpServletRequest request;


    public UserDetails getUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return principal;
    }

    public Map<Headers, String> getHeadersInfo() {
        HashMap<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return convertHeader(map);
    }

    public String getLanguage() {
        Map<Headers, String> headers = getHeadersInfo();
        if (headers.containsKey((Headers.LANGUAGE))) {
            return headers.get(Headers.LANGUAGE);
        } else {
            return Headers.LANGUAGE.defValue;
        }
    }

    private HashMap<Headers, String> convertHeader(HashMap<String, String> map) {
        HashMap<Headers, String> hashMap = new HashMap<>();
        for (String s : map.keySet()) {
            if (Headers.findByKey(s) != null) {
                hashMap.put(Headers.findByKey(s), map.get(s));
            }
        }
        return hashMap;
    }

}

