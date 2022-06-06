package com.talkon.talkon.entities.transaction;


import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.conversation.video.VideoCall;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "internal_transactions")
public class InternalTransaction  extends Auditable {

    @OneToOne(optional = false)
    VideoCall videoCall;

    int commissionFee;



}
