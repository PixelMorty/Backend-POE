package canard.intern.post.following.backend.dto.survey;

import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
public class QuestionDto {


    private Integer id ;

    @NotBlank
    @NotNull
    private String title ;


    private QuestionType questionType ;


    private Set<ChoiceDto> choices = new HashSet<>();

}
