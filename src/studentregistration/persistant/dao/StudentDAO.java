package studentregistration.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentregistration.persistant.dto.StudentDTO;
import studentregistration.persistant.dto.StudentResponseDTO;
import studentregistration.persistant.dto.UserDTO;
import studentregistration.persistant.dto.UserResponseDTO;

public class StudentDAO {
	
	public static Connection con = null;
	static {
		try {
			con = MyConnection.getCon();		
		}catch(Exception e) {
			System.out.println("Connection in Student Dao error.");
		}
	}
	
	
	public int insertStudent(StudentDTO dto) {
		int result = 0;
		String sql = "Insert into student_table (id, name, dob, gender, phone, education, photo) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudentId());
			ps.setString(2, dto.getStudentName());
			ps.setString(3, dto.getStudentDob());
			ps.setString(4, dto.getStudentGender());
			ps.setString(5, dto.getStudentPhone());
			ps.setString(6, dto.getStudentEducation());
			ps.setString(7, dto.getStudentPhoto());
			result = ps.executeUpdate();
			System.out.println("Student insert success.");
			
		} catch (SQLException e) {
			System.out.println("Student Insert Fail.");
		}
		return result;
	}
	
	public int updateStudent(StudentDTO dto) {
		int result = 0;
		String sql = "update student_table set name = ?, dob = ?, gender = ?,"
				+ "phone = ?, education = ?, photo = ? where id = ?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(7, dto.getStudentId());
			ps.setString(1, dto.getStudentName());
			ps.setString(2, dto.getStudentDob());
			ps.setString(3, dto.getStudentGender());
			ps.setString(4, dto.getStudentPhone());
			ps.setString(5, dto.getStudentEducation());
			ps.setString(6, dto.getStudentPhoto());
			result = ps.executeUpdate();
			System.out.println("Student table updated successfully.");
			
		} catch (SQLException e) {
			System.out.println("Student update Fail.");
		}
		return result;
	}
	
	public int deleteStudent(StudentDTO dto) {
		int result = 0;
		String sql = "delete from student_table where id = ?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudentId());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Student delete Fail.");
		}
		return result;
	}
	
	public StudentResponseDTO selectStudent(StudentDTO dto) {	
		StudentResponseDTO studentRes = new StudentResponseDTO();
		String sql = "select * from student_table where id=?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudentId());			
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				studentRes.setStudentId(rs.getString("id"));
				studentRes.setStudentName(rs.getString("name"));
				studentRes.setStudentDob(rs.getString("dob"));
				studentRes.setStudentGender(rs.getString("gender"));
				studentRes.setStudentPhone(rs.getString("phone"));
				studentRes.setStudentEducation(rs.getString("education"));
				studentRes.setStudentPhoto(rs.getString("photo"));
				
			}			
			
		} catch (SQLException e) {
			System.out.println("Student Selection with id Fail.");
		}
		return studentRes;
		
	}

	public ArrayList<StudentResponseDTO> selectStudentAll() {	
		ArrayList<StudentResponseDTO> list = new ArrayList();
		String sql = "select * from student_table ";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);		
			ResultSet rs =  ps.executeQuery();
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
			
		} catch (SQLException e) {
			System.out.println("Student Selection all Fail.");
		}
		return list;
		
	}



}
