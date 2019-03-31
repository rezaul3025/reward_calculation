package com.reward.api.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="reward")
public class Reward {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(precision = 12, length=10, name="price_in_euro", scale = 4)
   private BigDecimal priceInEuro;

   @Column(precision = 12, length=10, name="converted_price", scale = 4)
   private BigDecimal convertedPrice;

   @Column(length=10, name="type")
   private RewardType type;

   @Column(name="date")
   private Date date;

   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   @JsonBackReference
   private User user;

   @OneToOne
   @JoinColumn(name = "exercise_id", referencedColumnName = "id")
   @JsonManagedReference
   private Exercise exercise;

   public Reward(BigDecimal priceInEuro , BigDecimal convertedPrice, RewardType type, Date date)
   {
      this.priceInEuro = priceInEuro;
      this.convertedPrice = convertedPrice;
      this.type = type;
      this.date = date;
   }

   public Reward()
   {

   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public BigDecimal getPriceInEuro()
   {
      return priceInEuro;
   }

   public void setPriceInEuro(BigDecimal priceInEuro)
   {
      this.priceInEuro = priceInEuro;
   }

   public BigDecimal getConvertedPrice()
   {
      return convertedPrice;
   }

   public void setConvertedPrice(BigDecimal convertedPrice)
   {
      this.convertedPrice = convertedPrice;
   }

   public RewardType getType()
   {
      return type;
   }

   public void setType(RewardType type)
   {
      this.type = type;
   }

   public Date getDate()
   {
      return date;
   }

   public void setDate(Date date)
   {
      this.date = date;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public Exercise getExercise() {
      return exercise;
   }

   public void setExercise(Exercise exercise) {
      this.exercise = exercise;
   }
}
