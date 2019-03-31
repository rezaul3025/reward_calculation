package com.reward.api.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity(name="exercise")
public class Exercise
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private Integer steps;

   private Date date;

   private ExerciseType type;

   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   @JsonBackReference
   private User user;

   @OneToOne(mappedBy = "exercise")
   @JsonBackReference
   private Reward reward;

   public Exercise()
   {

   }

   public Exercise(Integer steps, Date date, ExerciseType type)
   {
      this.steps = steps;
      this.date = date;
      this.type = type;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Integer getSteps()
   {
      return steps;
   }

   public void setSteps(Integer steps)
   {
      this.steps = steps;
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

   public Reward getReward() {
      return reward;
   }

   public void setReward(Reward reward) {
      this.reward = reward;
   }
}
