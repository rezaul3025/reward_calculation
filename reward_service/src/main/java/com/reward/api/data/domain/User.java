package com.reward.api.data.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.Reward;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=100)
	private String name;
	
	@Column(length=3, name="country_iso_code")
	private String countryISOCode;
	
	@Column(length=3, name="currency_iso_code")
	private String currencyISOCode;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Exercise> exercises;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Reward> rewards;
	
	public User() {
		
	}
	
	public User(String name, String countryISOCode, String currencyISOCode) {
		this.name = name;
		this.countryISOCode = countryISOCode;
		this.currencyISOCode = currencyISOCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryISOCode() {
		return countryISOCode;
	}

	public void setCountryISOCode(String countryISOCode) {
		this.countryISOCode = countryISOCode;
	}

	public String getCurrencyISOCode() {
		return currencyISOCode;
	}

	public void setCurrencyISOCode(String currencyISOCode) {
		this.currencyISOCode = currencyISOCode;
	}

	public List<Exercise> getExercises()
	{
		return exercises;
	}

	public void setExercises(List<Exercise> exercises)
	{
		this.exercises = exercises;
	}

	public List<Reward> getRewards()
	{
		return rewards;
	}

	public void setRewards(List<Reward> rewards)
	{
		this.rewards = rewards;
	}
}
