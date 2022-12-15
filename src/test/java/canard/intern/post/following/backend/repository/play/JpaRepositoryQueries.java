package canard.intern.post.following.backend.repository.play;

import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

// this is not a unit tests
// play with jpa repository queries
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JpaRepositoryQueries {
    @Autowired
    TraineeRepository traineeRepository;
@Autowired
   PoeRepository poeRepository;

private static void displayArray(Collection<?> collection){
    collection.forEach((t)->System.out.println("\t- "+t));
}
    @Test
    void traineesByLastnamePartialIgnoreCase() {
        String partialName = "Croche";
        var trainees = traineeRepository.findByLastnameContainingIgnoreCase(partialName);
        trainees.forEach((t)->System.out.println("\t- "+t));
    }

    @Test
    void poesByTitleIgnoreCase() {
        String title = "Consultant Devops";

        // le mauvais: renvoie  plusieurs copies de la Poe
        var trainees = traineeRepository.findByPoeTitleIgnoreCase(title);
        trainees.forEach((t)->System.out.println("\t- "+t.getPoe()));
        // le bon: renvoie qu'une POe
        var poes = poeRepository.findByTitleIgnoreCase(title);
        poes.forEach((t)->System.out.println("\t- "+t));


    }


    @Test
    void poesByType() {
       // PoeType poeType = PoeType.POEC;
         String poeType = "POEC";

        var poes = poeRepository.findByPoeTypeIgnoreCase(poeType);
        poes.forEach((t)->System.out.println("\t- "+t));

    }

    @Test
    void poesStartingYear(){
        int year = 2022;
        var poes = poeRepository.findByBeginDateGreaterThan(LocalDate.of(year,1,1));
        poes.forEach((t)->System.out.println("\t- "+t));
    }

    @Test
    void traineesByPoeTitleIgnoreCase() {
        String title = "Consultant Cyber Sécurité";
        //String title =  "Java Fullstack";
        var trainees = traineeRepository.findByPoeTitleIgnoreCase(title);

        displayArray(trainees);
    }
}
