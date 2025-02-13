package com.API.EquipmentRental.model;

import com.API.EquipmentRental.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users_equipment_rental")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String username;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_equipment",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipmentid", referencedColumnName = "id")
    )
    List<Equipment> equipment;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password", nullable = false)
    private String hashedPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
