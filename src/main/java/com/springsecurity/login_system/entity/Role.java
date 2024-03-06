package com.springsecurity.login_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
