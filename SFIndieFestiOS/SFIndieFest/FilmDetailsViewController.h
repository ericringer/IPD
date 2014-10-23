//
//  FilmDetailsViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Film.h"
#include <EventKit/EventKit.h>
@interface FilmDetailsViewController : UIViewController

@property (nonatomic, retain) Film* film;
@property (strong, nonatomic) IBOutlet UITextView *filmSynopsis;
@property (strong, nonatomic) IBOutlet UILabel *dateTimeLabel;
@property (strong, nonatomic) IBOutlet UIImageView *filmImage;

@property (strong, nonatomic) EKEventStore *eventStore;
@property (strong, nonatomic) IBOutlet UISwitch *scheduleSwitch;
- (IBAction)btnReminder:(id)sender;

@end
