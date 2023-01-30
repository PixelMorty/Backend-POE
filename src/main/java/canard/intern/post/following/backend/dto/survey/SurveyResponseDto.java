package canard.intern.post.following.backend.dto.survey;

import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.entity.QuestionResponse;
import canard.intern.post.following.backend.entity.Survey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@ToString
@Getter
@Setter
public class SurveyResponseDto {



    private Integer id ;

    @NotNull
    private SurveyDto survey;

    private Set<QuestionResponseDto> questionResponses = new HashSet<>();

}
