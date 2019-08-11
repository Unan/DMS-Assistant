package com.griddynamics.dms.controller;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.GoogleUserDto;
import com.griddynamics.dms.model.Token;
import com.griddynamics.dms.service.GoogleService;
import com.griddynamics.dms.service.SurveyService;
import com.griddynamics.dms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

    private final SurveyService surveyService;
    private final GoogleService googleService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(SurveyService surveyService,
                           GoogleService googleService,
                           TokenService tokenService
    ) {
        this.surveyService = surveyService;
        this.googleService = googleService;
        this.tokenService = tokenService;
    }

    @PostMapping("/oauth")
    public ResponseEntity<?> loginGoogle(@RequestBody Token token) {
        GoogleUserDto dto = googleService.getUser(token.getToken());
        Token returningToken = new Token();
        Employee employee = surveyService.findEmployeeByEmail(dto.getEmail());

        if (employee != null) {
            String jwt = tokenService.encodeToken(dto.getEmail());
            returningToken.setToken(jwt);
            return ResponseEntity.ok(returningToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
