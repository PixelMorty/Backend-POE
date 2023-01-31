package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.PoeDetailsDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.SurveyRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.PoeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PoeServiceJpa implements PoeService {

    @Autowired
    PoeRepository poeRepository;
    @Autowired
    TraineeRepository traineeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<PoeDto> getAll(){
        return poeRepository.
                        findAll().stream().map((pe)->modelMapper.map(pe,PoeDto.class)).toList();
    }

    @Override
    public Optional<PoeDetailsDto> getById(int id){
        // on récup la un optional de Trainee


        return poeRepository.findById(id)
                .map((pe)-> {
                    var trainees = traineeRepository.findByPoeId(pe.getId()).stream().map((te)->
                            modelMapper.map(te,TraineeDto.class)
                    ).toList();

                    var poeDetailDto = modelMapper.map(pe, PoeDetailsDto.class);//transfo que si y'a kkchose dans la boite
                    poeDetailDto.setTrainees(trainees);
                    return  poeDetailDto;
                });
    }

    @Override
    public Set<PoeDto> getByTitleContaining(String titlePartial){
        return null;
    }


    @Override
    public PoeDto create(PoeDto poeDto){
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Poe poeDb;
        try {
            poeDb= poeRepository.save(modelMapper.map(poeDto, Poe.class));
        }catch(Exception e){
            throw (new UpdateException("poe couldn't be saved",e));
        }

        return modelMapper.map(poeDb, PoeDto.class);
    }


    @Override
    public Optional<PoeDto> update(int id, PoeDto poeDto){
        poeDto.setId(id);
        var optPoe = poeRepository.findById(id) ;
        try {
            if (optPoe.isPresent()) {
                var poeDb= optPoe.get();
                modelMapper.map(poeDto, poeDb); // change traineeDb in hibernate cache
                poeRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(poeDb, PoeDto.class));
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new UpdateException("poe couldn't be updated",e);
        }

    }


    @Override
    public boolean delete(int id){
        try {
            if (poeRepository.findById(id).isPresent()) {
                poeRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("poe couldn't be deleted",e));
            //return false;
        }
    }

    @Override
    public PoeDetailsDto addSurveyFirstMonth(Integer poeId,Integer surveyId) {

        try {
            var OptPoeEntity = poeRepository.findById(poeId);
                if (OptPoeEntity.isEmpty()) {
                    throw (new UpdateException("no poe with id : " + poeId));
                } else {

                    var surveyEntity = surveyRepository.findById(surveyId);
                    if (surveyEntity.isEmpty()){
                        throw (new UpdateException("no survey at id : " + surveyId));
                    }else{
                        OptPoeEntity.get().setSurveyFirstMonth(surveyEntity.get());
                    }

                }
                poeRepository.flush(); // synchro et force l'update en sql
                var poeDetailsDto=modelMapper.map(OptPoeEntity.get(), PoeDetailsDto.class);
                var trainees = traineeRepository.findByPoeId(poeDetailsDto.getId()).stream().map((te)->
                        modelMapper.map(te,TraineeDto.class)
                    ).toList();
                poeDetailsDto.setTrainees(trainees);
                return poeDetailsDto;
            }catch(Exception e){// autres problèmes
                throw (new UpdateException("poe survey couldn't be updated ",e));
                //return false;
            }
    }

    @Override
    public PoeDetailsDto addSurveySecondMonth(Integer poeId,Integer surveyId) {
        try {
            var OptPoeEntity = poeRepository.findById(poeId);
            if (OptPoeEntity.isEmpty()) {
                throw (new UpdateException("no poe with id : " + poeId));
            } else {

                var surveyEntity = surveyRepository.findById(surveyId);
                if (surveyEntity.isEmpty()){
                    throw (new UpdateException("no survey at id : " + surveyId));
                }else{
                    OptPoeEntity.get().setSurveySecondMonth(surveyEntity.get());
                }

            }
            poeRepository.flush(); // synchro et force l'update en sql
            var poeDetailsDto=modelMapper.map(OptPoeEntity.get(), PoeDetailsDto.class);
            var trainees = traineeRepository.findByPoeId(poeDetailsDto.getId()).stream().map((te)->
                    modelMapper.map(te,TraineeDto.class)
            ).toList();
            poeDetailsDto.setTrainees(trainees);
            return poeDetailsDto;
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("poe survey couldn't be updated ",e));
            //return false;
        }
    }

    @Override
    public PoeDetailsDto addSurveyThirdMonth(Integer poeId,Integer surveyId) {
        try {
            var OptPoeEntity = poeRepository.findById(poeId);
            if (OptPoeEntity.isEmpty()) {
                throw (new UpdateException("no poe with id : " + poeId));
            } else {

                var surveyEntity = surveyRepository.findById(surveyId);
                if (surveyEntity.isEmpty()){
                    throw (new UpdateException("no survey at id : " + surveyId));
                }else{
                    OptPoeEntity.get().setSurveyThirdMonth(surveyEntity.get());
                }

            }
            poeRepository.flush(); // synchro et force l'update en sql
            var poeDetailsDto=modelMapper.map(OptPoeEntity.get(), PoeDetailsDto.class);
            var trainees = traineeRepository.findByPoeId(poeDetailsDto.getId()).stream().map((te)->
                    modelMapper.map(te,TraineeDto.class)
            ).toList();
            poeDetailsDto.setTrainees(trainees);
            return poeDetailsDto;
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("poe survey couldn't be updated ",e));
            //return false;
        }
    }

    @Override
    public PoeDetailsDto removeSurveys(Integer poeId) {
        try {
            var OptPoeEntity = poeRepository.findById(poeId);
            if (OptPoeEntity.isEmpty()) {
                throw (new UpdateException("no poe with id : " + poeId));
            } else {
                OptPoeEntity.get().setSurveyFirstMonth(null);
                OptPoeEntity.get().setSurveySecondMonth(null);
                OptPoeEntity.get().setSurveyThirdMonth(null);


            }
            poeRepository.flush(); // synchro et force l'update en sql
            var poeDetailsDto=modelMapper.map(OptPoeEntity.get(), PoeDetailsDto.class);
            var trainees = traineeRepository.findByPoeId(poeDetailsDto.getId()).stream().map((te)->
                    modelMapper.map(te,TraineeDto.class)
            ).toList();
            poeDetailsDto.setTrainees(trainees);
            return poeDetailsDto;
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("poe 's survey couldn't be removed ",e));
            //return false;
        }
    }


}
