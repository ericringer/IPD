//
//  FilmTrailerViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/22/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Film.h"
@interface FilmTrailerViewController : UIViewController
@property (strong, nonatomic) IBOutlet UIWebView *trailerWebView;
@property (nonatomic, retain) Film* film;
@end
