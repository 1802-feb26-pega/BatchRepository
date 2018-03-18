package com.revature.demo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.demo.pojos.Artist;
import com.revature.demo.util.ConnectionFactory;

public class ArtistDAOImpl implements ArtistDAO{

	public List<Artist> getAllArtists() {
		// TODO Auto-generated method stub
		List<Artist> artists = new ArrayList<Artist>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM artist";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Artist temp = new Artist();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				artists.add(temp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artists;
	}

	public Artist getArtistById(int id) {
		// TODO Auto-generated method stub
		Artist artist = new Artist();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM artist WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artist;
	}

	public String getNameById(int id) {
		// TODO Auto-generated method stub
		String name = "";
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT name FROM artist WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			name = rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	public Artist addArtist(Artist artist) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO artist(name) VALUES (?)";
			String[] key = new String[1];
			key[0] = "artistid";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, artist.getName());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if(rowsAffected > 0) {
				while(rs.next()) {
					artist.setId(rs.getInt(1));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artist;
	}

	public int updateArtist(int id, String newName) {
		// TODO Auto-generated method stub
		int rowsAffected = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
		String sql ="UPDATE artist SET name = ? WHERE artistid = ?";
		PreparedStatement pstmt = conn.preparedStatement(sql);
		pstmt
		}
		return 0;
	}
	
	public int removeArtist(int id) {
		ArtistDAO artistDao = new ArtistDAOImpl();
		if(artistDao)
		return 0;
	}
	
	public boolean hasAlbum() {
		return false;
		
	}

}