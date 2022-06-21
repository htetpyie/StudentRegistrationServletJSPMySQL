package helpers;

import java.util.ArrayList;

import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.UserDTO;
import studentregistration.persistant.dto.UserResponseDTO;

public class UserHelper {
	public static String idGenerator() {
		String id = "";
		ArrayList<UserResponseDTO> list =new UserDAO().selectUserAll();
		if(list == null || list.size() <= 0) {
			id = "USR001";
		}else {			
			UserResponseDTO lastDTO = list.get(list.size()-1);
			int lastId = Integer.parseInt(lastDTO.getUserId().substring(3));
			id = String.format("USR"+"%03d", lastId+1);
		}
		return id;
	}
	
	public static boolean isEmailExist(String email) {
		ArrayList<UserResponseDTO> list =new UserDAO().selectUserAll();
		if(list != null) {
			for(UserResponseDTO user: list) {
				if(user.getUserEmail().equals(email)) return true;
			}
		}
		return false;
	}

//	public static void main(String[]args) {
//		System.out.print(idGenerator());
//	}
}
