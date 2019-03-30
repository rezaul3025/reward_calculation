package com.reward.api.repository;

import com.reward.api.domain.Exercise;
import com.reward.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise>  findByUser(User user);
}
