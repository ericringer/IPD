//
//  ScheduleViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "ScheduleViewController.h"
#import "NavDrawer.h"
#import "Film.h"
#import "ScheduleViewCell.h"
#include <EventKit/EventKit.h>
@interface ScheduleViewController ()

@end

@implementation ScheduleViewController
@synthesize tableView;
NavDrawer * navDrawer;
NSArray * films;
EKEventStore *eventStore;
-(id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    
    [self.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold " size:17],
      NSFontAttributeName, nil]];

    films = [Film getStaticFilms];
    
    eventStore = [[EKEventStore alloc] init];
    [eventStore requestAccessToEntityType:EKEntityTypeReminder
                               completion:^(BOOL granted, NSError *error) {
                                   if (granted){
                                       [self loadReminders];
                                   }else{
                                       NSLog(@"Access to store not granted");
                                   }
                                   return;
                               }];

    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}
-(void)loadReminders{
    EKCalendar *calendar = [eventStore defaultCalendarForNewReminders];
    NSPredicate *predicate = [eventStore predicateForRemindersInCalendars:@[calendar]];
    
    [eventStore fetchRemindersMatchingPredicate:predicate  completion:^(NSArray *reminders) {
        for (int i=0; i<films.count; i++) {
            EKReminder * reminder = [self getReminderForFilm:reminders Film:[films objectAtIndex:i]];
            Film * film = [films objectAtIndex:i];
            [film setReminder:reminder];
        }
        [tableView reloadData];
    }];
    

}
-(EKReminder *)getReminderForFilm:(NSArray *)reminders Film:(Film *)film{
    for (int i=0; i<reminders.count; i++) {
        if ([[[reminders objectAtIndex:i] title] isEqualToString:[film filmTitle]])
        {
            return [reminders objectAtIndex:i];
        }
    }
    return nil;
}
-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];

     
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSLog(@"Film Count: %i", [films count]);
    return [films count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    ScheduleViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    if(cell == nil){
        cell = [[ScheduleViewCell alloc] initWithStyle:
                UITableViewCellStyleDefault
                                        reuseIdentifier:CellIdentifier];
        [cell.textLabel setTextColor:[UIColor whiteColor]];
    }
    
    Film * film = [films objectAtIndex:indexPath.row];
    [cell setFilm:film];
    [cell setEventStore:eventStore];
    [[cell scheduleText] setText:[film filmTitle]];
    [[cell filmTime] setText:[film dateTime]];
    [[cell scheduleSwitch] setOn:NO];
    if([film reminder] !=nil)[[cell scheduleSwitch] setOn:YES];
    return cell;
    
}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{

    ScheduleViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    
    BOOL isSet = [cell.scheduleSwitch isOn];
    [cell.scheduleSwitch setOn:!isSet animated:YES];
   [cell btnReminder:self];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    
}
-(IBAction)menuButton:(id)sender {
    [navDrawer swingDrawer];
}


/*
 // Override to support conditional editing of the table view.
 - (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
 {
 // Return NO if you do not want the specified item to be editable.
 return YES;
 }
 */

/*
 // Override to support editing the table view.
 - (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
 {
 if (editingStyle == UITableViewCellEditingStyleDelete) {
 // Delete the row from the data source
 [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
 } else if (editingStyle == UITableViewCellEditingStyleInsert) {
 // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
 }
 }
 */

/*
 // Override to support rearranging the table view.
 - (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
 {
 }
 */

/*
 // Override to support conditional rearranging of the table view.
 - (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
 {
 // Return NO if you do not want the item to be re-orderable.
 return YES;
 }
 */

#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
   
    
}


@end
