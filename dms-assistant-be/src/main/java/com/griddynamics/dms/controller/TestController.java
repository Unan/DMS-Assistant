package com.griddynamics.dms.controller;

import com.griddynamics.dms.model.Auth;
import com.griddynamics.dms.model.Token;
import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.service.SurveyService;
import com.griddynamics.dms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("qa")
@Controller
@RequestMapping("/api/v1/test")
public class TestController {

    private final SurveyService surveyService;
    private final TokenService tokenService;

    @Autowired
    public TestController(SurveyService surveyService,
                          TokenService tokenService
    ) {
        this.surveyService = surveyService;
        this.tokenService = tokenService;
    }

    @PostMapping("/backdoor")
    public ResponseEntity<?> loginBackdoor(@RequestBody Auth auth) {
        Token returningToken = new Token();
        Employee employee = surveyService.findEmployeeByEmail(auth.getLogin());

        if (employee != null) {
            String jwt = tokenService.encodeToken(auth.getLogin());
            returningToken.setToken(jwt);
            return ResponseEntity.ok(returningToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
