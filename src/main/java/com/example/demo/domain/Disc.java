package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Disc")
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(nullable = false)
    private String Name;
    String Country;
    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id")
    private Genre Genre;
    int Year;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "producer_disc",
            joinColumns = @JoinColumn(name="disc_id"),
            inverseJoinColumns = @JoinColumn(name="producer_id"))
    @JsonManagedReference
    private Producer producer;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "disc_id")
    private List<Person_List> list;

}
