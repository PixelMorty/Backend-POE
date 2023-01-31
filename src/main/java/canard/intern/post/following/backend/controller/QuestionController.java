package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/questions")
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto addQuestion (@Valid @RequestBody QuestionDto questionDto)  {
        return questionService.create(questionDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion (@PathVariable("id") Integer id)  {

        if(! questionService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        };
    }

    @PatchMapping("/{id}")
    public Optional<QuestionDto> update(@PathVariable("id") Integer id, @Valid @RequestBody  QuestionDto questionDto){
        // TODO renvoyer throw new ResponseStatusException(HttpStatus.NOT_FOUND);  si questionService.update(id, questionDto) renvoie un optionel vide
        // TODO renvoyer questionService.update(id, questionDto)  sinon PAS DEUX FOIS LA REQUETTE UPDATE PLS
       return  questionService.update(id, questionDto);
    }



}
