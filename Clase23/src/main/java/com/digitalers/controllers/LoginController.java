package com.digitalers.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digitalers.commons.time.DateUtils;
import com.digitalers.entities.User;
import com.digitalers.enums.MessageEnum;

@WebServlet(value = { "/login", "/sesion" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static List<User> users = new ArrayList<>();

	public LoginController() {
		super();
		initUsers();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean invalidate = Boolean.valueOf(request.getParameter("invalidate"));

		if (request.getSession().getId() != null && invalidate != null && invalidate) {
			request.getSession().invalidate();
			request.setAttribute("messageEnum", MessageEnum.CLOSE_SESSION);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			response.sendRedirect("lista.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String key = request.getParameter("key");
		User user = getUser(email, key);
		String path = "index.jsp";
		HttpSession session = null;
		if (user != null) {
			if (!user.isActive()) {
				request.setAttribute("messageEnum", MessageEnum.INACTIVE_USER);
			} else {
				// muere por tiempo o por invalidacion de sesion
				session = request.getSession();
				session.setMaxInactiveInterval(60);
				session.setAttribute("user", user);
				// muere en la peticion o respuesta
				// request.setAttribute("users", users);
				path = "lista.jsp";
			}
		} else {
			request.setAttribute("messageEnum", MessageEnum.INCORRECT_CREDENTIALS);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);

	}

	private User getUser(String email, String key) {
		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email) && user.getKey().equals(key)) {
				return user;
			}
		}
		return null;
	}

	private void initUsers() {

		users.add(new User(1L,
				DateUtils.getLocalDateTime("2022-03-15 16:05:33", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_24H),
				"user1@gmail.com", "1234", false));
		users.add(new User(2L,
				DateUtils.getLocalDateTime("2022-04-18 17:05:33", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_24H),
				"user2@gmail.com", "1234", true));
		users.add(new User(3L,
				DateUtils.getLocalDateTime("2022-05-30 09:05:44", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_24H),
				"user3@gmail.com", "1234", false));
		users.add(new User(4L,
				DateUtils.getLocalDateTime("2022-05-30 16:05:56", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_24H),
				"user4@gmail.com", "1234", true));

		users.add(new User(5L,
				DateUtils.getLocalDateTime("2022-05-30 16:05:56", DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_24H),
				"user5@gmail.com", "1234", true));
	}

}
