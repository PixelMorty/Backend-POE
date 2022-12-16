package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.entity.Trainee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoeDetailsDtoTest {

    @Test
    void builder(){
        var poeDetailsDto = PoeDetailsDto.builder()
                .id(1)
                .title("Java Fullstack")
                .trainees(List.of(
                        TraineeDto.builder()
                                .id(33)
                                .lastname("doe")
                                .firstname("billy")
                                .build(),
                        TraineeDto.builder()
                                .id(54)
                                .lastname("bond")
                                .firstname("James")
                                .build()
                )).build();
        System.out.println(poeDetailsDto);
        assertSame(PoeDetailsDto.class,poeDetailsDto.getClass());

    }
}