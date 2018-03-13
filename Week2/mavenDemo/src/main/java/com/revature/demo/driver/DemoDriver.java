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
	}
}
