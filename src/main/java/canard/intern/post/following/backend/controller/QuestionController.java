package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Set<QuestionDto> getAll() throws ResponseStatusException{
        var optQuestionDtos =questionService.getAll();
        if(optQuestionDtos.isPresent()){
            return optQuestionDtos.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    QuestionDto getById(@PathVariable("id") Integer id ){
        var optQuestionDtos =questionService.getById(id);
        if(optQuestionDtos.isPresent()){
            return optQuestionDtos.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto addQuestion (@Valid @RequestBody QuestionDto questionDto)  {
        return questionService.create(questionDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion (@PathVariable("id") Integer id)  {
        questionService.delete(id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void update(@PathVariable("id") Integer id, @Valid @RequestBody  QuestionDto questionDto){
        questionService.update(id, questionDto);
    }



}
