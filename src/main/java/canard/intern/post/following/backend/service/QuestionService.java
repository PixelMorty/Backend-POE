package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.survey.QuestionDto;

import java.util.Optional;
import java.util.Set;

public interface QuestionService {
    Optional<Set<QuestionDto>> getAll();

    Optional<QuestionDto> getById(Integer id);

    QuestionDto create(QuestionDto questionDto);

    void delete(Integer id);

    void update(Integer id, QuestionDto questionDto);
}