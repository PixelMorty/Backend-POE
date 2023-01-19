package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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

        return questionRepository.findById(id)
                .map((question)-> modelMapper.map(question, QuestionDto.class));
    }


    @Override
    public boolean delete(Integer id) {

        try {
            if (questionRepository.findById(id).isPresent()) {
                questionRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw (new UpdateException("Question couldn't be deleted", e));
        }
    }



    @Override
    public QuestionDto create(QuestionDto questionDto) {


        try {
            var varIntermediaire = modelMapper.map(questionDto,Question.class);
            var question = questionRepository.save(varIntermediaire);
            return modelMapper.map( question, QuestionDto.class);
        }catch(Exception e){
            throw (new UpdateException("Question couldn't be saved",e));
        }
    }



    @Override
    public void update(Integer id, QuestionDto questionDto) {
//TODO
    }
}
