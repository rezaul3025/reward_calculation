package com.reward.api.dto;

import com.reward.api.domain.ExerciseType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class ExerciseDTO {

    @Positive(message = "Steps cannot be negative")
    @NotNull(message = "Steps cannot be null")
    private Integer steps;

    private Date date;

    @NotNull(message = "Exercise Type cannot be null")
    private ExerciseType type;

    private Boolean isRewarded;

    private Integer userId;

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public Boolean getRewarded() {
        return isRewarded;
    }

    public void setRewarded(Boolean rewarded) {
        isRewarded = rewarded;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
