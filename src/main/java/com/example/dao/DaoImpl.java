package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import com.example.model.EmsUser;
import com.example.model.ErsType;
import com.example.model.Reimbursement;
import com.example.model.UserRole;
import com.example.model.UserStatus;

public class DaoImpl {
	private Dbconnection dbCon;

	public DaoImpl() {
		dbCon = new Dbconnection();
	}

	public EmsUser getinfo(String username) { // Gettting information

		EmsUser emsuser = new EmsUser();
		// System.out.println(""+emsuser);
		int count = 0;
		try (Connection con = dbCon.getDbConnection()) {

			String sql = "select * from ers_users where ers_username = '" + username + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count++;
				emsuser.setId(rs.getInt(1));
				emsuser.setUsername(rs.getString(2));
				emsuser.setPassword(rs.getString(3));
				emsuser.setFirstName(rs.getString(4));
				emsuser.setLastName(rs.getString(5));
				emsuser.setEmail(rs.getString(6));
				emsuser.setRole(UserRole.valueOf(rs.getInt(7)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (count > 0) {
			return emsuser;
		} else {
			return null;
		}
	}

	public boolean createTicket(Reimbursement reimbursement) {

		try (Connection con = dbCon.getDbConnection()) {

			String sql = "insert into ers_reimbursement(reimb_amount,reimb_submitted,reimb_description,reimb_author,reimb_status_id,reimb_type_id) values ("
					+ reimbursement.getReimbAmount() + ",'" + reimbursement.getReimbSubmitted() + "','"
					+ reimbursement.getReimbDescription() + "'," + reimbursement.getReimbAuthor().getId() + ","
					+ reimbursement.getReimbStatus().getValue() + "," + reimbursement.getReimbType().getValue() + ");";
			Statement statement = con.createStatement();
			System.out.println(sql);
			int count = statement.executeUpdate(sql);
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Reimbursement> getTickets(int userId) {

		try (Connection con = dbCon.getDbConnection()) {

			List<Reimbursement> resList = new ArrayList<Reimbursement>();
			String sql = "select * from ers_reimbursement where reimb_author = " + userId;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement res = new Reimbursement();
				res.setId(rs.getInt(1));
				res.setReimbAmount(rs.getDouble(2));
				res.setReimbSubmitted(rs.getString(3));
				res.setReimbResolved(rs.getString(4));
				res.setReimbDescription(rs.getString(5));
				res.setReimbStatus(UserStatus.valueOf(rs.getInt(8)));
				res.setReimbType(ErsType.valueOf(rs.getInt(9)));
				EmsUser resolver = getUser(rs.getInt(7));
				if (resolver != null) {
					res.setReimbResolver(resolver);
				}
				EmsUser author = getUser(rs.getInt(6));
				if (author != null) {
					res.setReimbAuthor(author);
				}

				resList.add(res);
				
			}
			return resList;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	public EmsUser getUser(int userId) {

		EmsUser emsuser = new EmsUser();
		// System.out.println(""+emsuser);
		int count = 0;
		try (Connection con = dbCon.getDbConnection()) {

			String sql = "select * from ers_users where ers_users_id = " + userId;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count++;
				emsuser.setId(rs.getInt(1));
				emsuser.setUsername(rs.getString(2));
				emsuser.setPassword(rs.getString(3));
				emsuser.setFirstName(rs.getString(4));
				emsuser.setLastName(rs.getString(5));
				emsuser.setEmail(rs.getString(6));
				emsuser.setRole(UserRole.valueOf(rs.getInt(7)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (count > 0) {
			return emsuser;
		} else {
			return null;
		}

	}
	
	
	public List<Reimbursement> getTicketsForAdmin() {

		try (Connection con = dbCon.getDbConnection()) {

			List<Reimbursement> resList = new ArrayList<Reimbursement>();
			String sql = " SELECT ers_reimbursement.*,ers_users.user_first_name ,ers_users.user_last_name ,ers_users.user_email \r\n"
					+ "FROM ers_reimbursement\r\n"
					+ "LEFT JOIN ers_users ON ers_reimbursement.reimb_author = ers_users.ers_users_id;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement res = new Reimbursement();
				res.setId(rs.getInt(1));
				res.setReimbAmount(rs.getDouble(2));
				res.setReimbSubmitted(rs.getString(3));
				res.setReimbResolved(rs.getString(4));
				res.setReimbDescription(rs.getString(5));
				res.setReimbStatus(UserStatus.valueOf(rs.getInt(8)));
				res.setReimbType(ErsType.valueOf(rs.getInt(9)));
				EmsUser resolver = getUser(rs.getInt(7));
				if (resolver != null) {
					res.setReimbResolver(resolver);
				}
				
				EmsUser author = new EmsUser();
				author.setId(rs.getInt(6));
				author.setFirstName(rs.getString(10));
				author.setLastName(rs.getString(11));
				author.setEmail(rs.getString(12));
				
				res.setReimbAuthor(author);
				resList.add(res);
				
			}
			return resList;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public Reimbursement changeStatus(int id,UserStatus status, int userId) {
		try (Connection con = dbCon.getDbConnection()) {
		Reimbursement rem = new Reimbursement();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
			rem.setReimbResolved(dtf.format(now));
			String sql = "update ers_reimbursement set reimb_status_id=" + status.getValue() + ",reimb_resolved='" + rem.getReimbResolved() + "',reimb_resolver=" + userId + " WHERE reimb_id=" + id;

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
						
				
			}
			
			return rem;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
		
	}
}
