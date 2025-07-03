package com.mydating.dating.entity;

import java.util.List;

import com.mydating.dating.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	
	@ElementCollection
//	@OneToMany			// if both classes are entity then only we should use @Onetomany(list<string>is not entity class.but User class is entity class.
	private List<String> interests;
	
}
