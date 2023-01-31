package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name =  "survey_responses")
public class SurveyResponse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @ManyToOne
    private Survey survey;


    @OneToMany // not ready yet
    @JoinColumn(name = "survey_response_id")
    @Cascade({ org.hibernate.annotations.CascadeType.ALL})
    private Set<QuestionResponse> questionResponses = new HashSet<>();

    @OneToOne
    private Trainee trainee;
}
