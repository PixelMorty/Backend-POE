package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionServiceJpa implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Optional<Set<QuestionDto>> getAll() {
        var questionsDbDto = questionRepository.findAll().stream().map(
                (Question question)-> {
                    return modelMapper.map(question, QuestionDto.class);
                }
        ).collect(Collectors.toSet());
        return Optional.of(questionsDbDto);
    }

    @Override
    public Optional<QuestionDto> getById(Integer id) {
        //TODO
        return Optional.empty();
    }

    @Override
    public QuestionDto create(QuestionDto questionDto) {
        try {
           var question = questionRepository.save(modelMapper.map(questionDto,Question.class));
           return modelMapper.map( question, QuestionDto.class);
        }catch(Exception e){
            throw (new UpdateException("Question couldn't be saved",e));
        }
    }

    @Override
    public void delete(Integer id) {

        try{
            //TODO
        }
        catch(Exception e){
            throw (new UpdateException("Question couldn't be deleted",e));
        }
    }

    @Override
    public void update(Integer id, QuestionDto questionDto) {

    }
}
