package canard.intern.post.following.backend.dto;

import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Builder@ToString
public class PoeTypeCountDto {

    private String poeType;
    private Long countPoe;
}
