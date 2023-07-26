package com.endava.springdataexample.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    @Column(name = "construction_date")
    private LocalDate constructionDate;

    private String engine;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "garage_id")
    private Garage garage;
}
