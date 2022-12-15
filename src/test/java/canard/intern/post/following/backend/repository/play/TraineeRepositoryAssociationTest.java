package canard.intern.post.following.backend.repository.play;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;


//this is not  a unit test
//goal: how to deal with association Trainee => poe
@DataJpaTest
//@ActiveProfiles("testu") pas besoin si on utilise l'annotation suivante
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TraineeRepositoryAssociationTest {
    @Autowired
    TraineeRepository traineeRepository;
    @Autowired
    private PoeRepository poeRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void traineeWithPoe() {
        var trainee = traineeRepository.findById(7).get();

        var poe = trainee.getPoe();
        System.out.println(trainee);
        System.out.println(poe);
    }

    // sol 1 (intermédiaire)
    @Test
    void poeWithTraineeHibernate() {

        int idPoe = 6;
        var poe = poeRepository.findById(idPoe).get();
        System.out.println(poe);

        // SQL: select * from trainees where poe_id = 2;

        // SQL => HQL/JPQL
        var sql = "select t from Trainee t  where t.poe.id = ?1";   // nom de la classe au lieu du nom de la table, t au lieu de *, t.poe.id au lieu de t.poe_id
        var trainees = entityManager.createQuery(sql, Trainee.class)
                .setParameter(1, idPoe)
                .getResultList();
      //  System.out.println(trainees);
        for (var trainee:trainees){
            System.out.println("\t- "+trainee);
        }
        //OU
        trainees.forEach((t)->System.out.println("\t- "+t));


    }
    // sol 2 (finale) requette sur trainee
    @Test
    void poeWithTraineesWithJpaRepository(){
        int idPoe = 6;
        var trainees =          traineeRepository.findByPoeId(idPoe);
        trainees.forEach((t)->System.out.println("\t- "+t));
    }
}
