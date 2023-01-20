package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.survey.SurveyDto;
import canard.intern.post.following.backend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {


    @Autowired
    private SurveyService surveyService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<SurveyDto> getAll() throws ResponseStatusException {
        return  surveyService.getAll();

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SurveyDto getById(@PathVariable("id") Integer id) {
        var optSurveyDto =surveyService.getById(id);
        if(optSurveyDto.isPresent()){
            return optSurveyDto.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto addSurvey (@Valid @RequestBody SurveyDto surveyDto)  {

        var optSurveyDtoDb =surveyService.create(surveyDto);
        if(optSurveyDtoDb.isPresent()){
            return optSurveyDtoDb.get();
        }else{
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }


    @PatchMapping ("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SurveyDto updateSurvey (@Valid @RequestBody SurveyDto surveyDto,@PathVariable("id") Integer id)  {
        var optSurveyDtoDb =surveyService.update(id,surveyDto);
        if(optSurveyDtoDb.isPresent()){
            return optSurveyDtoDb.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping ("/change-questions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SurveyDto changeQuestions ( @RequestBody List<Integer> questionIds,@PathVariable("id") Integer id)  {
        var optSurveyDtoDb =surveyService.changeQuestions(id,questionIds);
        if(optSurveyDtoDb.isPresent()){
            return optSurveyDtoDb.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSurvey (@PathVariable("id") Integer id)  {

        if(! surveyService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        };
    }
}
