package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PoeRepository extends JpaRepository<Poe,Integer> {
    List<Poe> findByTitleIgnoreCase(String title);
    List<Poe>  findByPoeTypeIgnoreCase(String poeType);

    List<Poe> findByBeginDateGreaterThan(LocalDate localDate);

}