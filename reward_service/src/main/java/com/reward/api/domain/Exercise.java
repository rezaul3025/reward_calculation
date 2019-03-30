package com.reward.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity(name="exercise")
public class Exercise
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private Integer step;

   private Date date;

   private ExerciseType type;

   private Boolean isRewarded;

   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   @JsonBackReference
   private User user;

   @OneToOne(mappedBy = "exercise")
   private Reward reward;

   public Exercise()
   {

   }

   public Exercise(Integer step, Date date, ExerciseType type, Boolean isRewarded)
   {
      this.step = step;
      this.date = date;
      this.type = type;
      this.isRewarded = isRewarded;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Integer getStep()
   {
      return step;
   }

   public void setStep(Integer step)
   {
      this.step = step;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public ExerciseType getType()
   {
      return type;
   }

   public void setType(ExerciseType type)
   {
      this.type = type;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public Boolean getRewarded() {
      return isRewarded;
   }

   public void setRewarded(Boolean rewarded) {
      isRewarded = rewarded;
   }

   public Reward getReward() {
      return reward;
   }

   public void setReward(Reward reward) {
      this.reward = reward;
   }
}
