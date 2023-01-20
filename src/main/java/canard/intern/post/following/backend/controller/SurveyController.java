package canard.intern.post.following.backend.controller;


import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.dto.survey.SurveyDto;
import canard.intern.post.following.backend.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/survey")

public class SurveyController {


    @Autowired
    private SurveyService surveyService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Set<SurveyDto> getAll() throws ResponseStatusException {
        var optSurveyDto = surveyService.getAll();
        if (optSurveyDto.isPresent()) {
            return optSurveyDto.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    QuestionDto getById(@PathVariable("id") Integer id) {
        var

        if (optSurveyDto.isPresent()) {
            return optSurveyDto.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


@PostMapping ("/add")
@ResponseStatus(HttpStatus.CREATED)

