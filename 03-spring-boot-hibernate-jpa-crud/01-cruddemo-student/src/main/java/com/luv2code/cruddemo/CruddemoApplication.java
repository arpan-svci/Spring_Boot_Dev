package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@AutoConfiguration
@ComponentScan(value = "com.luv2code.cruddemo.dao,com.luv2code.cruddemo.entity")
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDao){
		return runner->{
			// createMultipleStudent(studentDao);
			// readStudent(studentDao);
			// queryForStudent(studentDao);
			// queryForStudentByLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			deleteAllStudents(studentDao);
		};
	}

	private void deleteAllStudents(StudentDAO studentDao) {
		System.out.println("deleting all student");
		int numRowsDeleted= studentDao.deleteAll();
		System.out.println("deleted row count: "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDao) {
		int studentId=3;
		System.out.println("Deleting student id: "+studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDao) {
		//retrieve student based on the id: primary key
		int studentId=4;
		System.out.println("Getting student with id:"+studentId);
		Student myStudent= studentDao.findById(studentId);
		//change last name to 'Mandal'
		System.out.println("update the student...");
		myStudent.setLastName("Bera");
		//update the student
		studentDao.update(myStudent);
		//display the updated student
		System.out.println("update student: "+myStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDao) {
		// get the list of students
		List<Student> students=studentDao.findByLastName("Mandal");
		// display the list of students
		for(Student s:students){
			System.out.println(s);
		}
	}

	private void queryForStudent(StudentDAO studentDao) {
		// get list of student
		List<Student> Students=studentDao.findAll();
//		System.out.println("hello main");
		// display list of student
		for(Student s:Students){
			System.out.println(s);
		}
	}

	private void readStudent(StudentDAO studentDAO){
		// create a Student object
		System.out.println("Creating a new student object.....");
		Student temppStudent= new Student("Sujata","Bera","Sujata7@gmail.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(temppStudent);

		//display id for saved student
		int theId=temppStudent.getId();
		System.out.println("Saved Student. Generate id: "+theId);

		//retrieve student based on the id: primary key
		System.out.println("retrieving student with id: "+theId);
		Student myStudent=studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: "+myStudent);
	}
	private void createMultipleStudent(StudentDAO studentDAO){
		//create multiple student object
		System.out.println("creating a student object ...");
		Student tempStudent1= new Student("Arpan","Mandal","arpanmandal913@gmail.com");
		Student tempStudent2= new Student("Satabda","Mandal","mandal.satabda@gmail.com");
		Student tempStudent3= new Student("Rahul","Manna","rahulmanna2001@gmail.com");

		//save student objects
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}
	private void createStudent(StudentDAO studentDao){
		// create the student object
		System.out.println("creating a student object ...");
		Student tempStudent= new Student("Arpan","Mandal","arpanmandal913@gmail.com");
		//save the student object
		System.out.println("saving the student...");
		studentDao.save(tempStudent);

		//display id of save student

		System.out.println("saved student. Generated id: "+tempStudent.getId());
	}

}
