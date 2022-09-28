package com.digitalers.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digitalers.entities.User;
import com.digitalers.enums.MessageEnum;

@WebServlet(value = { "/users" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		Long id = Long.valueOf(request.getParameter("id"));
		
		if (action != null && id != null) {
			if (action.equals("update")) {
				// sesion objeto editar
				//request.getSession().setAttribute("userEdit", user);
				
				request.getSession().setAttribute("userEdit", getUserFromId(id));
				request.getRequestDispatcher("usuario.jsp").forward(request, response);

			} else if (action.equals("delete")) {
				delete(id);
			}
		}
		response.sendRedirect("lista.jsp");

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String key1 = request.getParameter("key1");
		String key2 = request.getParameter("key2");
		Boolean active = Boolean.valueOf(request.getParameter("active"));
		String path = "lista.jsp";

		
		User userUpdate = (User) request.getSession().getAttribute("userEdit");
		Boolean editando = (Boolean) request.getSession().getAttribute("editando");

		if (email != null && key1 != null && key2 != null && active != null) {
			
			
			if (!key1.equals(key2)) {
				
				request.setAttribute("messageEnum", MessageEnum.INCORRECT_KEYS);
				path = "usuario.jsp";
				
			} else if (editando != null && userUpdate != null && editando) {
				
				if (!update(userUpdate.getId(), email, key1, active)) {
					request.setAttribute("messageEnum", MessageEnum.INCORRECT_EMAIL);
					path = "usuario.jsp";
				}
				request.getSession().setAttribute("userEdit", null);
				
			} else if (validate(email)) {
				request.setAttribute("messageEnum", MessageEnum.INCORRECT_EMAIL);
				path = "usuario.jsp";
			} else {
				add(new User(null, LocalDateTime.now(), email, key1, active));
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	
	private boolean update (Long id, String email, String key, Boolean active) {
		for (User user : LoginController.users) {
			
			if (user.getId() != id && user.getEmail().equalsIgnoreCase(email)) {
				// no permite un correo ya existente a no ser que sea el suyo
				return false;
			} else if (user.getId() == id) {
				user.setEmail(email);
				user.setKey(key);		
				user.setActive(active);
				break;
			}
		}
		return true;
	}
	

	private void delete(Long id) {
		for (User user : LoginController.users) {
			if (user.getId() == id) {
				LoginController.users.remove(user);
				return;
			}
		}
	}

	
	private void add(User user) {
		Long id = LoginController.users.get(0).getId();
		for (User userAux : LoginController.users) {
			if (userAux.getId() > id) {
				id = userAux.getId();
			}
		}
		user.setId(id + 1);

		LoginController.users.add(user);
	}

	
	private boolean validate(String email) {
		for (User user : LoginController.users) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	
	
	private User getUserFromId(Long id) {
		for (User userAux : LoginController.users) {
			if (id == userAux.getId()) {
				return userAux;
			}
		}
		return null;
	}

}
