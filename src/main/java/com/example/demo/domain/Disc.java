package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "disc")
public class Disc {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String name;

  private String country;

  @ManyToOne(optional = false)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  private Integer year;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "producer_disc")
  private Producer producer;

//  @OneToMany(fetch = FetchType.EAGER)
//  @JoinColumn(name = "disc_id")
//  private List<PersonList> list;

}
