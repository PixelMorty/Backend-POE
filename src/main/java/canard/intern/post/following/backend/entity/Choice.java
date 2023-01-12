package canard.intern.post.following.backend.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter @Setter
@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 50)
    private String name;
}
