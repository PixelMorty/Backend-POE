package canard.intern.post.following.backend.entity;


import canard.intern.post.following.backend.enums.Gender;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name="trainees")// personnaliser  le nom de la table
public class Trainee {
    @Id      // indiquer que c'est la clé primaire (ID, prendre le bon (javax.persistence,jpa)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // affiner la stratégie d'initiation de la clé primaire
    private Integer id;

    private String lastname;

    private String firstname;

    private Gender gender;

    private LocalDate birthdate;

    private String phoneNumber;

    private String email;
}
