package com.griddynamics.dms.service;

import com.griddynamics.dms.model.GoogleUserDto;

public interface GoogleService {

    GoogleUserDto getUser(String token);
}