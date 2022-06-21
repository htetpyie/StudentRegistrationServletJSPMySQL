package studentregistration.controllers.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.CurrentDate;
import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.UserResponseDTO;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
//		if(id.equals("admin") && password.equals("password")) {
//			request.getRequestDispatcher("MNU001.jsp").forward(request, response);
//		}
		//Current Date
		String current_date = CurrentDate.now();
		
		UserDAO userDAO = new UserDAO();
		ArrayList<UserResponseDTO> list = userDAO.selectUserAll();
		if(list != null) {			
			for(UserResponseDTO user: list) {
				if( (user.getUserId().equals("id") || user.getUserEmail().equals(id))
						&& user.getUserPassword().equals(password) ) {
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("isLogin","Okay");
					request.getSession().setAttribute("date", current_date);
					request.getRequestDispatcher("MNU001.jsp").forward(request, response);
				}
			}
		}
		
		request.setAttribute("error", "Please check your data again! ");
		request.getRequestDispatcher("LGN001.jsp").forward(request, response);;
		
	}

}
