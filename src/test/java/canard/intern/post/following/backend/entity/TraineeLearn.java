package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

//use h2 database (in memory, pas d evraie base de donnée) par defaut
@DataJpaTest // copain pour faire tests de jpa/hibernate quand on a un projet spring boot. CA SE FAIT TT SEUL
class TraineeLearn {
    //ORM HIbernate
    @Autowired
    EntityManager entityManager;

    @Test
    void Mapping_OK_AllRequiredFields(){
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build()       ;

        entityManager.persist(trainee); // génère un insert
        entityManager.flush();// forcer une SYNCRHO  pour être sûr que l'id a été généré avant de le récup

        //verifiy id generated:

        var idGenerated = trainee .getId();
        assertNotNull(idGenerated);
        assertEquals(idGenerated,1);

        entityManager.clear(); // faire perdre la mémoire à hibernate
        var traineeRead = entityManager.find(Trainee.class,idGenerated);
        System.out.printf(traineeRead.toString());


            assertNotNull(traineeRead);
        // assert identiques
        //pour avoir toutes les erreurs et pas que la première:
        assertAll(
                () -> assertEquals(idGenerated,traineeRead.getId(),"trainee id"),
                () -> assertEquals(gender,traineeRead.getGender()),
                () -> assertEquals(birthdate,traineeRead.getBirthdate()),
                () -> assertEquals(firstname,traineeRead.getFirstname()),
                () -> assertEquals(email,traineeRead.getEmail()),
                () -> assertEquals(lastname,traineeRead.getLastname())
        );
    }
    @ParameterizedTest
    @EnumSource(  Gender.class)
    @NullSource // faire le test pr null aussi
    void Mapping_OK_genderNullable(Gender gender){
        String lastname = "Bond";
        String firstname = "James";

        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build()       ;

        entityManager.persist(trainee); // génère un insert
        entityManager.flush();// forcer une SYNCRHO  pour être sûr que l'id a été généré avant de le récup

        //verifiy id generated:

        var idGenerated = trainee .getId();
        assertNotNull(idGenerated);
        //assertEquals(idGenerated,1);

        entityManager.clear(); // faire perdre la mémoire à hibernate
        var traineeRead = entityManager.find(Trainee.class,idGenerated);


        assertNotNull(traineeRead);
        // assert identiques
        //pour avoir toutes les erreurs et pas que la première:
        assertAll(
                () -> assertEquals(idGenerated,traineeRead.getId(),"trainee id"),
                () -> assertEquals(gender,traineeRead.getGender()),
                () -> assertEquals(birthdate,traineeRead.getBirthdate()),
                () -> assertEquals(firstname,traineeRead.getFirstname()),
                () -> assertEquals(email,traineeRead.getEmail()),
                () -> assertEquals(lastname,traineeRead.getLastname())
        );
    }

    @ParameterizedTest
    @ValueSource(strings={"Phillipe","Bond","grdgezfsdvfgredfghgfdstrezersdfgfdsdfghgfds"})
    void Mapping_OK_Requiredlastname(String lastname){

        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950,1,6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build()       ;

        entityManager.persist(trainee); // génère un insert
        entityManager.flush();// forcer une SYNCRHO  pour être sûr que l'id a été généré avant de le récup

        //verifiy id generated:

        var idGenerated = trainee .getId();
        assertNotNull(idGenerated);
        //assertEquals(idGenerated,1);

        entityManager.clear(); // faire perdre la mémoire à hibernate
        var traineeRead = entityManager.find(Trainee.class,idGenerated);



        assertNotNull(traineeRead);
        // assert identiques
        //pour avoir toutes les erreurs et pas que la première:
        assertAll(
                () -> assertEquals(idGenerated,traineeRead.getId(),"trainee id"),
                () -> assertEquals(gender,traineeRead.getGender()),
                () -> assertEquals(birthdate,traineeRead.getBirthdate()),
                () -> assertEquals(firstname,traineeRead.getFirstname()),
                () -> assertEquals(email,traineeRead.getEmail()),
                () -> assertEquals(lastname,traineeRead.getLastname())
        );
    }


    @ParameterizedTest
    @ValueSource(strings={"grdgezfsdvfgredfghggggggggggggggggggggggggggggggggggfdstrezersdfgfdsdfghgfds",""})
    @NullSource // faire le test pr null aussi
    void Mapping_KO_Requiredlastname(String lastname){

            String firstname = "James";
            Gender gender = Gender.M;
            LocalDate birthdate = LocalDate.of(1950,1,6);
            String email = "james.bond@im6.org";
            String phoneNumber = "+33700700700";
            var trainee = Trainee.builder()
                    .lastname(lastname)
                    .firstname(firstname)
                    .gender(gender)
                    .birthdate(birthdate)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .build()       ;

                //vérifier qu'une instruction balance une erreur
        Exception exception = assertThrows(javax.persistence.PersistenceException.class, () -> {
            entityManager.persist(trainee);
        });

       // String expectedMessage = "For input string";
       // String actualMessage = exception.getMessage();

       // assertTrue(actualMessage.contains(expectedMessage));

        }


    @ParameterizedTest
    @CsvSource({
            "bond,james,M,1950-10-10,James.bond@mi6.com,0333443355",
            "Jean,neymar,X,2002-10-10,kk@ll,5412547877",
            "SansPhone,nhhr,F,1800-02-01,kk@ll,"
    })
    void Mapping_OK_AllParams(String lastname,String firstname,Gender gender,LocalDate birthdate,String email,String phoneNumber){

        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build()       ;

        entityManager.persist(trainee); // génère un insert
        entityManager.flush();// forcer une SYNCRHO  pour être sûr que l'id a été généré avant de le récup

        //verifiy id generated:

        var idGenerated = trainee .getId();
        assertNotNull(idGenerated);
        //assertEquals(idGenerated,1);

        entityManager.clear(); // faire perdre la mémoire à hibernate
        var traineeRead = entityManager.find(Trainee.class,idGenerated);



        assertNotNull(traineeRead);
        // assert identiques
        //pour avoir toutes les erreurs et pas que la première:
        assertAll(
                () -> assertEquals(idGenerated,traineeRead.getId(),"trainee id"),
                () -> assertEquals(gender,traineeRead.getGender()),
                () -> assertEquals(birthdate,traineeRead.getBirthdate()),
                () -> assertEquals(firstname,traineeRead.getFirstname()),
                () -> assertEquals(email,traineeRead.getEmail()),
                () -> assertEquals(lastname,traineeRead.getLastname())
        );
    }



}