package com.conconcc.LikeLionWeek2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HelloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique =false, nullable = false)
    private String name;
    @Column(name="user_age")
    private Integer age;
}
