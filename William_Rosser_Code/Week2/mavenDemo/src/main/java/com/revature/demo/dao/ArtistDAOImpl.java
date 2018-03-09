package com.revature.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.demo.pojos.Artist;
import com.revature.demo.util.ConnectionFactory;

public class ArtistDAOImpl implements ArtistDAO {

	public List<Artist> getAllArtists() {
		List<Artist> artists = new ArrayList<Artist>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM artist";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Artist temp = new Artist();
				temp.setArtistId(rs.getInt(1));
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
		return null;
	}

	public String getNameById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addArtist(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateArtist(int id, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int removeArtist(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
