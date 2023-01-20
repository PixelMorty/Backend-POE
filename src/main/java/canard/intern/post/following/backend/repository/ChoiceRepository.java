package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice,Integer> {
}
