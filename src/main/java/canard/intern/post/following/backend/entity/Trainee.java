package canard.intern.post.following.backend.entity;


import canard.intern.post.following.backend.enums.Gender;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
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
