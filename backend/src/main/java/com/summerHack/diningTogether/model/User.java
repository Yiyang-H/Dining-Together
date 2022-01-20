package com.summerHack.diningTogether.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 45, nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "currency", nullable = false)
    private Integer currency;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    private String email;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(name = "phone_number", length = 45, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "suburb", length = 4, nullable = false)
    private String suburb;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
    private List<Food> foods;
}
