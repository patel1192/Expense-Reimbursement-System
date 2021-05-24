package com.example.servlets;

import javax.servlet.http.HttpServletRequest;

import com.example.controller.EmsControllar;


public class RequestDispatcher {
public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI() + " in RequestDispatcher");
		switch(req.getRequestURI()) {
			case "/ERS/login.change":
			System.out.println("in Login.change dispatcher");
			return EmsControllar.login(req);
		/* return EmsControllar.createTicket(req); */
			 case "/ERS/createTicket.change":
				EmsControllar.createTicket(req);
				return "html/displayTicket.html"; 
			 case "/ERS/logout.change":
					System.out.println("in Logout.change dispatcher");
					EmsControllar.logout(req);
					return "html/logout.html";
				/*
				 * case "/ERS/updateStatus.change": System.out.println("Its me"); return
				 * "html/displayTicketForAdmin.html";
				 */
				
			default:
					System.out.println("in default");
					return "html/unsuccessfullogin.html";
				
			
		
		}
		
	}
}
