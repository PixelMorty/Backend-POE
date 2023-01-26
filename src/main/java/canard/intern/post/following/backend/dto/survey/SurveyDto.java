package canard.intern.post.following.backend.dto.survey;

import canard.intern.post.following.backend.entity.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@ToString
@Getter
@Setter
public class SurveyDto {


   // private List<Integer> idPositions;
    private Integer id;


    private String title="Titre";

    private Set<Question> questions = new HashSet<>();

}
