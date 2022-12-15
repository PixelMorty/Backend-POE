package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee,Integer> { // en fait une fausse bdd grace à jpa (comme le entityManager dans TraineeLearn)

    List<Trainee> findByPoeId (int idPoe);  // rien de plus à faire spring à compris quoi faire tt seul
                                            // faire attention à bien mettre le nom de la variable en camel case dans le nom de la méthode
                                            // puis de mettre le nom de l'attribut associé à la variable (ici Poe.id) pour voir sur quoi cheercher chez Poe)

    List<Trainee>  findByLastnameContainingIgnoreCase(String lastname);
    List<Trainee> findByPoeTitleIgnoreCase(String title);



}
