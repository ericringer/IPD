//
//  Film.m
//  SFIndieFest
//
//  Created by eric ringer on 9/24/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "Film.h"

@implementation Film
@synthesize filmTitle,filmImageName,dateTime,convertedDate,synopsis,trailerUrl,filmUid,reminder;

//Custom init method that only recieves a title
-(Film*)initWithTitle:(NSString *)title
{
    filmTitle = title;
    return self;
}

//Custom init method that recieves title,image name, date, synopsis and trailerUrl
-(Film*)initWithValues:(NSString *)title FilmImage:(NSString *)imageName DateTime:(NSString *)date Synopsis:(NSString *) synops TrialerUrl:(NSString *)trailer FilmUid:(NSString *)uid
{
    filmTitle = title;
    filmImageName = imageName;
    dateTime = date;
    synopsis = synops;
    trailerUrl = trailer;
    filmUid = uid;
    
    NSLocale* usLocale = [[NSLocale alloc] initWithLocaleIdentifier:@"en-US"];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setLocale:usLocale];
    [dateFormatter setDateFormat:@"MM/dd/yyyy hh:mm a"];
    
    convertedDate = [dateFormatter dateFromString:dateTime];
    NSCalendar *gregorian = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
    NSDateComponents *comps = [gregorian components:NSUIntegerMax fromDate:convertedDate];
    [comps setHour:9];
    [comps setMinute:0];
    convertedDate = [gregorian dateFromComponents:comps];
    return self;
}

//Returns films purchase url
-(NSString *)getPurchaseUrl
{
return @"http://www.brownpapertickets.com/";
}

//Static method that returns an array of Films
+(NSArray *)getStaticFilms
{
    NSMutableArray *films = [[NSMutableArray alloc]init];
    
    [films addObject:[[Film alloc]initWithValues:@"All Cheerleaders Die" FilmImage:@"acd" DateTime:@"12/05/2014 7:00 PM" Synopsis:@"All Cheerleaders Die is a 2013 American horror film remake. A high school girl's plot to revenge herself on the captain of the football team turns deadly when phantasmagorical elements are tossed into the mix." TrialerUrl:@"https://www.youtube.com/watch?v=ZyV7fLD7k40" FilmUid:@"1"]];
    
    [films addObject:[[Film alloc]initWithValues:@"An American Terror" FilmImage:@"at" DateTime:@"12/01/2014 5:00 PM" Synopsis:@"An American Terror is an explosive and surprising modern day horror mash-up. Totally and relentlessly disquieting – great atmosphere and sound and perfectly gruesome." TrialerUrl:@"https://www.youtube.com/watch?v=xN5zX58kS04" FilmUid:@"2"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Buck Wild" FilmImage:@"bw" DateTime:@"12/06/2014 9:00 PM" Synopsis:@"Craig Thompson’s idyllic hunting trip with his two buddies and unstable cousin is wrecked after hearing that his fiancé and best man-to-be have been cavorting behind his back. To make matters worse, his cousin appears on the verge of a murderous rampage while a rapidly-spreading Chupacabra virus is turning locals into the undead" TrialerUrl:@"https://www.youtube.com/watch?v=iPbKu55otj4" FilmUid:@"3"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Cannibal Diner" FilmImage:@"cd" DateTime:@"12/09/2014 9:00 PM" Synopsis:@"A group of models find themselves trapped in a factory inhabited by a cannibalistic degenerated family." TrialerUrl:@"https://www.youtube.com/watch?v=OQOVhWCgnoM" FilmUid:@"4"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Cannon Fodder" FilmImage:@"cf" DateTime:@"12/14/2014 5:00 PM" Synopsis:@"Doron, a security operative, who takes on one last mission: to capture, number 3 in the terrorist organization of Hezbollah, in Lebanon. With an elite force, Doron enters Lebanon to complete his last mission. Very soon he discovers that reality is not so simple, and that a new and unknown enemy is to be dealt with - and Hezbollah are the last thing on his mind. Doron has to deal with a ticking clock in the form of extensive I.D.F attack and a bloodthirsty enemy, Now that their enemy has changed its face, it's up to him and his unit to wage a new war, a different war, to find an antidote, get back across the border, before the middle east conflict is changed forever." TrialerUrl:@"https://www.youtube.com/watch?v=NYhjydn89vY" FilmUid:@"5"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Cheap Thrills" FilmImage:@"ct" DateTime:@"12/18/2014 7:00 PM" Synopsis:@"Cheap Thrills centers on Craig, a blue-collar family man supporting his wife and new baby. Leaving his home one disastrous day, Craig receives a notice that he’s behind on his rent and facing eviction. Upon arriving at work, Craig is fired, leaving him despondent. Not wanting to deliver the grim news to his wife, Craig stops off at a bar and runs into an old friend, Vince. Craig and Vince end up joining a wealthy married couple who ply the two friends with drinks and then suggest a series of increasingly strange dares in exchange for more and more money. As the night progresses, the stakes become more dangerous while the money offered increases, leading Craig and Vince to question their judgment and limits." TrialerUrl:@"https://www.youtube.com/watch?v=BLiWbTn1RpE" FilmUid:@"6"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Discopath" FilmImage:@"dp" DateTime:@"12/02/2014 9:00 PM" Synopsis:@"The mid-70's: a timid young New Yorker leads an uneventful life until he is fatefully exposed to the pulsating rhythms of a brand-new genre of music: disco. Unable to control his murderous impulses that stem from a traumatic childhood experience, Duane Lewis transforms into a dangerous serial killer exiled to Montreal." TrialerUrl:@"https://www.youtube.com/watch?v=FpeSl4Xh5v8" FilmUid:@"7"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Evil Feed" FilmImage:@"ef" DateTime:@"12/17/2014 7:00 PM" Synopsis:@"A group of young martial artists infiltrate an underground pit fighting ring where the loser is chopped up and served in a Chinese restaurant." TrialerUrl:@"https://www.youtube.com/watch?v=ynpa8_immFk" FilmUid:@"8"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Found" FilmImage:@"found" DateTime:@"12/12/2014 7:00 PM" Synopsis:@"Marty is the ideal fifth grader. He gets good grades, listens to his teachers, and doesn’t start trouble in class. But darkness is beginning to fall over Marty’s life. The kids at school won’t stop picking on him, his parents just don’t seem to understand him, and now Marty must grapple with a terrible secret that threatens to destroy life as he knows it.. " TrialerUrl:@"https://www.youtube.com/watch?v=Eca1gMpVbys" FilmUid:@"9"]];
    
    [films addObject:[[Film alloc]initWithValues:@"House of Dust" FilmImage:@"dust" DateTime:@"12/08/2014 5:00 PM" Synopsis:@"College students exploring an abandoned insane asylum accidentally shatter canisters holding the cremains of former mental patients. Inhaling the dusty ash filling the air, they're soon possessed by the souls once held within them. One is a convicted serial killer from 1950." TrialerUrl:@"https://www.youtube.com/watch?v=Sq7FthM_-MU" FilmUid:@"10"]];
    
    [films addObject:[[Film alloc]initWithValues:@"House of Good and Evil" FilmImage:@"house" DateTime:@"12/06/2014 7:00 PM" Synopsis:@"After the tragic loss of their unborn child, Chris and Maggie Conley long to escape the stress of the city and decide a country home is a healthy alternative. This new start begins to fall apart when unresolved secrets begin to surface and a deadly marital mistrust festers until blood is spilled. Some things are better kept apart.." TrialerUrl:@"https://www.youtube.com/watch?v=CnrM4AXYCys" FilmUid:@"11"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Judas Ghost" FilmImage:@"judas" DateTime:@"12/07/2014 5:00 PM" Synopsis:@"The Carnacki Institute exists to Do Something about ghosts. Ghosts have always been with us and it's up to the institute to find them and to lay them to rest. Be it the house that makes you walk by on the other side of the street, the dark figure standing at the top of the stairs, or those unexplained whispers in the night – the Carnacki Institute will locate and exorcise the spirit behind them. When supernatural reports from an old village hall point to an apparently standard haunting, an elite team of Ghost Finders is dispatched to assess the situation. The team of four include a cameraman and former Ghost Finder from the Carnacki Institute, who is there to document events as a training tool for new recruits. But things go from bad to worse when it becomes clear that they are facing something far more sinister than they first anticipated, and the team must use every trick they know to try and get out of the hall alive.." TrialerUrl:@"https://www.youtube.com/watch?v=LvK9KByNM9o" FilmUid:@"12"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Maligant" FilmImage:@"malignant" DateTime:@"12/14/2014 3:00 PM" Synopsis:@"After the death of his wife, a grieving man becomes the victim of a horrific experiment to cure his addiction." TrialerUrl:@"https://www.youtube.com/watch?v=s5hkfPntvJ4" FilmUid:@"13"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Motivational Growth" FilmImage:@"mg" DateTime:@"12/07/2014 7:00 PM" Synopsis:@"Ian Folivor, depressed and reclusive, finds himself taking advice from a fungal growth after a failed suicide attempt. The Mold, a smooth talking chunk of aspergillus born from the filth collecting in Ian's neglected bathroom, works to win Ian's trust by helping him clean himself up and remodel his lifestyle. Ian attracts the attention of a neighbor, Leah, and begins to receive strange messages from his broken TV that make him realize that The Mold may not be as helpful and well intentioned as it seems to be. Strange characters with even stranger events cast Ian's life a sharp relief in the shadow of an epic battle between good and evil that Ian is only partially aware of." TrialerUrl:@"https://www.youtube.com/watch?v=ToRhJWIBZrE" FilmUid:@"14"]];
    
    [films addObject:[[Film alloc]initWithValues:@"On Air" FilmImage:@"onair" DateTime:@"12/04/2014 7:00 PM" Synopsis:@"Doc Rock is the host of pirate radio station 'Nighthawk'. His favorite topic: the Nightslasher - a serial killer that haunts the town. Doc gives him bad names, mocks him and challenges his listeners to call in on: 'How hard can it be to prevent the slasher from slaying his next victim?' A bad idea! Just a moment later...the Nightslasher is on the phone line and he forces Doc Rock into a deadly game: If Doc manages to prevent him from killing the victim during the rest of the radio show, he will let her go. Doc Rock has to dig up his innermost secrets...and there is no chance to escape, because the killer is closer than he ever imagined." TrialerUrl:@"https://www.youtube.com/watch?v=Ghno54vbs30" FilmUid:@"15"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Patrick" FilmImage:@"patrick" DateTime:@"12/17/2014 9:00 PM" Synopsis:@"Patrick, a young coma patient, has been lying in a hospital bed for years, unable to communicate with anyone. When a beautiful nurse catches his eye, Patrick's latent psychic powers begin to emerge, and soon threaten the lives of everyone in the hospital." TrialerUrl:@"https://www.youtube.com/watch?v=vwhng9xKDew" FilmUid:@"16"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Pinup Dolls on Ice" FilmImage:@"pinup" DateTime:@"11/30/2014 7:00 PM" Synopsis:@"The Pinup Dolls are a hot retro act who put the tease back in striptease. But when an old friend (Suzi Lorraine) hires them to put on a show at a secluded campground, the girls find themselves being stalked by a homicidal maniac with a sick obsession with ice. As they're hunted one-by-one, they soon realize they'll have to rely on more than just their looks to survive this nightmare named Moe." TrialerUrl:@"https://www.youtube.com/watch?v=PFKQAw8MueQ" FilmUid:@"17"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Return to Nuke Em High Volume 1" FilmImage:@"return_image" DateTime:@"12/08/2014 9:00 PM" Synopsis:@"Return to the Class of Nuke 'Em High follows a young couple that are up against the school glee club. Unfortunately, the glee club has mutated into a gang called The Cretins. When the other students begin to undergo mutations, our couple must solve the mystery and save Tromaville High School." TrialerUrl:@"https://www.youtube.com/watch?v=KRtp3crlGoc" FilmUid:@"18"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Septic Man" FilmImage:@"septic" DateTime:@"12/10/2014 9:00 PM" Synopsis:@"Jack has a good—if somewhat smelly—life. He has a beautiful wife and a lovely home in a charming town, and if maintaining that life means occasionally being coated in goo, he’s fine with that. Jack’s a plumber. A good one. And so when his town is stricken by a contaminated water crisis, it’s to Jack that the town turns to find and solve the problem while the residents are evacuated away to cleaner pastures. But things don’t go as planned.Sure, Jack finds the source of the problem deep in a long forgotten sewage treatment holding tank, but he’s not alone in the plant and the unfriendly denizens trap him within the fetid tank with no food or clean water." TrialerUrl:@"https://www.youtube.com/watch?v=T7zN_GefAs4" FilmUid:@"19"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Stalled" FilmImage:@"stalled" DateTime:@"11/29/2014 07:00 PM" Synopsis:@"A janitor gets trapped in a women's restroom and encounters an all-out attack by a hoard of zombies." TrialerUrl:@"https://www.youtube.com/watch?v=Fw5i4Nkn9gQ" FilmUid:@"20"]];
    return films;
}
                                                        
@end
