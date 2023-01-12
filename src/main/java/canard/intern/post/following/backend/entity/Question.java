package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false, length = 50)
    private String title ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 13)
    private QuestionType questionType ;
}
