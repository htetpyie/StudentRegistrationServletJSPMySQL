package studentregistration.controllers.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.CourseHelper;
import helpers.UserHelper;
import studentregistration.models.CourseBean;
import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dto.CourseDTO;

@WebServlet("/CourseRegisterServlet")
public class CourseRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CourseRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = CourseHelper.idGenerator() ;
		String courseName = request.getParameter("courseName");
		CourseBean bean = new CourseBean();
		bean.setCourseId(id);
		bean.setCourseName(courseName);
		if(id.isBlank() || courseName.isBlank()) {
			request.setAttribute("error", "Course Name must be filled.");
			//request.setAttribute("course", bean);			
		}
		else if(CourseHelper.isCourseExist(courseName)) {
			request.setAttribute("error", courseName+" alerady exists. Try another one!");
			//request.setAttribute("course", bean);
		}
		else {
			CourseDTO dto = new CourseDTO();
			dto.setCourseId(id);
			dto.setCourseName(courseName);
			new CourseDAO().insertCourse(dto);
			request.setAttribute("success" ,courseName +"is successfully added.");
		}
		
		request.getRequestDispatcher("BUD003.jsp").forward(request, response);
	}

}
