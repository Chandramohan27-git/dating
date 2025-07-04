package com.mydating.dating.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mydating.dating.entity.User;
import com.mydating.dating.repository.UserRepository;
import com.mydating.dating.util.UserGender;

@Repository
public class UserDao {
	
	@Autowired
	UserRepository ur;

	public User saveUsers(User u) {
		
		return ur.save(u);
	}

	public List<User> findAllMaleUsers() {
		
		return ur.findByGender(UserGender.MALE);
	}

	public List<User> findAllFemaleUsers() {
		
		return ur.findByGender(UserGender.FEMALE);
	}

	public Optional<User> finduserById(int id) {
		// TODO Auto-generated method stub
		return ur.findById(id);
	}

	public List<User> searchByName(String letters) {
		return ur.searchByName(letters);
	}

	public List<User> searchByEmail(String letters) {
		// TODO Auto-generated method stub
		return ur.searchByEmail(letters);
	}
}
