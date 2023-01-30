package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.survey.SurveyResponseDto;

import java.util.List;
import java.util.Optional;

public interface SurveyResponseService {

    List<SurveyResponseDto> getAll();

    Boolean delete(Integer id);


    Optional<SurveyResponseDto> getById(Integer id);

    Optional<SurveyResponseDto> update(Integer id, SurveyResponseDto surveyDto);

    Optional<SurveyResponseDto> create(SurveyResponseDto surveyDto);

    Optional<SurveyResponseDto> changeQuestionResponse(Integer id, List<Integer> questionIds);
}
