package com.griddynamics.dms.service;

public interface TokenService {

    String encodeToken(String email);

    String decodeToken(String jwt);
}