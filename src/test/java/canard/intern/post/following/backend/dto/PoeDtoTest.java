
package canard.intern.post.following.backend.dto;

        import canard.intern.post.following.backend.enums.PoeType;
        import org.junit.jupiter.api.Test;

        import java.time.LocalDate;

        import static org.junit.jupiter.api.Assertions.*;

class PoeDtoTest {

    @Test
    void noArgsConstructor(){
        // when
        var poeDto = new PoeDto();

        // then/verify
        assertAll(
                () -> assertNull(poeDto.getId(), "poe id"),
                () -> assertNull(poeDto.getTitle(), "poe title")
        );
    }

    @Test
    void allArgsConstructor(){
        var poeDto = new PoeDto(
                1,
                "Java Fullstack",

                "POEI",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022,12,1));
    }


    @Test
    void builder(){
        var poeDto = PoeDto.builder()
                .id(1)
                .title("Java Fullstack")
                .build();
        System.out.println(poeDto);
        assertSame(PoeDto.class, poeDto.getClass());
        // TODO: assert all has been set

    }
}