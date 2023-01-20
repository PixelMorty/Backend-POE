package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.survey.SurveyDto;
import canard.intern.post.following.backend.entity.Survey;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.SurveyRepository;
import canard.intern.post.following.backend.service.SurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SurveyServiceJpa implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SurveyDto> getAll(){
        return surveyRepository.findAll().stream().map(
                (surveyEntity)-> modelMapper.map(surveyEntity, SurveyDto.class)
        ).toList();
    }


    @Override
    public Boolean delete(Integer id){
        //cherche s'il existe

        try {
            if(surveyRepository.findById(id).isPresent()){
                // si oui on supprime et renvoie vrai
                surveyRepository.deleteById(id);

                return true;
            }else{

                // faux sinon
                return false;
            }

        }catch(Exception e){// autres problèmes
            throw (new UpdateException("Survey couldn't be deleted",e));
            //return false;
        }
    }

    @Override
    public Optional<SurveyDto> getById(Integer id){

        var optSurvey = surveyRepository.findById(id);
        if(optSurvey.isPresent()){
            return Optional.of( modelMapper.map(optSurvey.get(), SurveyDto.class));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<SurveyDto> update (Integer id,SurveyDto surveyDto){
        var optSurvey = surveyRepository.findById(id);

        // verifier que y'a l'entité dans l'optionel
        // si présent mettre a jour
        if(optSurvey.isPresent()){

            //mettre a jour
                // optSurvey.get() récupère l' ENtité que y'a dedans

            var surveyEntity = optSurvey.get() ;


                // on la modifie comme on veut qu'elle soit
            surveyDto.setId(id);
            modelMapper.map(surveyDto,surveyEntity);


                // et flush (synchronisation)

            surveyRepository.flush();



            //
            // renvoyer un optionel de ce qui a mtn dans la base de donnée

            return Optional.of(modelMapper.map(surveyEntity, SurveyDto.class));

            // sinon renvoyer un optinel vide
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<SurveyDto> create(SurveyDto surveyDto){

       try{
           //inscrire dans la base donnée
           // transfo surveydto en survey
           var survey = modelMapper.map(surveyDto, Survey.class);
           // envoyer via le repository
           var surveydb = surveyRepository.save(survey);

           //verifier que ca s'est bien passé
           //récuperer le Survey de la base de donnée
           //fait ligne 109
           // transformer le survey en survey dto
           var surveydtoDb = modelMapper.map(surveydb,SurveyDto.class);
           return Optional.of(surveydtoDb);

           // renvoyer survey dto
       }catch(Exception e){
           throw (new UpdateException("Survey couldn't be created",e));
           //return false;
        }

    }



}
