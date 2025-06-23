package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.net.ProtocolFamily;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
