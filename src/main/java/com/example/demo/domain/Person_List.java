package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person_List")
public class Person_List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private Action action;
    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "disc_id")
    private Disc disc;

    private String was_created;

}
