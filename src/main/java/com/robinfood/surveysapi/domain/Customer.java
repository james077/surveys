package com.robinfood.surveysapi.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(length = 191, nullable = false)
    private String name;

    @Column(length = 191)
    private String email;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name = "customer_answer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers;

}
