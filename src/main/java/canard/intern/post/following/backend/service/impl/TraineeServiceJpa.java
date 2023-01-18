package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.TraineeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TraineeServiceJpa implements TraineeService {

    @Autowired
private
    TraineeRepository traineeRepository;
@Autowired
    PoeRepository poeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<TraineeDto> getAll() {
//        return traineeRepository.findAll().stream().map(
//                (t)->TraineeDto.builder()
//                        .id(t.getId())
//                        .lastname(t.getLastname())
//                        .email(t.getEmail())
//                        .firstname(t.getFirstname())
//                        .gender(t.getGender())
//                        .birthdate(t.getBirthdate())
//                        .phoneNumber(t.getPhoneNumber())
//                        .build()
//
//        ).toList();
        return traineeRepository.findAll().stream().map((t)->modelMapper.map(t,TraineeDto.class)).toList();
    }
    @Override
    public List<TraineeDetailDto> getAllDetailList() {

        return traineeRepository.findAll().stream().map((t)->modelMapper.map(t,TraineeDetailDto.class)).toList();
    }


    @Override
    public Optional<TraineeDetailDto> getById(int id) {
        // on récup la un optional de Trainee


        return traineeRepository.findById(id)

                .map((te)-> modelMapper.map(te, TraineeDetailDto.class));//transfo que si y'a kkchose dans la boite
                                                                        // va chercher tt seul l'attribut poe dans TraineeDetailDto



//        if (opTrainee.isPresent()){
//            // On convertit le Trainee en traineeDto s'il est pas vide
//            var traineeDto= modelMapper.map(opTrainee.get(),TraineeDto.class);
//
//            return Optional.of(traineeDto);
//        }
//        else{
//            return Optional.empty();
//        }
    }

    @Override
    public Set<TraineeDto> getByLastnameContaining(String lastnamePartial) {

        return null;
    }

    @Override
    public TraineeDetailDto create(TraineeDto traineeDto) {
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Trainee traineeDb;
        try {
            traineeDb= traineeRepository.save(modelMapper.map(traineeDto, Trainee.class));
        }catch(Exception e){
            throw (new UpdateException("trainee couldn't be saved",e));
        }

        return modelMapper.map(traineeDb, TraineeDetailDto.class);
    }

    @Override
    public Optional<TraineeDetailDto> update(int id, TraineeDto traineeDto) {
        traineeDto.setId(id);
        var optTraineeDb = traineeRepository.findById(id) ;
        try {
            if (optTraineeDb.isPresent()) {
                var traineeDb= optTraineeDb.get();
                modelMapper.map(traineeDto, traineeDb); // change traineeDb in hibernate cache
                traineeRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(traineeDb, TraineeDetailDto.class));
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new UpdateException("trainee couldn't be updated",e);
        }


    }

    @Override
    public boolean delete(int id) {
        try {
            if (traineeRepository.findById(id).isPresent()) {
                traineeRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }catch(Exception e){// autres problèmes
           throw (new UpdateException("Trainee couldn't be deleted",e));
            //return false;
        }
    }

    public Optional<TraineeDetailDto> setPoe(int idTrainee, int idPoe){
       return traineeRepository.findById(idTrainee)
                .flatMap(traineeEntity->                // permet de garder un seul niveau d'optional (sinon ça fait optional de optional (faire ça à chaque map sauf le dernier)
                        poeRepository.findById(idPoe)
                                .map(poeEntity -> {
                                    traineeEntity.setPoe(poeEntity);
                                    //var savedTrainee =traineeRepository.save(traineeEntity);
                                    traineeRepository.flush();//forcer l'update
                                    return modelMapper.map(traineeEntity, TraineeDetailDto.class);
                                })
                );


    }



    public Optional<TraineeDetailDto> remPoe (int idTrainee){
        return traineeRepository.findById(idTrainee)
                .map(traineeEntity-> {             // permet de garder un seul niveau d'optional (sinon ça fait optional de optional (faire ça à chaque map sauf le dernier)
                    traineeEntity.setPoe(null);
                    //var traineeSaved=traineeRepository.save(traineeEntity);
                    traineeRepository.flush();
                    return  modelMapper.map(traineeEntity, TraineeDetailDto.class);
                        }
                );


    }

    public Optional<List<TraineeDetailDto>> getByPoeId(Integer idPoe){
        return Optional.of( traineeRepository.findByPoeId(idPoe).stream().map(
                        (Trainee traineeEntity)-> modelMapper.map(traineeEntity, TraineeDetailDto.class)
                        ).toList());
    }
}
