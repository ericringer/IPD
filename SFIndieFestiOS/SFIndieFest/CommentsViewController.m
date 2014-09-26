//
//  CommentsViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "CommentsViewController.h"
#import "NavDrawer.h"
@interface CommentsViewController ()

@end

@implementation CommentsViewController
NavDrawer * navDrawer;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    
}

- (IBAction)menuButton:(id)sender {
    [navDrawer swingDrawer];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
