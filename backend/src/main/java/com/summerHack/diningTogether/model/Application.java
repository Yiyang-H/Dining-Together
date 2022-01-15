package com.summerHack.diningTogether.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "APPLICATION")
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @Column(name = "application_status", nullable = false)
    @ColumnDefault("0") // PENDING
    private ApplicationStatus status;

    @Column(name = "created_time", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createdTime;
}
