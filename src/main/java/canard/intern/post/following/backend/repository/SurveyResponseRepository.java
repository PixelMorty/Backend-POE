package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Survey;
import canard.intern.post.following.backend.entity.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse,Integer> {
}
