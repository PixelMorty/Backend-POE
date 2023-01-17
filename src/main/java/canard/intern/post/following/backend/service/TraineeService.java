package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.error.UpdateException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TraineeService {
    /*
     * get all trainees
     * @return trainees
     */

    List<TraineeDto> getAll();
    List<TraineeDetailDto> getAllDetailList();

    /**
     * get a trainee by its id if exists
     * @param id id of trainee
     * @return optional with trainee found
     * or optional empty if not exists
     */
    Optional<TraineeDetailDto> getById(int id);

    /**
     * get trainees with lastname containing lastnamePartial,
     * ignoring case
     * @param lastnamePartial
     * @return set of trainees matching
     */
    Set<TraineeDto> getByLastnameContaining(String lastnamePartial);

    /**
     * create trainee and generate an id
     * @param traineeDto trainee to create (without id)
     * @return trainee created with its id
     * @throws UpdateException if trainee cannot be created
     */
    TraineeDetailDto create(TraineeDto traineeDto);

    /**
     * update a trainee with this id if exists ;
     * replace all fields with fields from argument traineeDto
     * @param id id of trainee to update
     * @param traineeDto new version of trainee to update
     * @return optional with trainee updated if exists
     * or optional empty if not found
     * @throws UpdateException if found but cannot be updated
     */
    Optional<TraineeDetailDto> update(int id, TraineeDto traineeDto);

    /**
     * delete trainee with this id if exists
     * @param id id of trainee to delete
     * @return true if deleted, false if not found
     * @throws UpdateException if found and cannot be deleted
     */
    boolean delete(int id);


    /**
     * trainee trainee with poe if this idTrainee  and  exists
     * @param idPoe
     * @param idTrainee
     * @return trainee with details if poe and trainee exist
     * else optional empty
     */
    Optional<TraineeDetailDto> setPoe(int idTrainee, int idPoe);
    Optional<TraineeDetailDto> remPoe (int idTrainee);
    Optional<List<TraineeDetailDto>> getByPoeId(Integer idPoe);


}
