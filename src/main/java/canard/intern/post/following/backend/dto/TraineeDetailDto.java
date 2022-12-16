package canard.intern.post.following.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/// PENSER A METTRE TRAINEEDETAILDTO DANS LE CONTROLLER (MEME SI ça marche sans)
@SuperBuilder // METTRE DES DEUX COTES, CLASSE FILLE ET PARENT
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TraineeDetailDto extends TraineeDto{
    private PoeDto poe; // IMPORTANT DE PAS METTRE poeDto car sinon ModelMapper  fait pas le lien tt seul!!
}
