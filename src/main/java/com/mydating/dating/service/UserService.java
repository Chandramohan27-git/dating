package com.mydating.dating.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mydating.dating.dao.UserDao;
import com.mydating.dating.dto.Matchinguser;
import com.mydating.dating.entity.User;
import com.mydating.dating.util.UserGender;
@Service
public class UserService {
	@Autowired
	UserDao ud;
	public ResponseEntity<?> saveUsers(User u) {
		User savedUser= ud.saveUsers(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleusers = ud.findAllMaleUsers();
		if(maleusers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no male users present in the DB table");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(maleusers);
		}
	}
	public ResponseEntity<?> findAllFemaleUsers() {
		List<User> femaleUsers = ud.findAllFemaleUsers();
		if(femaleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no female users present in the DB table");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(femaleUsers);
		}
	}
	public ResponseEntity<?> findBestMatch(int id, int top) {
		Optional<User> optional=ud.finduserById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User id ");
		}
		User user = optional.get();	
		List<User> users = null;
		if(user.getGender().equals(UserGender.MALE)) {
			users = ud.findAllFemaleUsers();
		}
		else {
			users =ud.findAllMaleUsers();
		}		
		List<Matchinguser> matchingUsers= new ArrayList<>();
		for(User u:users) {
			Matchinguser mu=new Matchinguser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setAge(u.getAge());
			mu.setInterests(u.getInterests());
			mu.setGender(u.getGender());
			mu.setAgeDiff(Math.abs(user.getAge()-u.getAge()));
			List<String> interests1=user.getInterests();
			List<String> interests2 = u.getInterests();
			int mic=0;
			for(String s:interests1) {
				if(interests2.contains(s)) {
					mic++;
				}
			}
			mu.setMic(mic);
			matchingUsers.add(mu);
		}
		Comparator<Matchinguser> c=new UserSorting();
		Collections.sort(matchingUsers,c);
		List<Matchinguser> result =new ArrayList<>();
		for(Matchinguser mu:matchingUsers) {
			if(top==0) {
				break;
			}else {
				result.add(mu);
				top--;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	public ResponseEntity<?> searchByName(String letters) {
		List<User> users = ud.searchByName("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with letters :"+letters);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> searchByEmail(String letters) {
		List<User> users = ud.searchByEmail("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Mail users not found with letters :"+letters);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
}
