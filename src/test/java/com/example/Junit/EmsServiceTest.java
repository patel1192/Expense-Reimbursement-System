package com.example.Junit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.example.dao.DaoImpl;

import com.example.model.EmsUser;
import com.example.model.ErsType;
import com.example.model.Reimbursement;
import com.example.model.UserRole;
import com.example.model.UserStatus;
import com.example.service.EmsService;
public class EmsServiceTest {
	
	@Mock
	private DaoImpl fakeDao;
	private EmsService ccServ /* = new CreatureCardService(fakeDao) */;
	private EmsUser user;
	private EmsUser user2;
	private Reimbursement ticket;
	

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); //  will tell mackito to create an instance of ever field member marked by @Mock
		ccServ = new EmsService(fakeDao);
		 user2 = new EmsUser();
		 ticket = new Reimbursement();
		 
		user = new EmsUser(1, "Roma","password","Roma","Patel","teddy.9211@yahoo.com",UserRole.Employee);
		ticket = new Reimbursement(1, 20.0, "2016-06-22 19:10:25","2021-04-25 16:58:42","solved",user, user2, UserStatus.Approved,ErsType.Food);
		when(fakeDao.getinfo("Roma")).thenReturn(user); //this will override the method getByName, and will only perform the 
		//return action
		when(fakeDao.getinfo("nope")).thenReturn(user2);
		when(fakeDao.changeStatus(1, UserStatus.Approved, 1)).thenReturn(ticket);
		//when(fakeDao.getAll()).thenReturn(new ArrayList<CreatureCard>(Arrays.asList(card)));
		//doNothing().when(fakeDao).insert(card2); // how to mock void return type methods
		
	}
	
	@Test
	public void getloginSucess() {
		
		assertEquals("Roma", ccServ.getloginVerify("Roma","password").getUsername());
		
		
	}
	
	@Test
	public void getloginFailure() {
		assertEquals("password", ccServ.getloginVerify("Roma","password").getPassword());
		  }
	
	@Test
	public void getStatusUpdateSucess() {
		assertEquals("Approved", ccServ.changeStatus("1","Approved",1).getReimbStatus());
	}
	/*
	 * @Test public void getCardByNameFailure() {
	 * assertThrows(NullPointerException.class, ()->
	 * ccServ.getCreatureCardByName("nope")); }
	 * 
	 * @Test public void insertCardSuccess() { assertEquals("Card added",
	 * ccServ.insertCreatureCard(card2)); }
	 */
}
