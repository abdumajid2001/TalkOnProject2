package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.enums.Role;
import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Type;

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
@Table(schema = "users", indexes = {
        @Index(name = "phoneNumber_index", columnList = "phoneNumber"),
        @Index(name = "email_index", columnList = "email"),
        @Index(name = "username_index", columnList = "username")
})
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Email
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private LocalDate dataOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_onlined", columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean online;
    private LocalDateTime lastSeen;
    private String code;
    private int tryCount;
    private boolean firstTime;
    @Column(nullable = false)
    private LocalDateTime expiry;
    private double longitude;
    private double latitude;
    private String photoPath;
    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Message> chatMessages = new ArrayList<>();

    @Builder(builderMethodName = "childBuilder")
    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, boolean deleted, short status, String firstName, String lastName, String password, String phoneNumber, String email, String username, LocalDate dataOfBirth, Role role, boolean online, LocalDateTime lastSeen, String code, int tryCount, boolean firstTime, LocalDateTime expiry, double longitude, double latitude, String photoPath, List<Message> chatMessages) {
        super(id, createdAt, updatedAt, createdBy, deleted, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.role = role;
        this.online = online;
        this.lastSeen = lastSeen;
        this.code = code;
        this.tryCount = tryCount;
        this.firstTime = firstTime;
        this.expiry = expiry;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photoPath = photoPath;
        this.chatMessages = chatMessages;
    }
}
