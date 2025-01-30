package com.API.EquipmentRental.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

//combine user_id and equipment_id cannot be the same
@Table(name = "reviews",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "equipmentid"})})
@Entity
public class Review {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private UUID id;

    private String comment;

    @NotNull
    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipmentid", referencedColumnName = "id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private User user;
}
