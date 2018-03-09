package com.revature.demo.dao;

import java.util.List;

import com.revature.demo.pojos.Artist;

public interface ArtistDAO {

	public List<Artist> getAllArtists();
	public Artist getArtistById(int id);
	public String getNameById(int id);
	public Artist addArtist(Artist artist);
	public int updateArtist(int id, String newName);
	public int removeArtist(int id);
	public boolean hasAlbum();
	
}
