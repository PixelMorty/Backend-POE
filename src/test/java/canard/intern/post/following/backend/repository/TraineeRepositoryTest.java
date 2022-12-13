package canard.intern.post.following.backend.repository;


import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TraineeRepositoryTest {

    @Autowired
    TraineeRepository traineeRepository;

  //  @Autowired
    //EntityManager entityManager;
    @Autowired
    TestEntityManager testEntityManager; //usefull wrapper manager only for test, vérifie les trucs dans la db


    //@Rollback(value = false)
    @ParameterizedTest
    @CsvSource({
            "bond,james,M,1950-10-10,James.bond@mi6.com,0333443355",
            "Jean,neymar,X,2002-10-10,kk@ll,5412547877",
            "SansPhone,nhhr,F,1900-02-01,kk@ll,"
    })
    void save_OK_AllParams(
            String lastname,
            String firstname,
            Gender gender,
            LocalDate birthdate,
            String email,
            String phoneNumber) {

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //traineeRepository.save(trainee);
        Trainee traineeRead = traineeRepository.saveAndFlush(trainee);

        // verif le retour est bon
        assertNotNull(traineeRead);
        assertNotNull(traineeRead.getId());

        // verif trainee bien modifié l'id
        assertNotNull(trainee.getId());
    }

    @Test
    void getAll(){
        // given save data in database
        var traineesDatabase= List.of(
                Trainee.builder()
                        .lastname("Solo")
                        .firstname("Han")
                        .birthdate(LocalDate.of(1950,11,5))
                        .email("han.solo@faucon-millenium.fr")
                        .build(),
                Trainee.builder()
                        .lastname("Bob")
                        .firstname("Han")
                        .birthdate(LocalDate.of(1950,11,5))
                        .email("puic@hgh.fr")
                        .gender(Gender.M)
                        .build(),
                Trainee.builder()
                        .lastname("Billy")
                        .firstname("Han")
                        .birthdate(LocalDate.of(1950,11,5))
                        .email("Billy@ff.fr")
                        .phoneNumber("+33707070707")
                        .build()

        );

        traineesDatabase.stream().forEach((t)->testEntityManager.persistAndFlush(t));


        // read all data in database
        var trainees   = traineeRepository.findAll();
        // verifier la taille du tableau et chaque élément
        assertEquals(trainees.size(),3);

        // faire test avec le le trainee correpsondant

        for (int increment=0; increment <=2;increment++) {
            var traineeDb = trainees.get(increment);
            var traineeEntry = traineesDatabase.get(increment);

            assertAll(
                    () -> assertEquals(traineeEntry.getGender(), traineeDb.getGender()),
                    () -> assertEquals(traineeEntry.getBirthdate(), traineeDb.getBirthdate()),
                    () -> assertEquals(traineeEntry.getFirstname(), traineeDb.getFirstname()),
                    () -> assertEquals(traineeEntry.getEmail(), traineeDb.getEmail()),
                    () -> assertEquals(traineeEntry.getLastname(), traineeDb.getLastname())
            );

        }
        //assertEquals(trainees,traineesDatabase);

    }
    @Test
    void findById_OK() {

        int id ;
        var traineeEntry = Trainee.builder()

                .lastname("Solo")
                .firstname("Han")
                .birthdate(LocalDate.of(1950, 11, 5))
                .email("han.solo@faucon-millenium.fr")
                .build();

        id = testEntityManager.persistAndGetId(traineeEntry,Integer.class  );
        testEntityManager.flush();
        testEntityManager.clear(); // empty hibernate cache Important pour pas tricher?

        var optTrainee = traineeRepository.findById(id);
        assertTrue(optTrainee.isPresent(),"mon trainee est présent");
    }

    @Test
    void findById_KO_notFound(){
            int id = 12322;
            var optTrainee = traineeRepository.findById(id);
            assertTrue(optTrainee.isEmpty());

    }

    @Test
    void findById_KO_multipleId(){

    }
}