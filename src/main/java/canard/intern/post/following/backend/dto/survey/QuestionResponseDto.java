package canard.intern.post.following.backend.dto.survey;

import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionResponseDto {

    private Integer id ;



    private String freeResponse;

    private Boolean Yes_No = false;


    private ChoiceDto choice;


    @NotNull
    private QuestionDto question;

}
