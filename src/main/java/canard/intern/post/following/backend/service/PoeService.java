package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.PoeDetailsDto;
import canard.intern.post.following.backend.dto.PoeDto;

import canard.intern.post.following.backend.error.UpdateException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PoeService {
    /**
     * get all trainees
     * @return trainees
     */

    List<PoeDto> getAll();

    /**
     * get a trainee by its id if exists
     * @param id id of trainee
     * @return optional with trainee found
     * or optional empty if not exists
     */
    Optional<PoeDetailsDto> getById(int id);

    /**
     * get trainees with lastname containing lastnamePartial,
     * ignoring case
     * @param lastnamePartial
     * @return set of trainees matching
     */
    Set<PoeDto> getByTitleContaining(String titlePartial);

    /**
     * create trainee and generate an id
     * @param traineeDto trainee to create (without id)
     * @return trainee created with its id
     * @throws UpdateException if trainee cannot be created
     */
    PoeDto create(PoeDto poeDto);

    /**
     * update a trainee with this id if exists ;
     * replace all fields with fields from argument traineeDto
     * @param id id of trainee to update
     * @param PoeDto new version of trainee to update
     * @return optional with trainee updated if exists
     * or optional empty if not found
     * @throws UpdateException if found but cannot be updated
     */
    Optional<PoeDto> update(int id, PoeDto poeDto);

    /**
     * delete trainee with this id if exists
     * @param id id of trainee to delete
     * @return true if deleted, false if not found
     * @throws UpdateException if found and cannot be deleted
     */
    boolean delete(int id);

}