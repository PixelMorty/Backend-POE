package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.PoeDetailsDto;
import canard.intern.post.following.backend.dto.PoeDto;

import canard.intern.post.following.backend.service.PoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/api/poes")
public class PoeController {


    @Autowired
    private PoeService poeService;

    /**
     * GET /api/trainees
     * @return all trainees
     */
    @GetMapping
    public List<PoeDto> getAll(){return poeService.getAll();}

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
    public PoeDetailsDto getById(@PathVariable("id") int id){
        var optPoeDto =  poeService.getById(id);
        if (optPoeDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No poe found with id <%d>", id));
        }
        return optPoeDto.get();
    }



    @GetMapping("/search/byTitle")
    @ResponseStatus(HttpStatus.OK)
    public Set<PoeDto> getByLastName(@RequestParam("ln") String title){
        return null;//poeService.getByLastnameContaining(lastname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto create(@Valid @RequestBody PoeDto poeDto) {
//        try{
        return poeService.create(poeDto);
//        }catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "duplicate mail");
//        }

    }

    @PutMapping("/{id}")
    public PoeDto update(
            @PathVariable("id") int id,
            @Valid @RequestBody PoeDto poeDto
    ){
        if (Objects.nonNull(poeDto.getId()) && (poeDto.getId() != id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Id <%d> from path does not match id <%d> from body",
                            id, poeDto.getId()));
            // NB:you can use also:  MessageFormat.format or StringBuilder
        }
//        var updatedTrainnee=traineeService.update(id,poeDto);
//        if (updatedTrainnee.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("No trainee found with id <%d>", id));
//
//        return updatedTrainnee.get();
        //Meilleire façon, avec les optionals
        return poeService.update(id,poeDto).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("No poe found with id <%d>", id)));

    }

    //NB: other choice, return Dto removed if found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        var deleted = poeService.delete(id);
        if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("No poe found with id <%d>", id));
    }
}
