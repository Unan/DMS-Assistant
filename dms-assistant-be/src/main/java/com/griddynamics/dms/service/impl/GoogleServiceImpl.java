package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.exception.GoogleInvalidURLException;
import com.griddynamics.dms.exception.GoogleTokenExpiredException;
import com.griddynamics.dms.model.GoogleUserDto;
import com.griddynamics.dms.service.GoogleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class GoogleServiceImpl implements GoogleService {

    private static final String TOKEN = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=%s";

    @Override
    public GoogleUserDto getUser(String token) {
        try {
            URL url = new URL(String.format(TOKEN, token));
            return GoogleUserDto.of(StreamUtils.copyToString(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
        } catch (MalformedURLException e) {
            throw new GoogleTokenExpiredException();
        } catch (IOException e) {
            throw new GoogleInvalidURLException();
        }
    }
}