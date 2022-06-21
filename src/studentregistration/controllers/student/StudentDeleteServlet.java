package studentregistration.controllers.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.StudentDTO;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(id);
		new StudentCourseDAO().deleteCourseListByStudentId(dto.getStudentId());
		new StudentDAO().deleteStudent(dto);
		response.sendRedirect("ShowStudentServlet");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
