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

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cf_password = request.getParameter("cfpassword");
		String role = request.getParameter("role");
		String id = UserHelper.idGenerator(); // get auto generator id;		
		
		UserBean userBean = new UserBean(id, email, name, password, role);
		if(name.isBlank() || email.isBlank() || password.isBlank() ) {
			request.getSession().setAttribute("user_data", userBean);
			request.setAttribute("error", "Field cannot be blank! " );
			request.getRequestDispatcher("USR001.jsp").forward(request, response);
			}
		else if(!password.equals(cf_password)) {
			request.getSession().setAttribute("user_data", userBean);
			request.setAttribute("error", "Password doesn't match." );
			request.getRequestDispatcher("USR001.jsp").forward(request, response);
			}
		else if(UserHelper.isEmailExist(email)) {
			request.getSession().setAttribute("user_data", userBean);
			request.setAttribute("error", "Email already exists." );
			request.getRequestDispatcher("USR001.jsp").forward(request, response);			
		}
		else {
			UserDTO userDTO = new UserDTO(userBean.getUserId(), userBean.getUserEmail(), userBean.getUserName(), userBean.getUserPassword(), userBean.getUserRole());
			UserDAO userDAO = new UserDAO();
			userDAO.insertUser(userDTO);
			request.getSession().setAttribute("user_data","" );
			request.getSession().invalidate();
			request.setAttribute("user", userBean);
			request.getRequestDispatcher("LGN001.jsp").forward(request, response);
		}
		
		
	}

}
