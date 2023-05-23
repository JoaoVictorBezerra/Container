package com.api.containers.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "Containers")

public class ContainerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 70)
    private String cliente;
    @Column(nullable = false, unique = true, length = 11)
    private String container;
    @Column(nullable = false, length = 2)
    public int tipo;
    @Column(nullable = false, length = 5)
    public String status;
    @Column(nullable = false, length = 10)
    public String categoria;

}
