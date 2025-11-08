package com.KissTech.crm.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Session  extends  AbstractEntity{

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    private String token;
    private Date expirationTime;
}
