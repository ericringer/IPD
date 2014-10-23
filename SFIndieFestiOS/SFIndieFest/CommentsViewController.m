//
//  CommentsViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "CommentsViewController.h"
#import "NavDrawer.h"
#import <Parse/Parse.h>
@interface CommentsViewController ()

@end

@implementation CommentsViewController
@synthesize theTableView;
NavDrawer * navDrawer;
NSArray *comments;
PFObject * selectedItem;

-(void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    //Load nav drawer
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    
    comments = [[NSArray alloc]init];
    
}

//Pu;; film comments from Parse and load reload table view
-(void)viewDidAppear:(BOOL)animated{
        PFQuery *query = [PFQuery queryWithClassName:@"filmComments"];
        [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
            if (!error) {
                comments = objects;
                [theTableView reloadData];
            } else {
                // Log details of the failure
                NSLog(@"Error: %@ %@", error, [error userInfo]);
            }
        }];

}

//Open or close nav drawer
-(IBAction)menuButton:(id)sender {
    [navDrawer swingDrawer];
}

-(void)didReceiveMemoryWarning
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
    return [comments count];
}

//Load film title and film comment on cell
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [theTableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    if(cell == nil){
        cell = [[UITableViewCell alloc] initWithStyle:
                UITableViewCellStyleSubtitle
                                      reuseIdentifier:CellIdentifier];
        [cell.textLabel setTextColor:[UIColor whiteColor]];
        [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
    }
    
    PFObject * comment = [comments objectAtIndex:indexPath.row];
    
    cell.textLabel.text = [comment valueForKey:@"filmTitle"];
    cell.detailTextLabel.text = [comment valueForKey:@"filmComment"];
    return cell;
    
}

//Dismiss keyboard
- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    return YES;
}

//Show alert view with full comment and option to share comment to Facebook
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    selectedItem = [comments objectAtIndex:indexPath.row];
    
    UIAlertView *messageAlert = [[UIAlertView alloc]
                                 initWithTitle:[selectedItem objectForKey:@"filmTitle"] message:[selectedItem objectForKey:@"filmComment"] delegate:self cancelButtonTitle:@"OK" otherButtonTitles:@"Share",nil];
    
    [messageAlert show];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];

    
}

//If share vutton is pressed share comment
- (void)alertView:(UIAlertView *)alertView
clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 1){
        [self shareComment:[selectedItem objectForKey:@"filmTitle"] Comment:[selectedItem objectForKey:@"filmComment"]];
    }
    selectedItem = nil;
}

//Share comment to Facebook
- (void)shareComment:(NSString *)filmTitle Comment:(NSString *)comment {
    UIActivityViewController *controller =
    [[UIActivityViewController alloc]
     initWithActivityItems:@[filmTitle,comment]
     applicationActivities:nil];
    controller.excludedActivityTypes = @[UIActivityTypePostToWeibo,
                                         UIActivityTypeMessage,
                                         UIActivityTypeMail,
                                         UIActivityTypePrint,
                                         UIActivityTypeCopyToPasteboard,
                                         UIActivityTypeAssignToContact,
                                         UIActivityTypeSaveToCameraRoll,
                                         UIActivityTypeAddToReadingList,
                                         UIActivityTypePostToFlickr,
                                         UIActivityTypePostToVimeo,
                                         UIActivityTypePostToTencentWeibo,
                                         UIActivityTypeAirDrop,
                                         UIActivityTypePostToTwitter];
    [self presentViewController:controller animated:YES completion:nil];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
