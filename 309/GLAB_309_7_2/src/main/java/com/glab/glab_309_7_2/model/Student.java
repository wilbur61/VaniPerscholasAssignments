package com.glab.glab_309_7_2.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.Entity;
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    private Long accountNo;
    private Long marks;
}
