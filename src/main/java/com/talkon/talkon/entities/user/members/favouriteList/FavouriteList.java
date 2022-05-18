package com.talkon.talkon.entities.user.members.favouriteList;

import javax.persistence.*;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.user.members.Mentee;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "users")
@Entity(name = "favourite_lists")
@FieldDefaults(level = AccessLevel.PRIVATE)

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class FavouriteList extends Auditable {

    @OneToOne
    Mentee mentee;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    List<String> menteeList = new ArrayList<>();

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    List<String> mentorList = new ArrayList<>();

}
