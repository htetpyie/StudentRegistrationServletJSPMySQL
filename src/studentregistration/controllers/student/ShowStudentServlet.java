package studentregistration.controllers.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.CourseResponseDTO;
import studentregistration.persistant.dto.StudentResponseDTO;


@WebServlet("/ShowStudentServlet")
public class ShowStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<StudentResponseDTO> studentList = new StudentDAO().selectStudentAll();
		Map map = new HashMap();
		for(StudentResponseDTO student: studentList) {
			String courses = new StudentCourseDAO().selectCoursesByStudentId(student.getStudentId());
			map.put(student.getStudentId(), courses);
		}
		
		request.setAttribute("studentList", studentList);
		request.setAttribute("map",map);
		request.getRequestDispatcher("STU003.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
