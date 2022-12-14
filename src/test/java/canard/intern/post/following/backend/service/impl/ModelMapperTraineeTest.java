package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import  static org.junit.jupiter.api.Assertions.*;

public class ModelMapperTraineeTest {

    static ModelMapper modelMapper;;
    @BeforeAll
    static void initMapper(){
        modelMapper= new ModelMapper();
    }


    @Test
void map_traineeToDto() {
        int id = 12345;
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";
        var traineeEntity = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

    var traineeDto =modelMapper.map(traineeEntity, TraineeDto.class);
    assertNotNull(traineeDto);
    assertSame(TraineeDto.class,traineeDto.getClass());
    assertAll(
            () -> assertEquals(traineeEntity.getEmail(),traineeDto.getEmail()),
            () -> assertEquals(traineeEntity.getFirstname(),traineeDto.getFirstname()),
            () -> assertEquals(traineeEntity.getId(),traineeDto.getId()),
            () -> assertEquals(traineeEntity.getBirthdate(),traineeDto.getBirthdate()),
            () -> assertEquals(traineeEntity.getLastname(),traineeDto.getLastname()),
            () -> assertEquals(traineeEntity.getGender(),traineeDto.getGender())
    );
    }


    @Test
    void TraineeDto_To_Trainee() {
        int id = 12345;
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        String email = "james.bond@im6.org";
        String phoneNumber = "+33700700700";
        var traineeDto = TraineeDto.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        var traineeEntity =modelMapper.map(traineeDto, Trainee.class);
        assertNotNull(traineeEntity);
        assertSame(Trainee.class,traineeEntity.getClass());
        assertAll(
                () -> assertEquals(traineeDto.getEmail(),traineeEntity.getEmail()),
                () -> assertEquals(traineeDto.getFirstname(),traineeEntity.getFirstname()),
                () -> assertEquals(traineeDto.getId(),traineeEntity.getId()),
                () -> assertEquals(traineeDto.getBirthdate(),traineeEntity.getBirthdate()),
                () -> assertEquals(traineeDto.getLastname(),traineeEntity.getLastname()),
                () -> assertEquals(traineeDto.getGender(),traineeEntity.getGender())
        );
    }


}
