//
//  FilmDetailsViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "FilmDetailsViewController.h"
#import "FilmTrailerViewController.h"
#import "PostCommentViewController.h"
#import "Film.h"
#import "WebViewController.h"
@interface FilmDetailsViewController ()

@end

@implementation FilmDetailsViewController
@synthesize film, filmSynopsis, dateTimeLabel, filmImage,eventStore,scheduleSwitch;

BOOL didGrant;
-(id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(void)viewDidLoad
{
    [super viewDidLoad];
    
    //Set nav title font
    [self.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold " size:17],
      NSFontAttributeName, nil]];
    
    //Load film details unto view
    self.navigationItem.title = [film filmTitle];
    [filmSynopsis setText:[film synopsis]];
    [dateTimeLabel setText:[film dateTime]];
    [filmImage setImage:[UIImage imageNamed:[film filmImageName]]];
    
    if([film reminder] !=nil)[scheduleSwitch setOn:YES];
}

//Add or remove reminder
- (IBAction)btnReminder:(id)sender {
    NSLog(@"%@ %@ Switched: %hhd",[film filmTitle],[film convertedDate],[scheduleSwitch isOn]);
    if([scheduleSwitch isOn]){
        [self addReminder];
    }else{
        [self removeReminder];
    }
}

//Add film reminder
-(void)addReminder{
    EKReminder *reminder = [EKReminder
                            reminderWithEventStore:self.eventStore];
    
    reminder.title = [film filmTitle];
    
    reminder.calendar = [eventStore defaultCalendarForNewReminders];
    
    NSDate *date = [film convertedDate];
    
    EKAlarm *alarm = [EKAlarm alarmWithAbsoluteDate:date];
    
    [reminder addAlarm:alarm];
    
    
    NSError *error = nil;
    
    [eventStore saveReminder:reminder commit:YES error:&error];
    
    if (error){
        NSLog(@"error = %@", error);
    }else{
        [film setReminder:reminder];
    }
}

//Remove reminder
-(void)removeReminder{
    if([film reminder]!=nil){
        NSError *error = nil;
        [eventStore removeReminder:[film reminder] commit:YES error:&error];
        if (error)
            NSLog(@"error = %@", error);
    }
}
-(void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

//Pass film object to view controller
 -(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
 {
     if([segue.identifier isEqualToString:@"BuyTicket"]){
         WebViewController *vc = [segue destinationViewController];
         [vc setFilm:film];
     }
     if([segue.identifier isEqualToString:@"Trailer"]){
         FilmTrailerViewController *vc = [segue destinationViewController];
         [vc setFilm:film];
     }
     if([segue.identifier isEqualToString:@"PostComment"]){
         PostCommentViewController *vc = [segue destinationViewController];
         [vc setFilm:film];
     }
 }


@end
