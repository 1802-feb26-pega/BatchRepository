package com.revature.demo.dao;

import java.util.List;

import com.revature.demo.pojos.Artist;

public interface ArtistDAO {
	
	public List<Artist> getAllArtists();
	public Artist getArtistById(int artistId);
	public Artist addArtist(Artist newArtist);
	public int updateArtist(int artistId, Artist updatedArtist);
	public int removeArtist(int artistId);
	public boolean hasAlbum(int artistId);
	

}
