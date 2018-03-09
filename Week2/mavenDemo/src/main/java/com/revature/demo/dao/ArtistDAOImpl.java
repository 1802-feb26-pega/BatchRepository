package com.revature.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.demo.pojos.Album;
import com.revature.demo.pojos.Artist;
import com.revature.demo.util.ConnectionFactory;

public class ArtistDAOImpl implements ArtistDAO {

	@Override
	public List<Artist> getAllArtists() {
		
		List<Artist> artists = new ArrayList<Artist>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
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
			e.printStackTrace();
		}
		
		return artists;
	}

	@Override
	public Artist getArtistById(int artistId) {

		Artist artist = new Artist();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM artist WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, artistId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artist.setId(artistId);
				artist.setName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artist;
	}

	@Override
	public Artist addArtist(Artist newArtist) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO artist(name) VALUES (?)";
			
			String[] key = new String[2];
			key[0] = "artistid";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newArtist.getName());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()) {
					newArtist.setId(rs.getInt(1));
				}
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newArtist;
	}

	@Override
	public int updateArtist(int artistId, Artist updatedArtist) {
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE artist SET name = ? WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedArtist.getName());
			pstmt.setInt(2, artistId);
			rowsAffected = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	@Override
	public int removeArtist(int artistId) {
		
		ArtistDAO artistDao = new ArtistDAOImpl();
		
		if(artistDao.hasAlbum(artistId)) {
			System.out.println("Artist cannot be deleted!");
			System.out.println("Remove their albums and try again.");
			return -1;
		}
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM artist WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, artistId);
			rowsAffected = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	@Override
	public boolean hasAlbum(int artistId) {
		
		AlbumDAO albumDao = new AlbumDAOImpl();
		List<Album> artistAlbums = albumDao.getAlbumsByArtistId(artistId);
		
		if(artistAlbums.isEmpty()) {
			return false;
		}
		
		return true;
		
	}

}
