package com.revature.demo.driver;

import java.util.List;

import com.revature.demo.dao.ArtistDAO;
import com.revature.demo.dao.ArtistDAOImpl;
import com.revature.demo.pojos.Artist;

public class DemoDriver {

	public static void main(String[] args) {
		ArtistDAO artDao = new ArtistDAOImpl();
		
		List<Artist> artists = artDao.getAllArtists();
		
		for(Artist artist : artists) {
			System.out.println(artist);
		}
		
		Artist a1 = artDao.getArtistById(1);
		System.out.println(a1);
		
		System.out.println(artDao.getNameById(1));
		
		Artist a2 = new Artist(1, "testing new methods");
		Artist a3 = new Artist(1, "testing update");

		System.out.println(artDao.addArtist(a2));
		System.out.println(artDao.updateArtist(277, a3));
		System.out.println(artDao.removeArtist(276));
	}
}
