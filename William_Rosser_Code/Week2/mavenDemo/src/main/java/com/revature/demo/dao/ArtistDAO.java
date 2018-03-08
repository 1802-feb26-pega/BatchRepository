package com.revature.demo.dao;

import java.util.List;

import com.revature.demo.pojos.Artist;


public interface ArtistDAO {
	
	public List<Artist> getAllArtists();
	public Artist getArtistById(int id);
	public String getNameById(int id);
	public Artist addArtist(String name);
	public Artist updateArtist(int id, String name);

}
