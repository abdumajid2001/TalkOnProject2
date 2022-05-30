package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private boolean isOnline;

    private LocalDateTime lastSeen;

    private String code;

    private int tryCount;

    private boolean firstTime;

    @Column(nullable = false)

    private LocalDateTime expiry;

    int timeZone;

    @Column(columnDefinition = "text")
    private String photoPath;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Message> chatMessages = new ArrayList<>();

    @Builder(builderMethodName = "childBuilder")
    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, boolean deleted, short status, String firstName, String lastName, String password, String phoneNumber, String email, String username, LocalDate dataOfBirth, Role role, boolean isOnline, LocalDateTime lastSeen, String code, int tryCount, boolean firstTime, LocalDateTime expiry, int timeZone, String photoPath, List<Message> chatMessages) {
        super(id, createdAt, updatedAt, createdBy, deleted, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.role = role;
        this.isOnline = isOnline;
        this.lastSeen = lastSeen;
        this.code = code;
        this.tryCount = tryCount;
        this.firstTime = firstTime;
        this.expiry = expiry;
        this.timeZone = timeZone;
        this.photoPath = photoPath;
        this.chatMessages = chatMessages;
    }


}
