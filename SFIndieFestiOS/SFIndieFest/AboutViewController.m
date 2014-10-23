//
//  AboutViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "AboutViewController.h"
#import "NavDrawer.h"
@interface AboutViewController ()

@end

@implementation AboutViewController
@synthesize lblAbout,comingSoonImageView;
NavDrawer * navDrawer;

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
    
    //Load nav drawer
    navDrawer = [[NavDrawer alloc] init];
    [navDrawer setParentView:self];
    [navDrawer createDrawer];
    lblAbout.layer.borderColor = [UIColor blackColor].CGColor;
    lblAbout.layer.borderWidth = 1.0;
    
    //Create array of coming soon film images and create "slideshow" animation
    NSArray * comingSoonImages = [NSArray arrayWithObjects:
				[UIImage imageNamed:@"indie.png"],
				[UIImage imageNamed:@"doc.png"],
				[UIImage imageNamed:@"roller.png"],nil];
	
	comingSoonImageView.animationImages = comingSoonImages;
	comingSoonImageView.animationDuration = 15;
	comingSoonImageView.animationRepeatCount = 0;
	[comingSoonImageView startAnimating];
	
}

//Open or closr nav drawer
-(IBAction)menuButton:(id)sender {
    [navDrawer swingDrawer];
}

-(void)didReceiveMemoryWarning
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
