package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.survey.SurveyDto;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> ff0f2076d4d60765475d1560acc163ecf7695afa
import java.util.Optional;
import java.util.Set;

public interface SurveyService {
<<<<<<< HEAD

    Optional<Set<SurveyDto>> getAll();
=======
    List<SurveyDto> getAll();
    Boolean delete(Integer id);
    Optional<SurveyDto> getById(Integer id);
    Optional<SurveyDto> update (Integer id,SurveyDto surveyDto);
    Optional<SurveyDto> create(SurveyDto surveyDto);
    Optional<SurveyDto>  changeQuestions(Integer id,List<Integer> questionIds);
>>>>>>> ff0f2076d4d60765475d1560acc163ecf7695afa
}
