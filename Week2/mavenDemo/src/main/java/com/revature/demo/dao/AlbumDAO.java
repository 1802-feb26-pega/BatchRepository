package com.revature.demo.dao;

import java.util.List;

import com.revature.demo.pojos.Album;

public interface AlbumDAO {

	public List<Album> getAllAlbums();
	public List<Album> getAlbumsByArtistId(int artistId);
	public Album getAlbumByAlbumId(int albumId);
	public Album addAlbum(Album newAlbum);
	public int updateAlbum(int albumId, Album updatedAlbum);
	public int removeAlbumByAlbumId(int albumId);
	public int removeAlbumsByArtistId(int artistId);
	public boolean hasTracks(int albumId);
	
	
}
