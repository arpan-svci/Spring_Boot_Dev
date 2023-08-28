package com.luv2code.CrudDemo;

import com.luv2code.CrudDemo.dao.AppDAO;
import com.luv2code.CrudDemo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AfterDomainEventPublication;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//findCourseAndStudentByCourseId(appDAO);
			//findStudentAndCourseByStudentId(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourseById(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId=2;
		appDAO.deleteStudentById(theId);
		System.out.println("deleting student id: "+theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int studentId=2;
		Student tempStudent=appDAO.findStudentAndCourseByStudentId(studentId);
		int courseId=11;
		Course tempCourse=appDAO.findCourseAndStudentByCourseId(courseId);
		tempStudent.addCourse(tempCourse);
		appDAO.update(tempStudent);
		System.out.println("tempStudent: "+tempStudent);
		System.out.println("associated courses: "+tempStudent.getCourses());
		System.out.println("Done!");
	}

	private void findStudentAndCourseByStudentId(AppDAO appDAO) {
		int theId=1;
		Student student=appDAO.findStudentAndCourseByStudentId(theId);
		System.out.println("student: "+student);
		System.out.println("Associated courses: "+student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudentByCourseId(AppDAO appDAO) {
		int theId=10;
		// find the particular course for this id
		Course tempCourse=appDAO.findCourseAndStudentByCourseId(theId);
		System.out.println("tempCourse: "+tempCourse);
		System.out.println("Associated students: "+tempCourse.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course

		// create the students

		// add students to the code

		// save the course and associate student

		Course tempCourse=new Course("C++");
		Course tempCourse1=new Course("Java");
		Student s1=new Student("Arpan","Mandal","arpanmandal913@gmail.com");
		Student s2=new Student("Satabda","Mandal","mandal.satabda@gmail.com");
		Student s3=new Student("Rahul","Manna","rahulmanna2001@gmail.com");
		tempCourse.addStudent(s1);
		tempCourse.addStudent(s3);
		tempCourse1.addStudent(s2);
		System.out.println("Saving the course: "+tempCourse);
		System.out.println("Associated students: "+tempCourse.getStudents());
		appDAO.saveCourse(tempCourse);
		System.out.println("Saving the course: "+tempCourse1);
		System.out.println("Associated students: "+tempCourse1.getStudents());
		appDAO.saveCourse(tempCourse1);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId=10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("DOne!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId=10;
		Course course=appDAO.findCourseAndReviewsByCourseId(theId);
		// print the course
		System.out.println(course);
		// print the reviews
		System.out.println(course.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		// add some reviews
		// save the course
		Course course=new Course("Django");

		course.addReview(new Review("this course is so helpful"));
		course.addReview(new Review("this course is better than any of the other course"));
		course.addReview(new Review("this is really nice"));

		appDAO.saveCourse(course);
		System.out.println("saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		System.out.println("Done!");
	}

	private void deleteCourseById(AppDAO appDAO) {
		int theId=11;
		System.out.println("deleted course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int courseId=10;
		// find the course
		System.out.println("finding course id: "+courseId);
		Course tempCourse=appDAO.findCourseById(courseId);

		// update the course
		tempCourse.setTitle("Spring Boot");
		appDAO.updateCourse(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;
		// find the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		// update some data for instructor
		tempInstructor.setLastName("Mandal");
		appDAO.updateInstructor(tempInstructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		System.out.println("instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate courses: "+tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);

		// find courses for instructor
		System.out.println("finding courses for instructor id: "+theId);
		List<Course> courses=appDAO.findCoursesByInstructorId(theId);
		//associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("associated courses: "+tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate courses: "+tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor=new Instructor("Arpan","Mandal","arpanmandal913@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/on_the_way","student");

		// associate the objects

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// add some courses
		Course tempCourse1=new Course("Java");
		Course tempCourse2=new Course("Python");
		Course tempCourse3=new Course("C++");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		//save the instructor
		System.out.println("saving instructor"+tempInstructor);
		System.out.println("The Courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=3;
		System.out.println("deleting instructor id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructorDetail object
		int theId=1;
		InstructorDetail tempInstructorDetail= appDAO.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println("tempInstructorDetail: "+tempInstructorDetail);
		// print associated instructor
		System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor"+tempInstructor);
		System.out.println("the associated instructor details only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {


		// create the instructor
		Instructor tempInstructor=new Instructor("Arpan","Mandal","arpanmandal913@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/on_the_way","student");

		// associate the objects

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		System.out.println("saving instructor"+tempInstructor);
		appDAO.save(tempInstructor);

		// create the instructor
		Instructor tempInstructor1=new Instructor("Satabda","Mandal","mandal.satabda@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail1=new InstructorDetail("http://www.youtube.com/spark","student");

		// associate the objects

		tempInstructor1.setInstructorDetail(tempInstructorDetail1);

		//save the instructor
		System.out.println("saving instructor"+tempInstructor1);
		appDAO.save(tempInstructor1);

		System.out.println("Done!");
	}
}
