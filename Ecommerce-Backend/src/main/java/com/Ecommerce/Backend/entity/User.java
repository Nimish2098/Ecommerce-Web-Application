package com.Ecommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    @CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
    private Set<String> roles;

}
