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

import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.StudentDTO;
import studentregistration.persistant.dto.StudentResponseDTO;

@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String course = request.getParameter("course");
		
		if(id.isBlank() && name.isBlank() && course.isBlank()) {
			response.sendRedirect("ShowStudentServlet");
		}else {
			ArrayList<StudentResponseDTO> studentList = new ArrayList();
			studentList = new StudentCourseDAO().searchByIdNameCourse(id, name, course);
			Map map = new HashMap();
			for(StudentResponseDTO student: studentList) {
				String courses = new StudentCourseDAO().selectCoursesByStudentId(student.getStudentId());
				map.put(student.getStudentId(), courses);
			}
			
			request.setAttribute("studentList", studentList);
			request.setAttribute("map",map);
			request.getRequestDispatcher("STU003.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
