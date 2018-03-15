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

public class AlbumDAOImpl implements AlbumDAO {

	@Override
	public List<Album> getAllAlbums() {
		
		List<Album> albums = new ArrayList<Album>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM album";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Album temp = new Album();
				temp.setId(rs.getInt(1));
				temp.setTitle(rs.getString(2));
				temp.setArtistId(rs.getInt(3));
				albums.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return albums;
	}
	
	@Override
	public List<Album> getAlbumsByArtistId(int artistId) {
		
		List<Album> albums = new ArrayList<Album>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM album WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Album temp = new Album();
				temp.setId(rs.getInt(1));
				temp.setTitle(rs.getString(2));
				temp.setArtistId(artistId);
				albums.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return albums;
	}

	@Override
	public Album getAlbumByAlbumId(int albumId) {
		
		Album album = new Album();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM album WHERE albumid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				album.setId(albumId);
				album.setTitle(rs.getString(2));
				album.setArtistId(rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Album addAlbum(Album newAlbum) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO album(name, artistid) VALUES (?, ?)";
			
			String[] key = new String[1];
			key[0] = "albumid";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newAlbum.getTitle());
			pstmt.setInt(2, newAlbum.getArtistId());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()) {
					newAlbum.setId(rs.getInt(1));
				}
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newAlbum;
	}

	@Override
	public int updateAlbum(int albumId, Album updatedAlbum) {
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE album SET title = ?, artistid = ? WHERE albumid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedAlbum.getTitle());
			pstmt.setInt(2, updatedAlbum.getArtistId());
			pstmt.setInt(3, albumId);
			rowsAffected = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}

	@Override
	public int removeAlbumByAlbumId(int albumId) {
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "DELETE FROM album WHERE albumid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			rowsAffected = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
		
	}
	
	@Override
	public int removeAlbumsByArtistId(int artistId) {
		
		AlbumDAO albumDao = new AlbumDAOImpl();
		List<Album> artistAlbums = albumDao.getAlbumsByArtistId(artistId);
		
		if(!artistAlbums.isEmpty() ) {
			for(Album album: artistAlbums) {
				if(albumDao.hasTracks(album.getId())) {
					System.out.println("The album, " + album.getTitle() + ", cannot be removed.");
					System.out.println("Please remove its tracks and try again.\n");
				}
			}
			return -1;
		}
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "DELETE FROM album WHERE artistid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, artistId);
			rowsAffected = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
		
	}
	
	@Override
	public boolean hasTracks(int albumId) {
		// To be fully implemented later, for now we know that ALL albums have tracks
		return true;
	}

}
