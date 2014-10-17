//
//  ViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/16/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController
NavDrawer * navDrawer;

-(void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    
}

-(IBAction)menuButton:(UIBarButtonItem *)sender
{
    [navDrawer swingDrawer];
}


-(void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
