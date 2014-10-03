package com.ericringer.sfindiefest.types;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;


public class Film implements Serializable{
	private static final long serialVersionUID = 676045795640966085L;
	public static final String FILM = "film";
	public static final String TRAILER_URL = "trailerUrl";
	public static final String PURCHASE_URL = "purchaseUrl";
	
	private String filmTitle;
	private int filmImageID;
	private String dateTime;
	private String synopsis;
	private String trailerUrl;
	private String filmUid;
	private Date convertedDateTime;
	public Film(){}
	public Film(String filmTitle){
		this.filmTitle = filmTitle;
	}
	public Film(String filmTitle,int filmImageID,String dateTime,String synopsis,String trailerUrl,String filmUid){
		this.filmTitle = filmTitle;
		this.filmImageID = filmImageID;
		this.dateTime = dateTime;
		this.synopsis = synopsis;
		this.trailerUrl = trailerUrl;
		this.filmUid = filmUid;

		try {
			SimpleDateFormat  format = new SimpleDateFormat("MM/dd/yyyy hh:mm aa",Locale.US);
			convertedDateTime = format.parse(dateTime);  
		} catch (ParseException e) { }
	}
	public String getFilmTitle() {
		return filmTitle;
	}
	public int getFilmImageID() {
		return filmImageID;
	}
	public void setFilmImageID(int filmImageID) {
		this.filmImageID = filmImageID;
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
		films.add(new Film("All Cheerleaders Die",R.drawable.acd,"12/05/2014 7:00 PM","All Cheerleaders Die is a 2013 American horror film remake. A high school girl's plot to revenge herself on the captain of the football team turns deadly when phantasmagorical elements are tossed into the mix.","https://www.youtube.com/watch?v=ZyV7fLD7k40","1"));
		films.add(new Film("An American Terror",R.drawable.at,"12/01/2014 5:00 PM","An American Terror is an explosive and surprising modern day horror mash-up. Totally and relentlessly disquieting � great atmosphere and sound and perfectly gruesome.","https://www.youtube.com/watch?v=xN5zX58kS04","2"));
		films.add(new Film("Buck Wild",R.drawable.bw,"12/06/2014 9:00 PM","Craig Thompson�s idyllic hunting trip with his two buddies and unstable cousin is wrecked after hearing that his fianc� and best man-to-be have been cavorting behind his back. To make matters worse, his cousin appears on the verge of a murderous rampage while a rapidly-spreading Chupacabra virus is turning locals into the undead", "https://www.youtube.com/watch?v=iPbKu55otj4", "3"));
		films.add(new Film("Cannibal Diner",R.drawable.cd,"12/09/2014 9:00 PM","A group of models find themselves trapped in a factory inhabited by a cannibalistic degenerated family.", "https://www.youtube.com/watch?v=OQOVhWCgnoM", "4"));
		films.add(new Film("Cannon Fodder",R.drawable.cf,"12/14/2014 5:00 PM","Doron, a security operative, who takes on one last mission: to capture, number 3 in the terrorist organization of Hezbollah, in Lebanon. With an elite force, Doron enters Lebanon to complete his last mission. Very soon he discovers that reality is not so simple, and that a new and unknown enemy is to be dealt wit","https://www.youtube.com/watch?v=NYhjydn89vY","5"));
		films.add(new Film("Cheap Thrills",R.drawable.ct,"12/18/2014 7:00 PM","Cheap Thrills centers on Craig, a blue-collar family man supporting his wife and new baby. Leaving his home one disastrous day, Craig receives a notice that he�s behind on his rent and facing eviction. Upon arriving at work, Craig is fired, leaving him despondent. Not wanting to deliver the grim news to his wife, Craig stops off at a bar and runs into an old friend, Vince. Craig and Vince end up joining a wealthy married couple who ply the two friends with drinks and then suggest a series of increasingly strange dares in exchange for more and more money. As the night progresses, the stakes become more dangerous while the money offered increases, leading Craig and Vince to question their judgment and limits.","https://www.youtube.com/watch?v=BLiWbTn1RpE","6"));
		films.add(new Film("Discopath",R.drawable.dp,"12/02/2014 9:00 PM","The mid-70's: a timid young New Yorker leads an uneventful life until he is fatefully exposed to the pulsating rhythms of a brand-new genre of music: disco. Unable to control his murderous impulses that stem from a traumatic childhood experience, Duane Lewis transforms into a dangerous serial killer exiled to Montreal.","https://www.youtube.com/watch?v=FpeSl4Xh5v8","7"));
		films.add(new Film("Evil Feed",R.drawable.ef,"12/17/2014 7:00 PM","A group of young martial artists infiltrate an underground pit fighting ring where the loser is chopped up and served in a Chinese restaurant.","https://www.youtube.com/watch?v=ynpa8_immFk","8"));
		films.add(new Film("Found",R.drawable.found,"12/12/2014 7:00 PM","Marty is the ideal fifth grader. He gets good grades, listens to his teachers, and doesn�t start trouble in class. But darkness is beginning to fall over Marty�s life. The kids at school won�t stop picking on him, his parents just don�t seem to understand him, and now Marty must grapple with a terrible secret that threatens to destroy life as he knows it.. ","https://www.youtube.com/watch?v=Eca1gMpVbys","9"));
		films.add(new Film("House of Dust",R.drawable.dust,"12/08/2014 5:00 PM","College students exploring an abandoned insane asylum accidentally shatter canisters holding the cremains of former mental patients. Inhaling the dusty ash filling the air, they're soon possessed by the souls once held within them. One is a convicted serial killer from 1950.","https://www.youtube.com/watch?v=Sq7FthM_-MU","10"));
		films.add(new Film("House of Good and Evil",R.drawable.house,"12/06/2014","After the tragic loss of their unborn child, Chris and Maggie Conley long to escape the stress of the city and decide a country home is a healthy alternative. This new start begins to fall apart when unresolved secrets begin to surface and a deadly marital mistrust festers until blood is spilled. Some things are better kept apart.","https://www.youtube.com/watch?v=CnrM4AXYCys","11"));
		films.add(new Film("Judas Ghost",R.drawable.judas,"12/07/2014 5:00 PM","The Carnacki Institute exists to Do Something about ghosts. Ghosts have always been with us and it's up to the institute to find them and to lay them to rest. Be it the house that makes you walk by on the other side of the street, the dark figure standing at the top of the stairs, or those unexplained whispers in the night � the Carnacki Institute will locate and exorcise the spirit behind them. When supernatural reports from an old village hall point to an apparently standard haunting, an elite team of Ghost Finders is dispatched to assess the situation. The team of four include a cameraman and former Ghost Finder from the Carnacki Institute, who is there to document events as a training tool for new recruits. But things go from bad to worse when it becomes clear that they are facing something far more sinister than they first anticipated, and the team must use every trick they know to try and get out of the hall alive.","https://www.youtube.com/watch?v=LvK9KByNM9o","12"));
		films.add(new Film("Maligant",R.drawable.malignant,"12/14/2014 3:00 PM","After the death of his wife, a grieving man becomes the victim of a horrific experiment to cure his addiction.","https://www.youtube.com/watch?v=s5hkfPntvJ4","13"));
		films.add(new Film("Motivational Growth",R.drawable.mg,"12/07/2014 7:00 PM","Ian Folivor, depressed and reclusive, finds himself taking advice from a fungal growth after a failed suicide attempt. The Mold, a smooth talking chunk of aspergillus born from the filth collecting in Ian's neglected bathroom, works to win Ian's trust by helping him clean himself up and remodel his lifestyle. Ian attracts the attention of a neighbor, Leah, and begins to receive strange messages from his broken TV that make him realize that The Mold may not be as helpful and well intentioned as it seems to be. Strange characters with even stranger events cast Ian's life a sharp relief in the shadow of an epic battle between good and evil that Ian is only partially aware of.","https://www.youtube.com/watch?v=ToRhJWIBZrE","14"));
		films.add(new Film("On Air",R.drawable.onair,"12/04/2014 7:00 PM","Doc Rock is the host of pirate radio station 'Nighthawk'. His favorite topic: the Nightslasher - a serial killer that haunts the town. Doc gives him bad names, mocks him and challenges his listeners to call in on: 'How hard can it be to prevent the slasher from slaying his next victim?' A bad idea! Just a moment later...the Nightslasher is on the phone line and he forces Doc Rock into a deadly game: If Doc manages to prevent him from killing the victim during the rest of the radio show, he will let her go. Doc Rock has to dig up his innermost secrets...and there is no chance to escape, because the killer is closer than he ever imagined.","https://www.youtube.com/watch?v=Ghno54vbs30","15"));
		films.add(new Film("Patrick",R.drawable.patrick,"12/17/2014 9:00 PM","Patrick, a young coma patient, has been lying in a hospital bed for years, unable to communicate with anyone. When a beautiful nurse catches his eye, Patrick's latent psychic powers begin to emerge, and soon threaten the lives of everyone in the hospital.","https://www.youtube.com/watch?v=vwhng9xKDew","16"));
		films.add(new Film("Pinup Dolls on Ice",R.drawable.pinup,"11/30/2014 7:00 PM","The Pinup Dolls are a hot retro act who put the tease back in striptease. But when an old friend (Suzi Lorraine) hires them to put on a show at a secluded campground, the girls find themselves being stalked by a homicidal maniac with a sick obsession with ice. As they're hunted one-by-one, they soon realize they'll have to rely on more than just their looks to survive this nightmare named Moe.","https://www.youtube.com/watch?v=PFKQAw8MueQ","17"));
		films.add(new Film("Return to Nuke Em High Volume 1",R.drawable.return_image,"12/08/2014 9:00 PM","Return to the Class of Nuke 'Em High follows a young couple that are up against the school glee club. Unfortunately, the glee club has mutated into a gang called The Cretins. When the other students begin to undergo mutations, our couple must solve the mystery and save Tromaville High School.","https://www.youtube.com/watch?v=KRtp3crlGoc","18"));
		films.add(new Film("Septic Man",R.drawable.septic,"12/10/2014 9:00 PM","Jack has a good�if somewhat smelly�life. He has a beautiful wife and a lovely home in a charming town, and if maintaining that life means occasionally being coated in goo, he�s fine with that. Jack�s a plumber. A good one. And so when his town is stricken by a contaminated water crisis, it�s to Jack that the town turns to find and solve the problem while the residents are evacuated away to cleaner pastures. But things don�t go as planned. Sure, Jack finds the source of the problem deep in a long forgotten sewage treatment holding tank, but he�s not alone in the plant and the unfriendly denizens trap him within the fetid tank with no food or clean water.","https://www.youtube.com/watch?v=T7zN_GefAs4","19"));
		films.add(new Film("Stalled",R.drawable.stalled,"11/30/2014 7:00 PM","A janitor gets trapped in a women's restroom and encounters an all-out attack by a hoard of zombies.","https://www.youtube.com/watch?v=Fw5i4Nkn9gQ","20"));
		return films;
	}
	public boolean isReminderSet(Context context) {
		return (getEventID(context) > 0);
	}
	public Date getConvertedDateTime() {
		return convertedDateTime;
	}
	public void setConvertedDateTime(Date convertedDateTime) {
		this.convertedDateTime = convertedDateTime;
	}
	public void setEventID(Context context,long eventID) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs.edit().putLong(filmUid, eventID).commit();
	}
	public long getEventID(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getLong(this.getFilmUid(), -1);
	}
	
}
