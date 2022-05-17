package com.example.mongodbtutorial;

import com.example.mongodbtutorial.model.Address;
import com.example.mongodbtutorial.model.Gender;
import com.example.mongodbtutorial.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class MongoDbTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbTutorialApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String email = "ribeiro5.danilo@gmail.com";
			Address address = new Address(
					"Brasil",
					"SP",
					"04444000");
			Student student = new Student(
					"Danilo",
					"Santos",
					email,
					Gender.MALE,
					address,
					List.of("Computer Science", "Maths"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);


			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

			repository.findStudentByEmail(email).ifPresentOrElse(s-> {
				System.out.println("email already taken ["+ email +"]");
			}, () -> {
				System.out.println("Inserting student " + student);
				repository.insert(student);
			});

		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException("found many emails ["+ email +"]");
		}

		if (students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println("email already taken ["+ email +"]");
		}
	}
}
