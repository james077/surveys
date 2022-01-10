package com.robinfood.surveysapi.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Survey {
  
  @Id
  @Column(name = "survey_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;

  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "survey")
  private List<Question> questions;

  public void setAllJoins() {
    if (questions != null) {
      questions.forEach(a -> {
        a.setSurvey(this);
      });
    }
  }

}
