package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {


    @Autowired
    private TraineeService traineeService;

    /**
     * GET /api/trainees
     * @return all trainees
     */
    @GetMapping
    public List<TraineeDto> getAll(){return traineeService.getAll();}

    /**
     * GET /api/trainees/{id}
     *
     * Example: in order to get trainee of id 3, call
     *    GET /api/trainees/3
     *
     * @param id id of trainee to get
     * @return trainee with this id if found
     */
    @GetMapping("/{id}")
    public TraineeDetailDto getById(@PathVariable("id") int id){
        var optTraineeDto =  traineeService.getById(id);
        if (optTraineeDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No trainee found with id <%d>", id));
        }
        return optTraineeDto.get();
    }

    @GetMapping("/search/byLastname")
    @ResponseStatus(HttpStatus.OK)
    public Set<TraineeDto> getByLastName(@RequestParam("ln") String lastname){
        return traineeService.getByLastnameContaining(lastname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDetailDto create(@Valid @RequestBody TraineeDto traineeDto) {
//        try{
            return traineeService.create(traineeDto);
//        }catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "duplicate mail");
//        }

    }

    @PutMapping("/{id}")
    public TraineeDetailDto update(
            @PathVariable("id") int id,
            @Valid @RequestBody TraineeDto traineeDto
    ){
        if (Objects.nonNull(traineeDto.getId()) && (traineeDto.getId() != id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Id <%d> from path does not match id <%d> from body",
                            id, traineeDto.getId()));
            // NB:you can use also:  MessageFormat.format or StringBuilder
        }
//        var updatedTrainnee=traineeService.update(id,traineeDto);
//        if (updatedTrainnee.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("No trainee found with id <%d>", id));
//
//        return updatedTrainnee.get();
        //Meilleire fa??on, avec les optionals
        return traineeService.update(id,traineeDto).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("No trainee found with id <%d>", id)));

    }


@PatchMapping("/{idTrainee}/setPoe/{idPoe}")
public TraineeDetailDto setPoe(
        @PathVariable("idTrainee") int idTrainee,
        @PathVariable int idPoe
){

    return traineeService.setPoe(idTrainee,idPoe).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Trainee not found or poe not found trainee poe"+ idTrainee + " " + idPoe));
}

// TODO setNullPoe with a PATCH

    //NB: other choice, return Dto removed if found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        var deleted = traineeService.delete(id);
        if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("No trainee found with id <%d>", id));
    }
}
