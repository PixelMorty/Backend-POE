package canard.intern.post.following.backend.dto.survey;

<<<<<<< HEAD

=======
>>>>>>> ff0f2076d4d60765475d1560acc163ecf7695afa
import canard.intern.post.following.backend.entity.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

<<<<<<< HEAD
import javax.persistence.ManyToMany;
=======
import javax.persistence.*;
>>>>>>> ff0f2076d4d60765475d1560acc163ecf7695afa
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
<<<<<<< HEAD

@Getter  @Setter
@ToString


public class SurveyDto {
    private  Integer id;

    @NotNull
    @NotBlank
    private String title;



=======
@ToString
@Getter
@Setter
public class SurveyDto {



    private Integer id;

@NotNull
@NotBlank
    private String title;

>>>>>>> ff0f2076d4d60765475d1560acc163ecf7695afa
    private Set<Question> questions = new HashSet<>();

}
