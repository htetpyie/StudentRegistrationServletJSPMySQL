package studentregistration.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentregistration.persistant.dto.StudentResponseDTO;

public class StudentCourseDAO {

	public static Connection con = null;
	static {
		try {
			con = MyConnection.getCon();		
		}catch(Exception e) {
			System.out.println("Connection in Student Dao error.");
		}
	}
	
	public int insertStudentCourse(String studentId, String courseId) {
		int result = 0;
		String sql = "Insert into student_course(student_id, course_id) values(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, studentId);
			ps.setString(2, courseId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Student course insert fail...");
		}
		
		return result;
	}

	public String selectCoursesByStudentId(String id) {	
		String courses = "";
		String sql = "select course_table.name from course_table join student_course on course_table.id = student_course.course_id where student_course.student_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				if(courses.isBlank()) {
					courses = rs.getString(1);
				}else
					courses = courses+", "+rs.getString(1);
			}			
			
		} catch (SQLException e) {
			System.out.println("Course Selection with student id Fail.");
		}
		return courses;
	}
	
	public String selectCourseIdByStudentId(String id) {	
		String courses = "";
		String sql = "select course_table.id from course_table join student_course on course_table.id = student_course.course_id where student_course.student_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				if(courses.isBlank()) {
					courses = rs.getString(1);
				}else
					courses = courses+", "+rs.getString(1);
			}			
			
		} catch (SQLException e) {
			System.out.println("Course Id Selection with student id Fail.");
		}
		return courses;
	}
	
	public int deleteCourseListByStudentId(String id) {
		int result = 0;
		String query = "delete from student_course where student_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);			
			result = ps.executeUpdate();
			System.out.println("delete success");
		} catch (SQLException e) {
			System.out.println("Delete student_coures fails.");
		}
		
		return result;
	}
	
	public ArrayList<StudentResponseDTO> searchByIdNameCourse(String id, String name, String course){
		ArrayList<StudentResponseDTO> list = new ArrayList();
		String sql = "select distinct student_table.id, student_table.name, student_table.dob,"
				+ "student_table.gender, student_table.phone, student_table.education, student_table.photo"
				+ " from student_table join student_course on student_table.id = student_course.student_id "
				+ "join course_table on student_course.course_id = course_table.id"
				+ " where student_table.id = ? or student_table.name = ? or course_table.name = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, course);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StudentResponseDTO studentRes = new StudentResponseDTO();
				studentRes.setStudentId(rs.getString("id"));
				studentRes.setStudentName(rs.getString("name"));
				studentRes.setStudentDob(rs.getString("dob"));
				studentRes.setStudentGender(rs.getString("gender"));
				studentRes.setStudentPhone(rs.getString("phone"));
				studentRes.setStudentEducation(rs.getString("education"));
				studentRes.setStudentPhoto(rs.getString("photo"));
				
				list.add(studentRes);
			}
			System.out.println("Selection done." + list);
		} catch (SQLException e) {
			System.out.println(" search selection error...");
		}
		return list;
	}
	
}
