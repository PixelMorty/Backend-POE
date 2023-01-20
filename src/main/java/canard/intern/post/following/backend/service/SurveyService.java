package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.survey.SurveyDto;

import java.util.Optional;
import java.util.Set;

public interface SurveyService {

    Optional<Set<SurveyDto>> getAll();
}
