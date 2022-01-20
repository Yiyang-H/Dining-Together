package com.summerHack.diningTogether.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "APPLICATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private User candidate;


    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "status", nullable = false)
    @ColumnDefault("0") // PENDING
    private ApplicationStatus status;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "candidate_notes", length = 255, nullable = false)
    private String candidateNotes;
}
