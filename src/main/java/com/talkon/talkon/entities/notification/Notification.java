package com.talkon.talkon.entities.notification;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.enums.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "notification")
@Entity(name = "notifications")
public class Notification extends Auditable {

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "text", nullable = false)
    String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    NotificationType type;

    @ManyToOne
            @JoinColumn(name = "to_id")
    User to;
}
