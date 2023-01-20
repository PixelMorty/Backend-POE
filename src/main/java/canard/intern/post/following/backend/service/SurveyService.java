package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.survey.SurveyDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SurveyService {
    List<SurveyDto> getAll();
    Boolean delete(Integer id);
    Optional<SurveyDto> getById(Integer id);
    Optional<SurveyDto> update (Integer id,SurveyDto surveyDto);
    Optional<SurveyDto> create(SurveyDto surveyDto);
}
