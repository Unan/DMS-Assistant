package com.griddynamics.dms.config.security.admin;

import com.griddynamics.dms.model.employee.Employee;
import com.griddynamics.dms.model.employee.InternalRole;
import com.griddynamics.dms.service.SurveyService;
import com.griddynamics.dms.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static final String TOKEN_HEADER = "x-token";

    private final SurveyService surveyService;
    private final TokenService tokenService;

    public AdminInterceptor(SurveyService surveyService, TokenService tokenService) {
        this.surveyService = surveyService;
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Admin admin = handlerMethod.getMethod().getAnnotation(Admin.class);

            if (admin == null) {
                return super.preHandle(request, response, handler);
            }

            String jwt = request.getHeader(TOKEN_HEADER);

            if (StringUtils.isEmpty(jwt)) {
                error(response, "Forbidden");
                return false;
            }

            String email = tokenService.decodeToken(jwt);
            if (email == null) {
                error(response, "Decoding error");
                return false;
            }

            Employee employee = surveyService.findEmployeeByEmail(email);

            if (employee == null) {
                error(response, "No Admin");
                return false;
            }

            if (employee.getInternalRole() != InternalRole.ADMIN) {
                error(response, "No credentials");
                return false;
            }

            request.setAttribute("employee", employee);
        }
        return super.preHandle(request, response, handler);
    }

    private void error(HttpServletResponse response, String text) throws IOException {
        try (ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response)) {
            httpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            httpResponse.getBody().write(text.getBytes(StandardCharsets.UTF_8));
        }
    }
}