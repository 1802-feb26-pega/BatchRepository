package com.pchase95.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.project1.pojos.Location;
import com.pchase95.project1.util.ConnectionFactory;

public class LocationDAO implements DAO<Location> {


	@Override
	public List<Location> getAll() {
		List<Location> results = new LinkedList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM trms_location";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveLocation(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Location getById(long id) {
		Location loc = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM trms_location WHERE loc_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				loc = retrieveLocation(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loc;
	}

	@Override
	public boolean add(Location newLoc) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"INSERT INTO trms_location (loc_country, loc_city, loc_province, loc_postal_code, loc_address_1, loc_address_2, loc_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String[] keys = { "loc_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			prepareLocation(newLoc, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newLoc.setId(rs.getLong(1));
				}	
				conn.commit();
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(long id, Location updatedLoc) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = 
					"UPDATE trms_location SET loc_country = ?, loc_city = ?, loc_province = ?, loc_postal_code = ?, loc_address1 = ?, loc_address2 = ?, loc_phone = ? WHERE loc_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareLocation(updatedLoc, pstmt);
			pstmt.setLong(8, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	@Override
	public boolean remove(long id) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM trms_location WHERE loc_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Location retrieveLocation(ResultSet rs) {
		try {
			Location loc = new Location();
			loc.setId(rs.getLong(1));
			loc.setCountry(rs.getString(2));
			loc.setCity(rs.getString(3));
			loc.setProvince(rs.getString(4));
			loc.setPostalCode(rs.getString(5));
			loc.setAddress1(rs.getString(6));
			loc.setAddress2(rs.getString(7));
			loc.setPhone(rs.getString(8));
			
			return loc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void prepareLocation(Location loc, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, loc.getCountry());
			pstmt.setString(2, loc.getCity());
			pstmt.setString(3, loc.getProvince());
			pstmt.setString(4, loc.getPostalCode());
			pstmt.setString(5, loc.getAddress1());
			pstmt.setString(6, loc.getAddress2());
			pstmt.setString(7, loc.getPhone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
