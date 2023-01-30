package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name =  "question_responses")
public class QuestionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(length = 500)
    private String freeResponse;
    @Column(nullable = false)
    private Boolean Yes_No = false;


    @ManyToOne()
    private Choice choice;

    @ManyToOne()
    private Question question;


}
