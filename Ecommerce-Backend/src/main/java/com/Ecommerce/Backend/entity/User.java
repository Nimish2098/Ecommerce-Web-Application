package com.Ecommerce.Backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;


    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    @CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
    private Set<String> roles;

}
