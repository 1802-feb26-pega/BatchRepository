package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.backend.ReimbStatus;
import com.revature.backend.ReimbType;
import com.revature.backend.Reimbursement;
import com.revature.backend.User;

public class ReimbursementDAO {

	private Connection conn;

	// Initialize connection
	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	// Method for inserting a new reimbursement
	public Reimbursement insertReimb(User author, double amount, ReimbType type, ReimbStatus status, String description)
			throws SQLException {
		System.out.println("ReimbDao: reached insertReimb()");

		String sql = "INSERT INTO TRMS_REIMBURSEMENT(" + " REIMB_AMOUNT, REIMB_SUBMITTED,"
				+ " REIMB_DESCRIPTION, REIMB_AUTHOR," + " REIMB_STATUS_ID, REIMB_TYPE_ID)" + " VALUES(?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql, new String[] { "REIMB_ID" });
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		stmt.setDouble(1, amount);
		stmt.setTimestamp(2, ts);
		stmt.setString(3, description);
		stmt.setInt(4, author.getUser_id());
		stmt.setInt(5, 1);
		stmt.setInt(6, type.getType_id());
		stmt.executeQuery();

		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int pk = rs.getInt(1);

		System.out.println("-----------------------ReimbDao: insertReimb: do we get here before execute?");

		Reimbursement reimb = new Reimbursement(pk, amount, ts, null, description, author, null, status, type);
		System.out.println("ReimbDao: insertReimb(): " + reimb);
		return reimb;
	}
	//Method for getting all reimbursements
	public List<Reimbursement> getAllReimbs() throws SQLException {
		List<Reimbursement> list = new ArrayList<>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT," + " s.REIMB_STATUS_ID, s.REIMB_STATUS,"
				+ " t.REIMB_TYPE_ID, t.REIMB_TYPE," + " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
				+ " auth._USERS_ID AS AUTHOR_ID," + " auth.TRMS_USERNAME AS AUTHOR_USERNAME,"
				+ " auth.USER_FIRST_NAME AS AUTHOR_FIRST_NAME," + " auth.USER_LAST_NAME AS AUTHOR_LAST_NAME,"
				+ " auth.USER_EMAIL AS AUTHOR_EMAIL," + " res.TRMS_USERS_ID AS RESOLVER_ID,"
				+ " res.TRMS_USERNAME AS RESOLVER_USERNAME," + " res.USER_FIRST_NAME AS RESOLVER_FIRST_NAME,"
				+ " res.USER_LAST_NAME AS RESOLVER_LAST_NAME," + " res.USER_EMAIL AS RESOLVER_EMAIL"
				+ " FROM TRMS_REIMBURSEMENT r" + " JOIN TRMS_REIMBURSEMENT_TYPE t"
				+ " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID" + " JOIN TRMS_REIMBURSEMENT_STATUS s"
				+ " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID" + " LEFT JOIN TRMS_USERS auth"
				+ " ON r.REIMB_AUTHOR = auth.TRMS_USERS_ID" + " LEFT join TRMS_users res"
				+ " ON r.reimb_resolver = res.TRMS_USERS_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapReimbs(rs, list);
		System.out.println("getAllReimbs(): List: " + list);
		System.out.println("List Size: " + list.size());
		return list;
	}
	//Method for getting reimbursements by the author who created them
	public List<Reimbursement> getReimbByAuthor(int author_id) throws SQLException {
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT," + " s.REIMB_STATUS_ID, s.REIMB_STATUS,"
				+ " t.REIMB_TYPE_ID, t.REIMB_TYPE," + " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
				+ " auth.TRMS_USERS_ID AS AUTHOR_ID," + " auth.TRMS_USERNAME AS AUTHOR_USERNAME,"
				+ " auth.USER_FIRST_NAME AS AUTHOR_FIRST_NAME," + " auth.USER_LAST_NAME AS AUTHOR_LAST_NAME,"
				+ " auth.USER_EMAIL AS AUTHOR_EMAIL," + " res.TRMS_USERS_ID AS RESOLVER_ID,"
				+ " res.TRMS_USERNAME AS RESOLVER_USERNAME," + " res.USER_FIRST_NAME AS RESOLVER_FIRST_NAME,"
				+ " res.USER_LAST_NAME AS RESOLVER_LAST_NAME," + " res.USER_EMAIL AS RESOLVER_EMAIL"
				+ " FROM TRMS_REIMBURSEMENT r" + " JOIN TRMS_REIMBURSEMENT_TYPE t"
				+ " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID" + " JOIN TRMS_REIMBURSEMENT_STATUS s"
				+ " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID" + " LEFT JOIN TRMS_USERS auth"
				+ " ON r.REIMB_AUTHOR = auth.TRMS_USERS_ID" + " LEFT join TRMS_users res"
				+ " ON r.reimb_resolver = res.TRMS_USERS_ID" + " WHERE r.REIMB_AUTHOR = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, author_id);
		ResultSet rs = stmt.executeQuery();
		mapReimbs(rs, results);
		System.out.println(results);
		return results;
	}
	//Method for mapping the reimbursements
	private void mapReimbs(ResultSet rs, List<Reimbursement> list) throws SQLException {
		int id;
		double amount;
		User author;
		User resolver;
		ReimbStatus status;
		ReimbType type;
		String description;
		Timestamp submitted, resolved;
		Reimbursement reimb;

		while (rs.next()) {
			id = rs.getInt("REIMB_ID");
			amount = rs.getDouble("REIMB_AMOUNT");
			author = setUser(rs, true);
			System.out.println("got user for isAuthor: " + author);
			resolver = setUser(rs, false);
			System.out.println("got user for isNotAuthor: " + resolver);
			status = new ReimbStatus(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
			type = new ReimbType(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
			description = rs.getString("REIMB_DESCRIPTION");
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			reimb = new Reimbursement(id, amount, submitted, resolved, description, author, resolver, status, type);
			System.out.println("mapReimbs(): Is author: " + reimb);
			list.add(reimb);
		}

	}
	//Method for setting a user
	private User setUser(ResultSet rs, boolean isAuthor) throws SQLException {
		int id;
		String username, lastName, firstName, email;
		if (isAuthor) {
			id = rs.getInt("AUTHOR_ID");
			username = rs.getString("AUTHOR_USERNAME");
			firstName = rs.getString("AUTHOR_FIRST_NAME");
			lastName = rs.getString("AUTHOR_LAST_NAME");
			email = rs.getString("AUTHOR_EMAIL");
		} else {
			if (rs.getString("RESOLVER_USERNAME") == null)
				return null;
			id = rs.getInt("RESOLVER_ID");
			username = rs.getString("RESOLVER_USERNAME");
			firstName = rs.getString("RESOLVER_FIRST_NAME");
			lastName = rs.getString("RESOLVER_LAST_NAME");
			email = rs.getString("RESOLVER_EMAIL");
		}
		return new User(id, username, firstName, lastName, email, null);
	}
	//Method for updating reimbursements
	public void update(Reimbursement reimb) throws SQLException {
		String sql = "UPDATE REIMBURSMENT" + " SET REIMB_STATUS_ID = ?, REIMB_RESOLVED = ?, REIMB_RESOLVER = ?"
				+ " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getStatus_id().status_id);
		stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
		stmt.setInt(3, reimb.getResolver_id().getUser_id());
		stmt.setInt(4, reimb.getId());
		stmt.executeUpdate();
	}
	//Method for mapping the rows
	private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {
		while (rs.next()) {
			int id = rs.getInt("reimb_id");
			Double amount = rs.getDouble("reimb_amount");
			Date date_submitted = rs.getDate("reimb_submitted");
			Date date_resolved = rs.getDate("reimb_resolved");
			String description = rs.getString("reimb_decription");

			User author = new User();
			author.setUser_id(rs.getInt("reimb_author"));

			User resolver = new User();
			resolver.setUser_id(rs.getInt("reimb_resolver"));

			ReimbStatus status_id = new ReimbStatus();
			status_id.setStatus_id(rs.getInt("reimb_status_id"));

			ReimbType type_id = new ReimbType();
			type_id.setType_id(rs.getInt("reimb_type_id"));

			Reimbursement obj = new Reimbursement(id, amount, date_submitted, date_resolved, description, author,
					resolver, status_id, type_id);
			results.add(obj);
		}
	}
	//Method that returns the reimbursement by status
	public List<Reimbursement> getReimbByStatus(String reimb_status) throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = "select TRMS_reimbursement.reimb_status_id" + " from TRMS_reimbursement_status"
				+ " inner join TRMS_reimbursement"
				+ " on TRMS_reimbursement_status.reimb_status_id = TRMS_reimbursement.reimb_status_id"
				+ " where reimb_status like '%" + reimb_status + "%'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		System.out.println("Get Reimbs By Status for: " + reimb_status + ": " + results);
		return results;
	}
	//Method for gets the status
	public List<ReimbStatus> getStatus() throws SQLException {
		List<ReimbStatus> results = new ArrayList<>();
		String sql = "SELECT REIMB_STATUS_ID, REIMB_STATUS" + " FROM TRMS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int status_id = rs.getInt("REIMB_STATUS_ID");
			String status = rs.getString("REIMB_STATUS");
			ReimbStatus obj = new ReimbStatus(status_id, status);
			results.add(obj);
		}
		System.out.println("ReimbDAO: getStatus: " + results);
		return results;
	}
	//Method that gets the types of reimbursements
	public List<ReimbType> getTypes() throws SQLException {
		List<ReimbType> results = new ArrayList<>();
		String sql = "SELECT REIMB_TYPE_ID, REIMB_TYPE" + " FROM TRMS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int type_id = rs.getInt("REIMB_TYPE_ID");
			String type = rs.getString("REIMB_TYPE");
			ReimbType obj = new ReimbType(type_id, type);
			results.add(obj);
		}
		System.out.println("ReimbDAO: getTypes: " + results);
		return results;
	}
	//Method that gets the reimbursement type by ID
	public ReimbType getTypeByID(int type_id) throws SQLException {

		String reimb_type = null;
		String sql = "SELECT REIMB_TYPE" + " FROM TRMS_REIMBURSEMENT_TYPE" + " WHERE REIMB_TYPE_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, type_id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			reimb_type = rs.getString("REIMB_TYPE");
		}
		ReimbType type = new ReimbType(type_id, reimb_type);
		System.out.println("ReimbDAO: getTypeByID(): " + type);
		return type;
	}
	//Method that gets the reimbursement status by ID
	public ReimbStatus getStatusByID(int status_id) throws SQLException {
		String reimb_status = null;

		String sql = "SELECT REIMB_STATUS" + " FROM TRMS_REIMBURSEMENT_STATUS" + " WHERE REIMB_STATUS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, status_id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			reimb_status = rs.getString("REIMB_STATUS");
		}
		ReimbStatus status = new ReimbStatus(status_id, reimb_status);
		System.out.println("ReimbDAO: getTypeByID(): " + status);
		return status;
	}
	//Method for updating the status of a reimbursement with a prepared statement
	void updateStatus(int reimb_id, int resolver, int status_id, Timestamp ts) throws SQLException {
		String sql = "UPDATE TRMS_REIMBURSEMENT" + " SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?,"
				+ " REIMB_RESOLVED = ?" + " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, status_id);
		stmt.setInt(2, resolver);
		stmt.setTimestamp(3, ts);
		stmt.setInt(4, reimb_id);
		stmt.executeQuery();
	}
	//Method for getting reimbursements by ID
	Reimbursement getReimbById(int reimb_id) throws SQLException {
		String sql = "SELECT REIMB_AMOUNT," + " s.REIMB_STATUS_ID, s.REIMB_STATUS," + " t.REIMB_TYPE_ID, t.REIMB_TYPE,"
				+ " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED," + " auth.TRMS_USERS_ID AS AUTHOR_ID,"
				+ " auth.TRMS_USERNAME AS AUTHOR_USERNAME," + " auth.USER_FIRST_NAME AS AUTHOR_FIRST_NAME,"
				+ " auth.USER_LAST_NAME AS AUTHOR_LAST_NAME," + " auth.USER_EMAIL AS AUTHOR_EMAIL,"
				+ " res.TRMS_USERS_ID AS RESOLVER_ID," + " res.TRMS_USERNAME AS RESOLVER_USERNAME,"
				+ " res.USER_FIRST_NAME AS RESOLVER_FIRST_NAME," + " res.USER_LAST_NAME AS RESOLVER_LAST_NAME,"
				+ " res.USER_EMAIL AS RESOLVER_EMAIL" + " FROM TRMS_REIMBURSEMENT r" + " JOIN TRMS_REIMBURSEMENT_TYPE t"
				+ " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID" + " JOIN TRMS_REIMBURSEMENT_STATUS s"
				+ " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID" + " LEFT JOIN TRMS_USERS auth"
				+ " ON r.REIMB_AUTHOR = auth.TRMS_USERS_ID" + " LEFT join TRMS_users res"
				+ " ON r.reimb_resolver = res.TRMS_USERS_ID" + " WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb_id);

		double amount;
		User author;
		User resolver;
		ReimbStatus status;
		ReimbType type;
		String description;
		Timestamp submitted, resolved;
		Reimbursement reimb = null;

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			amount = rs.getDouble("REIMB_AMOUNT");
			author = setUser(rs, true);
			resolver = setUser(rs, false);
			status = new ReimbStatus(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
			type = new ReimbType(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
			description = rs.getString("REIMB_DESCRIPTION");
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			reimb = new Reimbursement(reimb_id, amount, submitted, resolved, description, author, resolver, status,
					type);
		}
		return reimb;
	}
}