//
//  FilmDetailsViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "FilmDetailsViewController.h"
#import "FilmTrailerViewController.h"
#import "WebViewController.h"
@interface FilmDetailsViewController ()

@end

@implementation FilmDetailsViewController
@synthesize film;
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
    [self.navigationController.navigationBar setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      [UIFont fontWithName:@"Superclarendon-Bold " size:17],
      NSFontAttributeName, nil]];
    self.navigationItem.title = [film filmTitle];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Navigation

 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
 {
     if([segue.identifier isEqualToString:@"BuyTicket"]){
         WebViewController *vc = [segue destinationViewController];
         [vc setFilm:film];
     }
     if([segue.identifier isEqualToString:@"Trailer"]){
         FilmTrailerViewController *vc = [segue destinationViewController];
         [vc setFilm:film];
     }
 }


@end
