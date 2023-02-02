package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.entity.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Override
    List<Question> findAll();
    List<Question>  findAllByFavorite(boolean favorite);
}
