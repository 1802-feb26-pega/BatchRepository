package com.revature.demo.driver;

import java.util.List;

import com.revature.demo.dao.ArtistDAO;
import com.revature.demo.dao.ArtistDAOImpl;
import com.revature.demo.pojos.Artist;

public class DemoDriver {
	public static void main(String[] args) {
		ArtistDAO artDao = new ArtistDAOImpl();
		
		List<Artist> artists = artDao.getAllArtists();
		
		for(Artist a : artists) {
			System.out.println(a);
		}
		
		Artist artist = artDao.getArtistById(3);
		System.out.println();
		System.out.println(artist);
		
		System.out.println();
		String name = artDao.getNameById(2);
		System.out.println(name);
		
//		System.out.println();
//		Artist artist2 = artDao.addArtist("My Band");
//		System.out.println("Added "+ artist2);
	}
}
