package studentregistration.controllers.course;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dto.CourseResponseDTO;

/**
 * Servlet implementation class ShowCoursesServlet
 */
@WebServlet("/ShowCoursesServlet")
public class ShowCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCoursesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CourseResponseDTO> courseList= new CourseDAO().selectCourseAll();
		request.setAttribute("courseList", courseList);
		request.setAttribute("success",request.getAttribute("success"));
		request.setAttribute("error", request.getAttribute("error"));
		request.setAttribute("data", request.getAttribute("data"));
		request.getRequestDispatcher("STU001.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
