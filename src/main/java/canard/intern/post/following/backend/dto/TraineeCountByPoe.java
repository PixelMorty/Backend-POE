package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.entity.Trainee;

public interface TraineeCountByPoe {
    Trainee getTraineeCount();
    String getPoeType();
}
