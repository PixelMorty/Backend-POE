package canard.intern.post.following.backend.dto.survey;

import canard.intern.post.following.backend.entity.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
@ToString
@Getter
@Setter
public class SurveyDto {



    private Integer id;

    @NotNull
    @NotBlank
    private String title;

    private Set<Question> questions = new HashSet<>();

}