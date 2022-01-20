package com.summerHack.diningTogether.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "APPLICATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @Column(name = "application_status", nullable = false)
    @ColumnDefault("0") // PENDING
    private ApplicationStatus status;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;
}
