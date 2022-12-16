package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.validator.DateLessThan;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PoeDto {

    private Integer id;
    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String poeType;

    @NotNull
    private LocalDate beginDate;

    @NotNull
    private LocalDate endDate;


}