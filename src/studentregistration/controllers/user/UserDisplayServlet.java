package studentregistration.controllers.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.UserResponseDTO;

/**
 * Servlet implementation class UserDisplayServlet
 */
@WebServlet("/UserDisplayServlet")
public class UserDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<UserResponseDTO> list = new UserDAO().selectUserAll();
		request.setAttribute("userList", list);
		request.getRequestDispatcher("USR003.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
