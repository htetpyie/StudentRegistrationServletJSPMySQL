package studentregistration.controllers.student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.CourseResponseDTO;
import studentregistration.persistant.dto.StudentDTO;
import studentregistration.persistant.dto.StudentResponseDTO;


@WebServlet("/StudentDetailServlet")
public class StudentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(id);
		// To get student data
		StudentResponseDTO studentRes = new StudentDAO().selectStudent(dto);
		request.setAttribute("student", studentRes);
		
		//To get all course
		ArrayList<CourseResponseDTO> courseList= new CourseDAO().selectCourseAll();
		request.setAttribute("courseList", courseList);
		
		//To get the selected course
		String selectedCourses = new StudentCourseDAO().selectCourseIdByStudentId(dto.getStudentId());
		request.setAttribute("selectedCourseIdList", selectedCourses);
		
		request.getRequestDispatcher("STU002.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
