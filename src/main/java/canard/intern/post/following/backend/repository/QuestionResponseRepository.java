package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.entity.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionResponseRepository extends JpaRepository<QuestionResponse,Integer>  {
    @Override
    List<QuestionResponse> findAll();
}

