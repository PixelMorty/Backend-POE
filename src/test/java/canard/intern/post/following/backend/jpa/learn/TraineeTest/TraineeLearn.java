package canard.intern.post.following.backend.jpa.learn.TraineeTest;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;

//use h2 database (in memory, pas d evraie base de donnée) par defaut
@DataJpaTest // copain pour faire tests de jpa/hibernate quand on a un projet spring boot. CA SE FAIT TT SEUL
class TraineeLearn {
    //ORM HIbernate
    @Autowired
    EntityManager entityManager;

    @Test
    void save(){
        Trainee trainee =Trainee.builder()
                .lastname("Bond")
                .firstname("Jane")
                .gender(Gender.F)
                .birthdate(LocalDate.of(1944, 7, 15))
                .email("jane.bond@007.org")
                .phoneNumber("0627213176")
                .build()
                ;
        entityManager.persist(trainee); // génère un insert
        //System.out.println(trainee.toString());
    }


}