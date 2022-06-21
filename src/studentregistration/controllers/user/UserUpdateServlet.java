package studentregistration.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.UserHelper;
import studentregistration.models.UserBean;
import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.UserDTO;
import studentregistration.persistant.dto.UserResponseDTO;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//To show data in USR003
		String id = request.getParameter("id");
		UserDTO dto = new UserDTO();
		dto.setUserId(id);
		UserResponseDTO userRes = new UserDAO().selectUser(dto);
		request.setAttribute("user", userRes);
		request.getRequestDispatcher("USR002.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cf_password = request.getParameter("cfpassword");
		String role = request.getParameter("role");		
		String id = request.getParameter("id"); // get from request;
		
		UserBean userBean = new UserBean(id, email, name, password, role);
		if(name.isBlank() || email.isBlank() || password.isBlank() ) {
			request.getSession().setAttribute("user_data", userBean);
			request.setAttribute("error", "Field cannot be blank! " );
			request.getRequestDispatcher("USR002.jsp").forward(request, response);
			}
		else if(!password.equals(cf_password)) {
			request.getSession().setAttribute("user_data", userBean);
			request.setAttribute("error", "Password doesn't match." );
			request.getRequestDispatcher("USR002.jsp").forward(request, response);
			}				
		else {
			UserDTO userDTO = new UserDTO(userBean.getUserId(), userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword(), userBean.getUserRole());
			UserDAO userDAO = new UserDAO();
			userDAO.updateUser(userDTO)	;		
			response.sendRedirect("UserDisplayServlet");
		}
	}

}
