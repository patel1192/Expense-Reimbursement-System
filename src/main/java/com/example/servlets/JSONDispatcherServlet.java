package com.example.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.EmsControllar;
import com.example.model.EmsUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcherServlet extends HttpServlet {
	public static void process(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {

		System.out.println(req.getRequestURI() + " in JSONDispatcher");
		switch (req.getRequestURI()) {

		case "/ERS/getsessionvill.json":
			EmsControllar.getSessionVill(req, res);
			break;

		case "/ERS/createTicket.json":

			EmsControllar.createTicket(req);
			break;
		case "/ERS/displayAllTickets.json":

			EmsControllar.displayAllTickets(req, res);
			break;

		case "/ERS/displayAllForAdminTickets.json":

			EmsControllar.displayAllTicketsForAdmin(req, res);
			break;

		case "/ERS/changeStatus.json":
			System.out.println("status");
			EmsControllar.changeStatus(req);
			break;
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new EmsUser()));

		}
	}
}
