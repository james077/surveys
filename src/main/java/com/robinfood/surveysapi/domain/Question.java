package com.robinfood.surveysapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer id;

    private String description;

    private Boolean open;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public void setAllJoins() {
        if (answers != null) {
            answers.forEach(a -> {
                a.setQuestion(this);
            });
        }
    }

}
