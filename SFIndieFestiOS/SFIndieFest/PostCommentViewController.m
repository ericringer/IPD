//
//  PostCommentViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 10/13/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "PostCommentViewController.h"
#import <Parse/Parse.h>
@interface PostCommentViewController ()

@end

@implementation PostCommentViewController
@synthesize commentTextView,film;
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
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
	// Do any additional setup after loading the view.
}
- (IBAction)btnPostComment:(id)sender {
    PFObject *filmComment = [PFObject objectWithClassName:@"filmComments"];
    filmComment[@"filmTitle"] = [film filmTitle];
    filmComment[@"filmComment"] = [commentTextView text];
    [filmComment saveInBackground];
    [filmComment saveInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
        [[self navigationController] popViewControllerAnimated:YES];
    }];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
