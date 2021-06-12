package com.lisowski.pms.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Message {
    @Id
    private String id;
    private String subject;
    private String message;
    @DBRef
    private User user;

}
