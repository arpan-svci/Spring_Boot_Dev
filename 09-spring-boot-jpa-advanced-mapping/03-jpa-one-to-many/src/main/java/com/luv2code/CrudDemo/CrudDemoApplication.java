package com.luv2code.CrudDemo;

import com.luv2code.CrudDemo.dao.AppDAO;
import com.luv2code.CrudDemo.entity.Course;
import com.luv2code.CrudDemo.entity.Instructor;
import com.luv2code.CrudDemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AfterDomainEventPublication;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			findInstructorWithCourses(appDAO);
		};
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
		int theId=2;
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
