package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.ChoiceRepository;
import canard.intern.post.following.backend.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChoiceServiceJpa implements ChoiceService {
    @Autowired
    private ChoiceRepository choiceRepository;
    @Override
    public boolean delete(int id){
        try {
            if (choiceRepository.findById(id).isPresent()) {
                choiceRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }catch(Exception e){// autres problèmes
            throw (new UpdateException("Choice couldn't be deleted",e));
            //return false;
        }
    }
}
