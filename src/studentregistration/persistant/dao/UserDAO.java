package studentregistration.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentregistration.persistant.dto.UserDTO;
import studentregistration.persistant.dto.UserResponseDTO;

public class UserDAO {
	public static Connection con = null;
	static {
		try {
			con = MyConnection.getCon();		
		}catch(Exception e) {
			System.out.println("Connection in user Dao error.");
		}
	}
	
	
	public int insertUser(UserDTO dto) {
		int result = 0;
		String sql = "Insert into user_table (id, name, email, password, role) "
				+ "values(?, ?, ?, ?, ?)";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getUserName());
			ps.setString(3, dto.getUserEmail());
			ps.setString(4, dto.getUserPassword());
			ps.setString(5, dto.getUserRole());
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("User Insert Fail.");
		}
		return result;
	}
	
	public int updateUser(UserDTO dto) {
		int result = 0;
		String sql = "update user_table set name=? , email = ?, password = ?, role = ?"
				+ "where id = ?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(5, dto.getUserId());
			ps.setString(1, dto.getUserName());
			ps.setString(2, dto.getUserEmail());
			ps.setString(3, dto.getUserPassword());
			ps.setString(4, dto.getUserRole());
			result = ps.executeUpdate();
			System.out.println("User table updated successfully.");
			
		} catch (SQLException e) {
			System.out.println("User update Fail.");
		}
		return result;
	}
	
	public int deleteUser(UserDTO dto) {
		int result = 0;
		String sql = "delete from user_table where id = ?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());			
			result = ps.executeUpdate();
			System.out.println("User table detele successfully.");
			
		} catch (SQLException e) {
			System.out.println("User delete Fail.");
		}
		return result;
	}
	
	public UserResponseDTO selectUser(UserDTO dto) {	
		UserResponseDTO userRes = new UserResponseDTO();
		String sql = "select * from user_table where id=?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());			
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				userRes.setUserId(rs.getString("id"));
				userRes.setUserName(rs.getString("name"));
				userRes.setUserEmail(rs.getString("email"));
				userRes.setUserPassword(rs.getString("password"));
				userRes.setUserRole(rs.getString("role"));
			}			
			
		} catch (SQLException e) {
			System.out.println("Selection with id Fail.");
		}
		return userRes;
		
	}

	public ArrayList<UserResponseDTO> selectUsersByName(String name) {	
		ArrayList<UserResponseDTO> list = new ArrayList<UserResponseDTO>();
		String sql = "select * from user_table where name=?";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);			
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO userRes = new UserResponseDTO();
				userRes.setUserId(rs.getString("id"));
				userRes.setUserName(rs.getString("name"));
				userRes.setUserEmail(rs.getString("email"));
				userRes.setUserPassword(rs.getString("password"));
				userRes.setUserRole(rs.getString("role"));
				list.add(userRes);
			}			
			
		} catch (SQLException e) {
			System.out.println("Selection with name Fail.");
		}
		return list;
		
	}
	
	public ArrayList<UserResponseDTO> selectUserAll() {	
		ArrayList<UserResponseDTO> list = new ArrayList<UserResponseDTO>();
		String sql = "select * from user_table ";		
		try {
			PreparedStatement ps = con.prepareStatement(sql);		
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO userRes = new UserResponseDTO(rs.getString("id"),rs.getString("email"),
											rs.getString("name"),rs.getString("password"),rs.getString("role"));
				list.add(userRes);				
			}			
			
		} catch (SQLException e) {
			System.out.println("User Selection all Fail.");
		}
		return list;
		
	}

}
 