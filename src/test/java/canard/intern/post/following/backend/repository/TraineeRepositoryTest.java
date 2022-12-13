package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TraineeRepositoryTest {

    @Autowired
    TraineeRepository traineeRepository;


    @ParameterizedTest
    @CsvSource({
            "bond,james,M,1950-10-10,James.bond@mi6.com,0333443355",
            "Jean,neymar,X,2002-10-10,kk@ll,5412547877",
            "SansPhone,nhhr,F,1800-02-01,kk@ll,"
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
        assertNotNull(traineeRead);
        assertNotNull(trainee.getId());
    }

}