package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.dto.IPoeTypeCountDto;
import canard.intern.post.following.backend.dto.PoeTypeCountDto;
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

        // Ces deux methodes suivantes sont surchargées (meme nom mais pas memes arguments)
    List<Poe> findByBeginDateBetween(LocalDate beginDate, LocalDate endDate);
        List<Poe> findByBeginDateBetween(LocalDate beginDate, LocalDate endDate, Sort sort);

//    SELECT
//            poe_type,
//    count(*)
//    FROM poes
//    GROUP BY poe_type;
        @Query("SELECT new canard.intern.post.following.backend.dto.PoeTypeCountDto( p.poeType, count(p))"+
                "  FROM Poe p   GROUP BY p.poeType") // on met un new pour pouvoir convertir, plus besoin d'allias
        // penser à faire l'import en écriant PoeTypeCountDto en autocomplétion et copier coller le chemin entier T_T
        // Ctrl F hibernate pour trouver le message d'erreur de la query
        List<PoeTypeCountDto> countByPoeType();

        // autre manière de faire la même chose:
    // on crée l'interface IPoeTypeCountDto
    // spring crée tt seul l'objet concret qui suit l'interface IPoeTypeCountDto
    // meilleure solution mais valable que avec spring, et tte les entreprises n'utilisent pas spring h24
        @Query("SELECT p.poeType as poeType, count(p) as  countPoe"+
                "  FROM Poe p   GROUP BY p.poeType")
        List<IPoeTypeCountDto> countByPoeType2();
}