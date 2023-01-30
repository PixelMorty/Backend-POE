package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.survey.SurveyDto;
import canard.intern.post.following.backend.dto.survey.SurveyResponseDto;
import canard.intern.post.following.backend.service.SurveyResponseService;
import canard.intern.post.following.backend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/response")
public class SurveyResponseController {


    @Autowired
    private SurveyResponseService surveyResponseService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<SurveyResponseDto> getAll() throws ResponseStatusException {
        return  surveyResponseService.getAll();

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SurveyResponseDto getById(@PathVariable("id") Integer id) {
        var optSurveyResponseDto =surveyResponseService.getById(id);
        if(optSurveyResponseDto.isPresent()){
            return optSurveyResponseDto.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyResponseDto addSurvey (@Valid @RequestBody SurveyResponseDto surveyResponseDto)  {

        var optSurveyResponseDtoDb =surveyResponseService.create(surveyResponseDto);
        if(optSurveyResponseDtoDb.isPresent()){
            return optSurveyResponseDtoDb.get();
        }else{
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }





    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResponseSurvey (@PathVariable("id") Integer id)  {

        if(!surveyResponseService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        };
    }

}
