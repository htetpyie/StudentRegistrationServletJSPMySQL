package studentregistration.controllers.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.dao.UserDAO;
import studentregistration.persistant.dto.UserDTO;
import studentregistration.persistant.dto.UserResponseDTO;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	
		String name = request.getParameter("name");
		if(name.isBlank() && id.isBlank()) {
			response.sendRedirect("UserDisplayServlet");
		}
		else {
			UserDTO dto = new UserDTO();
			dto.setUserId(id);
			UserResponseDTO searchById = new UserDAO().selectUser(dto);
			ArrayList<UserResponseDTO> list = new UserDAO().selectUsersByName(name);		
			list.add(searchById);
			request.setAttribute("userList", list);
			request.getRequestDispatcher("USR003.jsp").forward(request, response);
				
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
