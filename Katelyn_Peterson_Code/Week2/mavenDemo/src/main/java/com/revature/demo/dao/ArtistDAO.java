package com.revature.demo.dao;

import java.util.List;

import com.revature.demo.pojos.Artist;

public interface ArtistDAO
{
	public List<Artist> getAllArtists();
	public Artist getArtistById(int id);
	public String getNameById(int id);
	public void addArtist(Artist newArtist);
	public void updateArtist(int id, Artist updatedArtist);
	public void removeArtist(int id);
}
