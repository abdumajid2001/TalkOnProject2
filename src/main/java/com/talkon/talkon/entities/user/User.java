package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.enums.Role;
import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.Table;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Entity(name = "users")
@Getter
@Setter
@Table(schema = "users")
@AllArgsConstructor
public class User extends Auditable {

    public User() {
        this.timeZone = -1;
    }

    private String firstName;

    private String lastName;

    private String password;

    private String phoneNumber;
    // TODO: 18/05/22  /*regex yoz*/

    @Email
    private String email;
    @Column(unique = true)
    private String username;
    private LocalDate dataOfBirth;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Integer timeZone;

    private boolean isOnline;

    private LocalDateTime lastSeen;


    private String code;
    private int tryCount;
    private boolean firstTime;
    @Column(nullable = false)
    private LocalDateTime expiry;

    private double longitude;

    private double latitude;

    private boolean online;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Message> chatMessages = new ArrayList<>();

    @Builder(builderMethodName = "childBuilder")

    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, short status, String firstName, String lastName, String password, String phoneNumber, String email, String username, LocalDate dataOfBirth, Role role, Integer timeZone,String createdBy, boolean isOnline, LocalDateTime lastSeen, String code, int tryCount, boolean firstTime, LocalDateTime expiry, List<Message> chatMessages) {
        super(id, createdAt, updatedAt,createdBy, deleted, status);

    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, short status, String firstName, String lastName, String password, String phoneNumber, String email, String username, LocalDate dataOfBirth, Role role, Integer timeZone, String code, int tryCount, boolean firstTime, LocalDateTime expiry, double longitude, double latitude, boolean online, List<Message> chatMessages) {
        super(id, createdAt, updatedAt, deleted, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.role = role;
        this.timeZone = timeZone;
        this.code = code;
        this.tryCount = tryCount;
        this.firstTime = firstTime;
        this.expiry = expiry;
        this.longitude = longitude;
        this.latitude = latitude;
        this.online = online;
        this.chatMessages = chatMessages;
        this.isOnline = isOnline;
        this.lastSeen = lastSeen;
    }
}
