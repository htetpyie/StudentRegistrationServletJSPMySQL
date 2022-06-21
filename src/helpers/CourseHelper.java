package helpers;

import java.util.ArrayList;

import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.CourseResponseDTO;
import studentregistration.persistant.dto.UserResponseDTO;


public class CourseHelper {
	
	public static String idGenerator() {
		String id = "";
		ArrayList<CourseResponseDTO> list =new CourseDAO().selectCourseAll();
		if(list == null || list.size() <= 0) {
			id = "COU001";
		}else {			
			CourseResponseDTO lastDTO = list.get(list.size()-1);
			int lastId = Integer.parseInt(lastDTO.getCourseId().substring(3));
			id = String.format("COU"+"%03d", lastId+1);
		}
		return id;
	}
	
	public static boolean isCourseExist(String courseName) {
		ArrayList<CourseResponseDTO> list =new CourseDAO().selectCourseAll();
		if(list != null) {
			for(CourseResponseDTO course: list) {
				if(course.getCourseName().equals(courseName)) return true;
			}
		}
		return false;
	}

}
