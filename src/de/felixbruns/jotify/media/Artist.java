package de.felixbruns.jotify.media;

import java.util.ArrayList;
import java.util.List;

import de.felixbruns.jotify.util.Hex;
import de.felixbruns.jotify.util.XMLElement;

public class Artist {
	private String       id;
	private String       name;
	private String       portrait;
	private float        popularity;
	private List<Artist> similarArtists;
	
	public Artist(String id, String name){
		this.id             = id;
		this.name           = name;
		this.portrait       = null;
		this.popularity     = -1.0f;
		this.similarArtists = new ArrayList<Artist>();
	}
	
	private Artist(){
		this.id             = null;
		this.name           = null;
		this.portrait       = null;
		this.popularity     = -1.0f;
		this.similarArtists = new ArrayList<Artist>();
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPortrait(){
		return this.portrait;
	}
	
	public float getPopularity(){
		return this.popularity;
	}
	
	public List<Artist> getSimilarArtists(){
		return this.similarArtists;
	}
	
	public static Artist fromXMLElement(XMLElement artistElement){
		Artist artist = new Artist();
		
		/* Set id. */
		if(artistElement.hasChild("id")){
			artist.id = artistElement.getChildText("id");
		}
		
		/* Set name. */
		if(artistElement.hasChild("name")){
			artist.name = artistElement.getChildText("name");
		}
		
		/* Set portrait. */
		if(artistElement.hasChild("portrait") && artistElement.getChild("portrait").hasChild("id")){
			artist.portrait = artistElement.getChild("portrait").getChildText("id");
		}
		
		/* Set popularity. */
		if(artistElement.hasChild("popularity")){
			artist.popularity = Float.parseFloat(artistElement.getChildText("popularity"));
		}
		
		/* Set similar artists. */
		if(artistElement.hasChild("similar-artists")){
			for(XMLElement similarArtistElement : artistElement.getChild("similar-artists").getChildren()){
				artist.similarArtists.add(Artist.fromXMLElement(similarArtistElement));
			}
		}
		
		/* TODO bios, genres, years-active, albums, ... */
		
		return artist;
	}
	
	public String toString(){
		return String.format("[%s]", this.name);
	}
	
	public static Artist fromURI(String uri) {
		Artist artist = new Artist();
		
		artist.id = Hex.URIToId(uri);
		
		return artist;
	}
}
