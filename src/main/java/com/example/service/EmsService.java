package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.log4j.Logger;
import com.example.dao.DaoImpl;
import com.example.model.EmsUser;
import com.example.model.ErsType;
import com.example.model.Reimbursement;
import com.example.model.UserStatus;

public class EmsService {
	private DaoImpl Dao;
	private Logger log;

	public EmsService(DaoImpl Dao) {
		super();
		this.log = Logger.getLogger(EmsService.class);
		this.Dao = Dao;
	}

	public EmsUser getloginVerify(String username, String password) {
		try {
			EmsUser user = Dao.getinfo(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					return user;
				} else {
					this.log.error("Logging failed for username: " + username);
				}
			}
		}catch(Exception e){
			log.error(e);
		}
		

		return null;
	}
	
	public Reimbursement createTicket(String type,String amount,String description,EmsUser author) {
		Reimbursement rem = new Reimbursement();
		rem.setReimbAuthor(author);
		rem.setReimbAmount(Double.parseDouble(amount));
		rem.setReimbDescription(description);
		rem.setReimbStatus(UserStatus.Pending);
		
		rem.setReimbType(ErsType.valueOf(type));
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
		rem.setReimbSubmitted(dtf.format(now));
		System.out.println("in service class" +rem);
		return Dao.createTicket(rem) ? rem : null;
		
	}
	
	public List<Reimbursement> getAllTickets(int userId){
		return Dao.getTickets(userId);
	}
	
	public List<Reimbursement> getAllTicketsForAdmin(){
		return Dao.getTicketsForAdmin();
}
	
	public Reimbursement changeStatus(String id,String action, int userId ) {
		
		return Dao.changeStatus(Integer.parseInt(id),UserStatus.valueOf(action),userId);
	}
}