package com.glearning.students.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.glearning.students.dao.StudentRepository;
import com.glearning.students.dao.UserRepository;
import com.glearning.students.model.Role;
import com.glearning.students.model.Student;
import com.glearning.students.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void initializeData(ApplicationReadyEvent readyEvent) {
		
		Student Suresh = new Student("Suresh", "Reddy", "Java", "India");
		Student Murali = new Student("Murali", "Mohan", "Python", "India");
		Student Daniel = new Student("Daniel", "Denson", "NodeJs", "New Zealand");
		Student Tanya = new Student("Tanya", "Gupta", "Java", "USA");
		
		this.studentRepository.save(Suresh);
		this.studentRepository.save(Murali);
		this.studentRepository.save(Daniel);
		this.studentRepository.save(Tanya);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUsersData(ApplicationReadyEvent readyEvent) {
		
			User kiran = new User("kiran", passwordEncoder.encode("welcome"));
			User vinay = new User("vinay", passwordEncoder.encode("welcome"));
			
			Role userRole = new Role("ROLE_USER");
			Role adminRole = new Role("ROLE_ADMIN");
			
			kiran.addRole(userRole);
			
			vinay.addRole(userRole);
			vinay.addRole(adminRole);
			
			this.userRepository.save(kiran);
			this.userRepository.save(vinay);
		
	}

}
