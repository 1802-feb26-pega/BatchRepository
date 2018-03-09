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
		
		System.out.println(artDao.addArtist("Revature"));
		
		System.out.println(artDao.updateArtist(276, "Update test"));
	}
}
