package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.survey.QuestionDto;
import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.ChoiceRepository;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceJpa implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ChoiceRepository choiceRepository;

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
    public Optional<QuestionDto> update(Integer id, QuestionDto questionDto) {
        // var question = modelMapper.map(questionDto, Question.class);
        // question = questionRepository.save(question);
        // Chercher dans la BDD la question qui correspond à l'id et le sotcker dans questionEntity
        // On transforme questionDTo en questionMappee
        // questionEntity = questionMappee
        // questionRepository.flush()

        var optQuestionEntity = questionRepository.findById(id);

        if (optQuestionEntity.isPresent()) {
            var questionEntity = optQuestionEntity.get();
            questionDto.setId(id);
//TODO PKKIFERIEN
//            questionEntity.getChoices().stream().forEach((choice)->
//            {
//                choiceService.delete(choice.getId());
//
//            });

            choiceRepository.deleteAllInBatch(questionEntity.getChoices());
            questionEntity.getChoices().clear();
            modelMapper.map(questionDto,questionEntity);
            questionRepository.flush();

            return Optional.of(modelMapper.map(questionEntity, QuestionDto.class));
        } else {
            return Optional.empty();
        }


    }
}
