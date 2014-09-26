package com.ericringer.sfindiefest.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ericringer.sfindiefest.types.Film;


public class Film implements Serializable{
	private static final long serialVersionUID = 676045795640966085L;
	public static final String FILM = "film";
	public static final String TRAILER_URL = "trailerUrl";
	public static final String PURCHASE_URL = "purchaseUrl";
	
	private String filmTitle;
	private String filmImageUrl;
	private String dateTime;
	private String synopsis;
	private String trailerUrl;
	private String filmUid;
	public Film(){}
	public Film(String filmTitle){
		this.filmTitle = filmTitle;
	}
	public Film(String filmTitle,String filmImageUrl,String dateTime,String synopsis,String trailerUrl,String filmUid){
		this.filmTitle = filmTitle;
		this.filmImageUrl = filmImageUrl;
		this.dateTime = dateTime;
		this.synopsis = synopsis;
		this.trailerUrl = trailerUrl;
		this.filmUid = filmUid;
	}
	public String getFilmTitle() {
		return filmTitle;
	}
	public String getFilmImageUrl() {
		return filmImageUrl;
	}
	public void setFilmImageUrl(String filmImageUrl) {
		this.filmImageUrl = filmImageUrl;
	}
	public void setFilmName(String filmName) {
		this.filmTitle = filmName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getTrailerUrl() {
		return trailerUrl;
	}
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}
	public String getFilmUid() {
		return filmUid;
	}
	public void setFilmUid(String filmUid) {
		this.filmUid = filmUid;
	}
	public String getPurchaseUrl(){
		return "http://www.brownpapertickets.com/";
	}
	public static List<Film> getStaticFilms(){
		List<Film> films = new ArrayList<Film>();
		films.add(new Film("All Cheerleaders Die","http://developer.android.com/assets/images/dac_logo.png","12/05/2013 7:00 PM","All Cheerleaders Die is a 2013 American horror film remake. A high school girl's plot to revenge herself on the captain of the football team turns deadly when phantasmagorical elements are tossed into the mix.","https://www.youtube.com/watch?v=5vIvyff3XMoA","1"));
		films.add(new Film("An American Terror","http://developer.android.com/assets/images/dac_logo.png","12/01/2013 5:00 PM","An American Terror is an explosive and surprising modern day horror mash-up. Totally and relentlessly disquieting � great atmosphere and sound and perfectly gruesome.","https://www.youtube.com/watch?v=xN5zX58kS04","2"));
		films.add(new Film("Cheap Thrills","http://developer.android.com/assets/images/dac_logo.png","12/18/2013 7:00 PM","Cheap Thrills centers on Craig, a blue-collar family man supporting his wife and new baby. Leaving his home one disastrous day, Craig receives a notice that he�s behind on his rent and facing eviction. Upon arriving at work, Craig is fired, leaving him despondent. Not wanting to deliver the grim news to his wife, Craig stops off at a bar and runs into an old friend, Vince. Craig and Vince end up joining a wealthy married couple who ply the two friends with drinks and then suggest a series of increasingly strange dares in exchange for more and more money. As the night progresses, the stakes become more dangerous while the money offered increases, leading Craig and Vince to question their judgment and limits.","https://www.youtube.com/watch?v=BLiWbTn1RpE","3"));
		films.add(new Film("Discopath","http://developer.android.com/assets/images/dac_logo.png","12/02/2013 9:00 PM","The mid-70's: a timid young New Yorker leads an uneventful life until he is fatefully exposed to the pulsating rhythms of a brand-new genre of music: disco. Unable to control his murderous impulses that stem from a traumatic childhood experience, Duane Lewis transforms into a dangerous serial killer exiled to Montreal.","https://www.youtube.com/watch?v=FpeSl4Xh5v8","4"));
		films.add(new Film("Evil Feed","http://developer.android.com/assets/images/dac_logo.png","12/17/2013 7:00 PM","A group of young martial artists infiltrate an underground pit fighting ring where the loser is chopped up and served in a Chinese restaurant.","https://www.youtube.com/watch?v=ynpa8_immFk","5"));
		films.add(new Film("Found","http://developer.android.com/assets/images/dac_logo.png","12/12/2013 7:00 PM","Marty is the ideal fifth grader. He gets good grades, listens to his teachers, and doesn�t start trouble in class. But darkness is beginning to fall over Marty�s life. The kids at school won�t stop picking on him, his parents just don�t seem to understand him, and now Marty must grapple with a terrible secret that threatens to destroy life as he knows it.. ","https://www.youtube.com/watch?v=Eca1gMpVbys","6"));
		films.add(new Film("Magligant","http://developer.android.com/assets/images/dac_logo.png","12/14/2013 3:00 PM","After the death of his wife, a grieving man becomes the victim of a horrific experiment to cure his addiction.","https://www.youtube.com/watch?v=s5hkfPntvJ4","7"));
		films.add(new Film("Patrick","http://developer.android.com/assets/images/dac_logo.png","12/17/2013 9:00 PM","Patrick, a young coma patient, has been lying in a hospital bed for years, unable to communicate with anyone. When a beautiful nurse catches his eye, Patrick's latent psychic powers begin to emerge, and soon threaten the lives of everyone in the hospital.","https://www.youtube.com/watch?v=vwhng9xKDew","8"));
		films.add(new Film("Return to Nuke Em High Volume 1","http://developer.android.com/assets/images/dac_logo.png","12/08/2013 9:00 PM","Return to the Class of Nuke 'Em High follows a young couple that are up against the school glee club. Unfortunately, the glee club has mutated into a gang called The Cretins. When the other students begin to undergo mutations, our couple must solve the mystery and save Tromaville High School.","https://www.youtube.com/watch?v=KRtp3crlGoc","9"));
		return films;
	}
	
}
