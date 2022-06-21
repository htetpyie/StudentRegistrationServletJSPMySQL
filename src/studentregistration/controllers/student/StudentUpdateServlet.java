package studentregistration.controllers.student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.StudentHelper;
import studentregistration.models.StudentBean;
import studentregistration.persistant.dao.CourseDAO;
import studentregistration.persistant.dao.StudentCourseDAO;
import studentregistration.persistant.dao.StudentDAO;
import studentregistration.persistant.dto.CourseResponseDTO;
import studentregistration.persistant.dto.StudentDTO;
import studentregistration.persistant.dto.StudentResponseDTO;

/**
 * Servlet implementation class StudentUpdateServlet
 */
@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public StudentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


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
		
		request.getRequestDispatcher("STU002-01.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String education = request.getParameter("education");
		String[] courseIdList = request.getParameterValues("course");
		String photo = request.getParameter("photo");
		
		//To get all course
		ArrayList<CourseResponseDTO> courseList= new CourseDAO().selectCourseAll();
		request.setAttribute("courseList", courseList);
		
		//To get the selected course
		String selectedCourses = new StudentCourseDAO().selectCourseIdByStudentId(id);
		request.setAttribute("selectedCourseIdList", selectedCourses);
		
		StudentBean bean = new StudentBean(id, name, dob, gender , phone, education, photo );
		if(bean.getStudentName().isBlank() || bean.getStudentDob().isBlank() || bean.getStudentGender().isBlank() ||
				bean.getStudentPhone().isBlank()
				|| courseIdList==null) {
			request.setAttribute("error", "Fields cannot be blank!");
			request.setAttribute("student", bean);
			request.getRequestDispatcher("STU002-01.jsp").forward(request, response);
		}else {
			StudentDTO dto = new StudentDTO(bean.getStudentId(), bean.getStudentName(),bean.getStudentDob(), bean.getStudentGender(),
					bean.getStudentPhone(), bean.getStudentEducation(), bean.getStudentPhoto() );
			new StudentDAO().updateStudent(dto);
			
			//Delete existing course related with the student and add again
			new StudentCourseDAO().deleteCourseListByStudentId(bean.getStudentId());
			
			for(String courseId: courseIdList) {
				new StudentCourseDAO().insertStudentCourse(dto.getStudentId(), courseId);
			}
			
			request.setAttribute("success", "Student Update Successfully");
			response.sendRedirect("ShowStudentServlet");
		}
	}

}
