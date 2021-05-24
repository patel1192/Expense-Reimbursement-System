package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.DaoImpl;
import com.example.model.EmsUser;
import com.example.model.Reimbursement;
import com.example.model.UserRole;
import com.example.service.EmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ReimbursementList {
	public List<Reimbursement> data;
	
	public ReimbursementList(List<Reimbursement> list) {
		this.data = list;
	}
}

public class EmsControllar {
	static EmsService serv = new EmsService(new DaoImpl());
	// private static HttpSession session;

	public static String login(HttpServletRequest req) {
		System.out.println("in controller login");
		if (!req.getMethod().equals("POST")) {
			return "html/index.html";
		}

		// EmsControllar.session = req.getSession();
		EmsUser user = serv.getloginVerify(req.getParameter("UserName"), req.getParameter("password"));
		System.out.println(user);
		if (user == null) {
			return "wrongcreds.change";
		} else {

			if (user.getRole() == UserRole.FinanceManager) {
				System.out.println("seetting session!");
				req.getSession().setAttribute("currentVill", user);
				System.out.println("in Manager");
				return "html/adminHome.html";
			} else {
				System.out.println("seetting session!");
				req.getSession().setAttribute("currentVill", user);
				return "html/home.html";
			}
		}
	}

	public static String logout(HttpServletRequest req) {
		System.out.println("in controller logout");
		Enumeration<String> attributes = req.getAttributeNames();
		while(attributes.hasMoreElements()) {
			String param = attributes.nextElement();
		    System.out.println(param);
		}
		req.getSession(false).removeAttribute("currentVill");
		req.getSession(false).invalidate();
		// EmsControllar.session = null;

		if (!req.getMethod().equals("POST")) {
			System.out.println("in post in logout");
			return "html/index.html";
		}	
		

		return "html/index.html";

	}

	public static void getSessionVill(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("inside");
		if (req.isRequestedSessionIdValid()) {
			System.out.println("inside IF #78");
			EmsUser user = (EmsUser) req.getSession(false).getAttribute("currentVill");
			System.out.println(user);
			res.setStatus(HttpServletResponse.SC_OK);
			System.out.println(res.getStatus());
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));	
		} else {
			System.out.println("inside ELSE #83");
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	public static String createTicket(HttpServletRequest req) {
		System.out.println("in create ticket");
		if (!req.getMethod().equals("POST")) {
			return "html/index.html";
		}
		System.out.println("Hello");
		EmsUser author = (EmsUser) req.getSession(false).getAttribute("currentVill");
		// System.out.println(req.getParameter("amount"));
		System.out.println(req);
		System.out.println(author);
		System.out.println(req.getParameter("type"));
		System.out.println(req.getParameter("amount"));
		System.out.println(req.getParameter("description"));
		Reimbursement rem = serv.createTicket(req.getParameter("type"), req.getParameter("amount"),
				req.getParameter("description"), author);
		return null;
	}

	public static void displayAllTickets(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (req.isRequestedSessionIdValid()) {
			EmsUser author = (EmsUser) req.getSession(false).getAttribute("currentVill");
			List<Reimbursement> remList = serv.getAllTickets(author.getId());
			res.setStatus(200);
			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();

			ReimbursementList obj = new ReimbursementList(remList);
			String response = mapper.writeValueAsString(obj);
			System.out.println(response);
			res.setContentType("application/json");
			pw.write(response);	
		} else {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	public static void displayAllTicketsForAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("display");

		// EmsUser author = (EmsUser) EmsControllar.session.getAttribute("currentVill");
		List<Reimbursement> remList = serv.getAllTicketsForAdmin();
		res.setStatus(200);
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		
		ReimbursementList obj = new ReimbursementList(remList);
		String response = mapper.writeValueAsString(obj);
		System.out.println(response);
		res.setContentType("application/json");
		pw.write(response);

	}

	public static void changeStatus(HttpServletRequest req) {
		EmsUser author = (EmsUser) req.getSession(false).getAttribute("currentVill");

		System.out.println(author);
		Reimbursement rem = serv.changeStatus(req.getParameter("id"), req.getParameter("action"), author.getId());
		System.out.println(rem);

	}
}