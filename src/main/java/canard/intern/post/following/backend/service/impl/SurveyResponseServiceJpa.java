package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.survey.SurveyDto;
import canard.intern.post.following.backend.dto.survey.SurveyResponseDto;
import canard.intern.post.following.backend.entity.QuestionResponse;
import canard.intern.post.following.backend.entity.Survey;
import canard.intern.post.following.backend.entity.SurveyResponse;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.*;
import canard.intern.post.following.backend.service.SurveyResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyResponseServiceJpa implements SurveyResponseService {


    @Autowired
    private SurveyResponseRepository surveyResponseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestionResponseRepository questionResponseRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;

    @Override
    public List<SurveyResponseDto> getAll(){
        return surveyResponseRepository.findAll().stream().map(
                (surveyResponseEntity)-> modelMapper.map(surveyResponseEntity, SurveyResponseDto.class)
        ).toList();
    }


    @Override
    public Boolean delete(Integer id){
        //cherche s'il existe

        try {
            if(surveyResponseRepository.findById(id).isPresent()){
                // si oui on supprime et renvoie vrai
                surveyResponseRepository.deleteById(id);

                return true;
            }else{

                // faux sinon
                return false;
            }

        }catch(Exception e){// autres problèmes
            throw (new UpdateException("SurveyResponse couldn't be deleted",e));
            //return false;
        }
    }

    @Override
    public Optional<SurveyResponseDto> getById(Integer id){

        var optSurveyResponse = surveyResponseRepository.findById(id);
        if(optSurveyResponse.isPresent()){
            return Optional.of( modelMapper.map(optSurveyResponse.get(), SurveyResponseDto.class));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<SurveyResponseDto> update (Integer id,SurveyResponseDto surveyResponseDto){
        var optSurveyResponse = surveyResponseRepository.findById(id);

        // verifier que y'a l'entité dans l'optionel
        // si présent mettre a jour
        if(optSurveyResponse.isPresent()){

            //mettre a jour
            // optSurvey.get() récupère l' ENtité que y'a dedans

            var surveyResponseEntity = optSurveyResponse.get() ;


            // on la modifie comme on veut qu'elle soit
            surveyResponseDto.setId(id);
            modelMapper.map(surveyResponseDto,surveyResponseEntity);


            // et flush (synchronisation)

            surveyResponseRepository.flush();



            //
            // renvoyer un optionel de ce qui a mtn dans la base de donnée

            return Optional.of(modelMapper.map(surveyResponseEntity, SurveyResponseDto.class));

            // sinon renvoyer un optinel vide
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<SurveyResponseDto>  changeQuestionResponse(Integer id,List<Integer> questionResponseIds){
        StringBuilder questionErrorMessage= new StringBuilder("");
        try {
            var optSurveyResponseDb = surveyResponseRepository.findById(id);

            if(optSurveyResponseDb.isPresent()){

                SurveyResponse surveyResponseDb =optSurveyResponseDb.get();

                var questionsResponse = questionResponseIds.stream().map((questionId)->{
                            var optquestionTemp = questionResponseRepository.findById(questionId);
                            if(optquestionTemp.isPresent()){
                                return optquestionTemp.get();
                            }else{
                                questionErrorMessage.append("La question d'id : ").append(questionId).append(" n'existe pas dans la base de données");
                                return optquestionTemp.get();
                            }
                        }
                ).collect(Collectors.toSet());
                surveyResponseDb.setQuestionResponses(questionsResponse);

                surveyResponseRepository.flush();
                return Optional.of( modelMapper.map(surveyResponseDb, SurveyResponseDto.class));
            }else{
                return Optional.empty();
            }

        }catch(Exception e){
            throw (new UpdateException("Survey's questionReponses coulnd't be changed " + questionErrorMessage ,e));
            //return false;
        }

    }

    @Override
    public Optional<SurveyResponseDto> create(SurveyResponseDto surveyResponseDto){

   //     try{
            // créer un surveyResponse entity depuis le dto
            var surveyResponseEntity = modelMapper.map(surveyResponseDto, SurveyResponse.class);

        // set survey:
        var optSurveyEntity = surveyRepository.findById(surveyResponseEntity.getSurvey().getId());
        if (optSurveyEntity.isEmpty()){
                throw (new UpdateException("No survey indicated or it doesn't exist in database"));
            }else{
            surveyResponseEntity.setSurvey(optSurveyEntity.get());
        }

        var questionResponsesEntities = new HashSet<QuestionResponse>();
        //création des questionReponse
        surveyResponseDto.getQuestionResponses().forEach((qr)-> {
            var questionResponse = new QuestionResponse();
            questionResponse.setFreeResponse(qr.getFreeResponse());
            questionResponse.setYes_No(qr.getYes_No());
            // question:
            var optQuestion=questionRepository.findById(qr.getQuestion().getId());
            if (optQuestion.isEmpty()){
                throw(new UpdateException("it is missing a question_id for a question or this question doesn't exist in database"));
            }else{
                questionResponse.setQuestion(optQuestion.get());
            }
            // choice:
            var optChoice=choiceRepository.findById(qr.getChoice().getId());
            if (optChoice.isEmpty()){
                throw(new UpdateException("it is missing a choice_id for a question or this choice doesn't exist in database"));
            }else{
                questionResponse.setChoice(optChoice.get());
            }
            questionResponsesEntities.add(questionResponse);

        });
        surveyResponseEntity.setQuestionResponses(questionResponsesEntities);



            // envoyer via le repository
            var surveyResponsedb = surveyResponseRepository.save(surveyResponseEntity);

            //verifier que ca s'est bien passé
            //récuperer le Survey de la base de donnée
            //fait ligne 109
            // transformer le survey en survey dto
            var surveyResponsedtoDb = modelMapper.map(surveyResponsedb,SurveyResponseDto.class);
            return Optional.of(surveyResponsedtoDb);

            // renvoyer survey dto
//        }catch(Exception e){
//            throw (new UpdateException("SurveyResponse couldn't be created",e));
//            //return false;
//        }

    }


}
