package com.dms.assistant.backend.clients;

import com.dms.assistant.backend.models.Notification;
import com.dms.assistant.backend.models.Remind;
import feign.Headers;
import feign.RequestLine;
import feign.Response;

import java.util.ArrayList;

public interface FakeSMTPClient {

    @RequestLine("GET /api/v1/messages")
    ArrayList<Notification> getNotifications();

    @RequestLine("GET /api/v1/messages")
    ArrayList<Remind> getReminds();

    @RequestLine("DELETE /api/v1/messages")
    Response deleteAllEmails();
}
