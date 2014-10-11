//
//  SearchViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "SearchViewController.h"
#import "NavDrawer.h"
@interface SearchViewController ()

@end

@implementation SearchViewController
@synthesize txtSearch,tableView;
NavDrawer * navDrawer;
NameSearchResult * searchResult;
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
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    searchResult = [[NameSearchResult alloc] init];
    
}


-(IBAction)menuButton:(id)sender
{
    [navDrawer swingDrawer];
}

- (IBAction)btnSearch:(id)sender {
    [txtSearch resignFirstResponder];
    NameSearch * search = [[NameSearch alloc] init];
    [search performSearch:[txtSearch text] ViewController:self ResultBlock:^(NameSearchResult * result) {
        searchResult = nil;
        searchResult = result;
        [tableView reloadData];
      }];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    return YES;
}
-(void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return searchResult.data.knownFor.count;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    if(cell == nil){
        cell = [[UITableViewCell alloc] initWithStyle:
                UITableViewCellStyleDefault
                                        reuseIdentifier:CellIdentifier];
        
        [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
    }
    NSArray * array = [[searchResult data] knownFor];
    KnownFor * knownFor = [array objectAtIndex:indexPath.row];
    [[cell textLabel] setText:[knownFor title]];
    
    return cell;
    
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
