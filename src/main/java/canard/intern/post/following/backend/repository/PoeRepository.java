package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Trainee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PoeRepository extends JpaRepository<Poe,Integer> {
    List<Poe> findByTitleIgnoreCase(String title);
    List<Poe>  findByPoeTypeIgnoreCase(String poeType);

    List<Poe> findByBeginDateGreaterThan(LocalDate localDate);

    //definir sa propre requette
    @Query("SELECT p FROM Poe p WHERE EXTRACT (YEAR FROM p.beginDate) = :year ORDER BY p.beginDate")
    List<Poe> findByBeginDateInYear(int year);

        List<Poe> findByTitleIgnoreCaseOrderByBeginDate(String title);

        List<Poe> findByBeginDateBetween(LocalDate beginDate, LocalDate endDate, Sort sort);

}