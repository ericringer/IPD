//
//  FilmTableViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "FilmTableViewController.h"
#import "FilmTableViewCell.h"
#import "Film.h"

#import "FilmDetailsViewController.h"
@interface FilmTableViewController ()

@end

@implementation FilmTableViewController
@synthesize eventStore;
NSArray * films;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    //Set nav title font
    [self.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold " size:17],
      NSFontAttributeName, nil]];
    NSLog(@"View Did Load");
    
    //Retrieve film array
    films = [Film getStaticFilms];

    //Prepare event store for reminders and load reminders into the film objects
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

//Retrieve all reminders and load matching reminders into corresponding film object
-(void)loadReminders{
    EKCalendar *calendar = [eventStore defaultCalendarForNewReminders];
    NSPredicate *predicate = [eventStore predicateForRemindersInCalendars:@[calendar]];
    
    [eventStore fetchRemindersMatchingPredicate:predicate  completion:^(NSArray *reminders) {
        for (int i=0; i<films.count; i++) {
            EKReminder * reminder = [self getReminderForFilm:reminders Film:[films objectAtIndex:i]];
            Film * film = [films objectAtIndex:i];
            [film setReminder:reminder];
        }
    }];
    
    
}

//Retrieve reminder from array for given film title
-(EKReminder *)getReminderForFilm:(NSArray *)reminders Film:(Film *)film{
    for (int i=0; i<reminders.count; i++) {
        if ([[[reminders objectAtIndex:i] title] isEqualToString:[film filmTitle]])
        {
            return [reminders objectAtIndex:i];
        }
    }
    return nil;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

//Returb 1 table section
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

//Return film count for table view
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSLog(@"Film Count: %lu", (unsigned long)[films count]);
    return [films count];
}

//Load film title and film image into cell
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    FilmTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    if(cell == nil){
        cell = [[FilmTableViewCell alloc] initWithStyle:
                UITableViewCellStyleDefault
                                      reuseIdentifier:CellIdentifier];
        [cell.textLabel setTextColor:[UIColor whiteColor]];
    }
    
    Film * film = [films objectAtIndex:indexPath.row];
    cell.filmTitle.text = [film filmTitle];
    [[cell filmImage] setImage:[UIImage imageNamed:[film filmImageName]]];
    
    return cell;
    
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
    //Pass film object and event store into FilmDetailsViewController
    if([segue.identifier isEqualToString:@"FilmDetails"]){
        FilmDetailsViewController *vc = [segue destinationViewController];
        
        Film * film = [films objectAtIndex:[self.tableView indexPathForSelectedRow].row];
        [vc setEventStore:eventStore];
        [vc setFilm:film];
    }
    
}


@end
