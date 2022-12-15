package canard.intern.post.following.backend.entity;


import canard.intern.post.following.backend.enums.Gender;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString(exclude = "poe") // exclure la propriété poe du tostring ;  FAIRE CA A CHAQUE FOIS QUON A UNE JOINTURE
@Table(name="trainees")// personnaliser  le nom de la table
public class Trainee {
    @Id      // indiquer que c'est la clé primaire (ID, prendre le bon (javax.persistence,jpa)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // affiner la stratégie d'initiation de la clé primaire
    private Integer id;

    @Column(length=50, name = "lastname",nullable = false) // personnalisation de ce que ça va donner en longeur et le nom par exemple
    private String lastname;

    @Column(length=50, name = "firstname",nullable = false )
    private String firstname;

    @Enumerated(EnumType.STRING)
    @Column(length=1)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length=15)
    private String phoneNumber;

    @Column(nullable = false,length=100,unique = true)
    private String email;

//   @Transient // t'occupe pas de cette colonne dans la base de donnée (pour quand on a pas encore tout branché)
   @ManyToOne//(fetch = FetchType.LAZY)  // indiquer   il sera lié à des poes qui eux auront plusieurs stagiaires  ;exprime dejà le nullable
   // PAS BESOINS DAUTRE CHOSE, il trouve tout seul l'objet associé. si c'était kkchoseToMany là faudrait renseigner des trucs en plus
   // grace à ça on peut faire le test traineeWithPoe() de traineeRepositoryAssociationTest
   // fetch = FetchType.LAZY permet de ne pas afficher la poe tant que je m'y interesse pas
  // @JoinColumn(name = "id_poe")// pour tuner : nom de colonne particulière
    private Poe poe;

}
