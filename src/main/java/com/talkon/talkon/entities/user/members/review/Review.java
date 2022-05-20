package com.talkon.talkon.entities.user.members.review;

import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.*;

import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "users")
@Entity(name = "reviews")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review extends Auditable {

    @Column(columnDefinition = "text")
    String body;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    Mentee mentee;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    Mentor mentor;
}
