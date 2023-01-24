package canard.intern.post.following.backend.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table (name = "surveys")
public class Survey {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 50)
    private String title;

    @ManyToMany
    @JoinTable(name = "survey_contrains_question",
            joinColumns = @JoinColumn(name = "survey_id"), // FK to this class (survey)
            inverseJoinColumns = @JoinColumn(name = "question_id") // FK to other class (Question)
    )

    private Set<Question> questions = new HashSet<>();

}
