package canard.intern.post.following.backend.dto.survey;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// ALERTE  IL EST INUTILE POUR LINSTANT!!
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChoiceDto {

    private Integer id;

    @NotNull
    @NotBlank
    private String name;

}

