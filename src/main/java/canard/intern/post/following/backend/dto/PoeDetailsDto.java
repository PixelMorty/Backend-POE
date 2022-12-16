package canard.intern.post.following.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

// @Builder NE MARCHE PAS AVEC LHERITAGGE!!
@SuperBuilder // METTRE DES DEUX COTES, CLASSE FILLE ET PARENT
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class PoeDetailsDto extends PoeDto{
    private List<TraineeDto> trainees;
}
