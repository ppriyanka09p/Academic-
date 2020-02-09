package com.nttdata.course.driver;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.nttdata.course.dao.AdminDAO;
import com.nttdata.course.dao.CourseDAOException;
import com.nttdata.course.dao.StudentDAO;
import com.nttdata.course.dao.UserDAO;
import com.nttdata.course.domain.Course;
import com.nttdata.course.domain.CoursePreference;
import com.nttdata.course.domain.Professor;
import com.nttdata.course.domain.StudPreference;
import com.nttdata.course.domain.Student;
import com.nttdata.course.domain.User;
import com.nttdata.course.service.CourseRegException;
import com.nttdata.course.service.CourseRegFacade;

public class CourseRegisterDriver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String student = "101";
		String admin = "102";
		
		int k = 0;
		System.out.println("Login");
		System.out.println("Enter user id:");
		String usrid = sc.next();
		System.out.println("Enter password:");
		String pswd = sc.next();

		CourseRegFacade crf = new CourseRegFacade();
		User u1 = null;
		try {
			u1 = crf.validateUser(usrid, pswd);
			System.out.println("Role Id: " + u1.getRoleId());
			String mon= "yes";
			while (mon.equals("yes")) {

				if (u1.getRoleId().equals(admin)) {
					System.out.println(" do you want to continue yes/no ");
					mon = sc.next();
					if (mon.equals("no"))

					{
						System.out.println("thankyou");
						break;
					}
					System.out.println("Login successful");
					System.out.println("1.Add Professor Details");
					System.out.println("2.Add Course Details");
					System.out.println("3.Add Student Details");
					String choice = sc.next();
					if (choice.equals("1")) {

						System.out.println("Enter professor name: ");
						String pnme = sc.next();
						System.out.println("Enter deptid: ");
						String did = sc.next();

						Professor p = new Professor(pnme, did);
						String re = crf.saveProfessor(p);
						System.out.println("Professor details are saved");

						System.out.println("Professor id generated is:" + re);
					}

					if (choice.equals("2")) {

						System.out.println("Enter the coursename: ");
						String cnme = sc.next();
						System.out.println("Enter the courseid: ");
						String prid = sc.next();

						Course c = new Course(cnme, prid);
						c.getCourseId();
						c.getCourseName();
						c.getProfId();
						String cr = crf.saveCourse(c);
						System.out.println("Course details are saved");
						System.out.println("Course id generated is:" + cr);
					}

					if (choice.equals("3")) {

						System.out.println("Enter the studentname: ");
						String sn = sc.next();
						System.out.println("Enter the studentadress: ");
						String sad = sc.next();
						System.out.println("Enter student dob in the format(dd/mm/yyyy): ");
						String dobs = sc.next();
						Date dos1 = null;
						SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
						try {
							dos1 = sdf.parse(dobs);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(dos1);

						System.out.println("Enter student degree: ");
						String sde = sc.next();
						Student st = new Student(sn, sad, dos1, sde);

						st.getStudentId();
						st.getStudentName();
						st.getAddress();
						st.getDob();
						st.getDegree();
						String er = crf.saveStudent(st);

						System.out.println("Student details saved");
						System.out.println("Student id generated:" + er);
					}
				}

				System.out.println("you want to continue Yes/No");
				mon = sc.next();
				System.out.println("test");
				if (mon.equals("no")) {
					System.out.println("thankyou");
				}
				if (u1.getRoleId().equals(student)) {
					System.out.println("Login successful");
					System.out.println("1.view all the course details");
					System.out.println("2.add course preference");
					System.out.println("3.view all the course preference ");
					String choice = sc.next();
					if (choice.equals("1")) {
						List<Course> allcou = new ArrayList<Course>();
						allcou = crf.getAllCourses();
						for (Course c : allcou) {
							System.out.println("Course id: " + c.getCourseId());
							System.out.println("Course name: "+ c.getCourseName());
							System.out.println("Professor id: " + c.getProfId());
						}
					}
					if (choice.equals("2")) {
						System.out.println("Enter student id");
						String sid = sc.next();
						System.out.println("Enter course id");
						String cid = sc.next();
						System.out.println("Enter course preference");
						String cpref = sc.next();
						CoursePreference cp = new CoursePreference(cid, cpref);

						List<CoursePreference> lcp = new ArrayList<CoursePreference>();
						lcp.add(cp);
						StudPreference sp = new StudPreference(sid, lcp);
						try {
							k = crf.saveCoursePref(sp, cp);
						} catch (CourseDAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (k > 0) {
							System.out.println("Student course preference saved");
						} else {
							System.out.println("course filled cant register anymore");
						}
					}
					if (choice.equals("3")) {
						System.out.println("Enter student id");
						String sid = sc.next();
						StudentDAO sdao = new StudentDAO();
						List<CoursePreference> prelist = new ArrayList<CoursePreference>();
						prelist = crf.getPrefCourses(sid);
						for (CoursePreference cp : prelist) {
							System.out.println("Course id: " + cp.getCourseId());
							System.out.println("Course preference: "+ cp.getPreference());
						}
					}
					
					if (mon.equals("no")) {
						System.out.println("thank you");
					}
				}
			}
		} catch (CourseRegException e) {
			System.out.println(e.getMessage());
		}
	}
}