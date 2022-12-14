package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.PoeRepository;
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
    private ModelMapper modelMapper;

    @Override
    public List<PoeDto> getAll(){
        return poeRepository.
                        findAll().stream().map((pe)->modelMapper.map(pe,PoeDto.class)).toList();
    }

    @Override
    public Optional<PoeDto> getById(int id){
        // on récup la un optional de Trainee


        return poeRepository.findById(id)
                .map((pe)-> modelMapper.map(pe, PoeDto.class));//transfo que si y'a kkchose dans la boite
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


}
