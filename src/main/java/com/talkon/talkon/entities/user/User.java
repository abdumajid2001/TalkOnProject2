package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.enums.Role;
import com.talkon.talkon.entities.base.Auditable;
import javax.persistence.Table;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "users")
@AllArgsConstructor
public class User extends Auditable {

    private String firstName;

    private String lastName;

    private String password;

    @Column(nullable = false)
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

    @OneToMany(mappedBy = "from")
    List<Message> chatMessages = new ArrayList<>();


    @Builder(builderMethodName = "childBuilder")
    public User(String id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, short status, String firstName, String lastName, String phoneNumber, String email, String username, String password, LocalDate dataOfBirth, Role role, Integer timeZone, List<Message> chatMessages, UUID createdBy, boolean isOnline, LocalDateTime lastSeen) {
        super(id, createdAt, updatedAt, createdBy, deleted, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dataOfBirth = dataOfBirth;
        this.role = role;
        this.timeZone = timeZone;
        this.chatMessages = chatMessages;
        this.isOnline = isOnline;
        this.lastSeen = lastSeen;
    }
}
