package com.dms.assistant.backend.models.email;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailAddress {

    private String domain;
    private String mailbox;

    public EmailAddress(@JsonProperty(value = "Domain") String domain,
                        @JsonProperty(value = "Mailbox") String mailbox) {
        this.domain = domain;
        this.mailbox = mailbox;
    }

    public String getDomain() {
        return domain;
    }

    public String getMailbox() {
        return mailbox;
    }

    public String getFullAddress() {
        return mailbox + "@" + domain;
    }
}
