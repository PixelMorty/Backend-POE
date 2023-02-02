package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name =  "questions")
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false, length = 50)
    private String title ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 13)
    private QuestionType questionType ;

    @Column()
    private Boolean favorite=false ;


    @OneToMany(fetch = FetchType.EAGER) // not ready yet
    @JoinColumn(name = "question_id")
    @Cascade({ org.hibernate.annotations.CascadeType.ALL})
    private Set<Choice> choices = new HashSet<>();


}
