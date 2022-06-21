package studentregistration.controllers.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.StudentHelper;
import studentregistration.models.StudentBean;
import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.StudentDTO;

/**
 * Servlet implementation class StudentRegisterServlet
 */
@WebServlet("/StudentRegisterServlet")
public class StudentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StudentRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = StudentHelper.idGenerator();
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String education = request.getParameter("education");
		String[] courseIdList = request.getParameterValues("course");
		String photo = request.getParameter("photo");
		
		StudentBean bean = new StudentBean(id, name, dob, gender , phone, education, photo );
		if(bean.getStudentName().isBlank() || bean.getStudentDob().isBlank() || bean.getStudentGender().isBlank() ||
				bean.getStudentPhone().isBlank()
				|| courseIdList==null) {
			request.setAttribute("error", "Fields cannot be blank!");
			request.setAttribute("data", bean);
			request.getRequestDispatcher("ShowCoursesServlet").forward(request, response);
		}else {
			
			StudentDTO dto = new StudentDTO(bean.getStudentId(), bean.getStudentName(),bean.getStudentDob(), bean.getStudentGender(),
					bean.getStudentPhone(), bean.getStudentEducation(), bean.getStudentPhoto() );
			new StudentDAO().insertStudent(dto);
			
			//Adding student_course
			for(String courseId: courseIdList) {
				new StudentCourseDAO().insertStudentCourse(dto.getStudentId(), courseId);
			}
			
			request.setAttribute("success", "Student Add Successfully");
			request.getRequestDispatcher("ShowCoursesServlet").forward(request, response);
		}
	
	}

}
