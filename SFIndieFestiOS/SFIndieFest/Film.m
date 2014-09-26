//
//  Film.m
//  SFIndieFest
//
//  Created by Eduardo Quiroz on 9/24/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "Film.h"

@implementation Film
@synthesize filmTitle,filmImageUrl,dateTime,synopsis,trailerUrl,filmUid;


-(Film*) initWithTitle:(NSString *)title
{
    filmTitle = title;
    return self;
}

-(Film*) initWithValues:(NSString *)title FilmImageUrl:(NSString *) imageUrl DateTime:(NSString *)date Synopsis:(NSString *) synops TrialerUrl:(NSString *)trailer FilmUid:(NSString *)uid
{
    filmTitle = title;
    filmImageUrl = imageUrl;
    dateTime = date;
    synopsis = synops;
    trailerUrl = trailer;
    filmUid = uid;
    return self;
}

-(NSString *)getPurchaseUrl{
return @"http://www.brownpapertickets.com/";
}

+(NSArray *)getStaticFilms{
    NSMutableArray *films = [[NSMutableArray alloc]init];
    
    [films addObject:[[Film alloc]initWithValues:@"All Cheerleaders Die" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/05/2013 7:00 PM" Synopsis:@"All Cheerleaders Die is a 2013 American horror film remake. A high school girl's plot to revenge herself on the captain of the football team turns deadly when phantasmagorical elements are tossed into the mix." TrialerUrl:@"https://www.youtube.com/watch?v=5vIvyff3XMo" FilmUid:@"1"]];
    
    [films addObject:[[Film alloc]initWithValues:@"An American Terror" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/01/2013 5:00 PM" Synopsis:@"An American Terror is an explosive and surprising modern day horror mash-up. Totally and relentlessly disquieting – great atmosphere and sound and perfectly gruesome." TrialerUrl:@"https://www.youtube.com/watch?v=xN5zX58kS04" FilmUid:@"2"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Cheap Thrills" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/18/2013 7:00 PM" Synopsis:@"Cheap Thrills centers on Craig, a blue-collar family man supporting his wife and new baby. Leaving his home one disastrous day, Craig receives a notice that he’s behind on his rent and facing eviction. Upon arriving at work, Craig is fired, leaving him despondent. Not wanting to deliver the grim news to his wife, Craig stops off at a bar and runs into an old friend, Vince. Craig and Vince end up joining a wealthy married couple who ply the two friends with drinks and then suggest a series of increasingly strange dares in exchange for more and more money. As the night progresses, the stakes become more dangerous while the money offered increases, leading Craig and Vince to question their judgment and limits." TrialerUrl:@"https://www.youtube.com/watch?v=BLiWbTn1RpE" FilmUid:@"3"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Discopath" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/02/2013 9:00 PM" Synopsis:@"The mid-70's: a timid young New Yorker leads an uneventful life until he is fatefully exposed to the pulsating rhythms of a brand-new genre of music: disco. Unable to control his murderous impulses that stem from a traumatic childhood experience, Duane Lewis transforms into a dangerous serial killer exiled to Montreal." TrialerUrl:@"https://www.youtube.com/watch?v=FpeSl4Xh5v8" FilmUid:@"4"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Evil Feed" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/17/2013 7:00 PM" Synopsis:@"A group of young martial artists infiltrate an underground pit fighting ring where the loser is chopped up and served in a Chinese restaurant." TrialerUrl:@"https://www.youtube.com/watch?v=ynpa8_immFk" FilmUid:@"5"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Found" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/12/2013 7:00 PM" Synopsis:@"Marty is the ideal fifth grader. He gets good grades, listens to his teachers, and doesn’t start trouble in class. But darkness is beginning to fall over Marty’s life. The kids at school won’t stop picking on him, his parents just don’t seem to understand him, and now Marty must grapple with a terrible secret that threatens to destroy life as he knows it.. " TrialerUrl:@"https://www.youtube.com/watch?v=Eca1gMpVbys" FilmUid:@"6"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Magligant" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/14/2013 3:00 PM" Synopsis:@"After the death of his wife, a grieving man becomes the victim of a horrific experiment to cure his addiction." TrialerUrl:@"https://www.youtube.com/watch?v=s5hkfPntvJ4" FilmUid:@"7"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Patrick" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/17/2013 9:00 PM" Synopsis:@"Patrick, a young coma patient, has been lying in a hospital bed for years, unable to communicate with anyone. When a beautiful nurse catches his eye, Patrick's latent psychic powers begin to emerge, and soon threaten the lives of everyone in the hospital." TrialerUrl:@"https://www.youtube.com/watch?v=vwhng9xKDew" FilmUid:@"8"]];
    
    [films addObject:[[Film alloc]initWithValues:@"Return to Nuke Em High Volume 1" FilmImageUrl:@"https://developer.apple.com/programs/ios/images/overview-distribute-040310.jpg" DateTime:@"12/08/2014 9:00 PM" Synopsis:@"Return to the Class of Nuke 'Em High follows a young couple that are up against the school glee club. Unfortunately, the glee club has mutated into a gang called The Cretins. When the other students begin to undergo mutations, our couple must solve the mystery and save Tromaville High School." TrialerUrl:@"https://www.youtube.com/watch?v=KRtp3crlGoc" FilmUid:@"9"]];
    return films;
}
                                                        
@end
